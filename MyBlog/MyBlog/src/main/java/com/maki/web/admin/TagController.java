package com.maki.web.admin;

import com.maki.po.Tag;
import com.maki.po.Type;
import com.maki.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /*
     * 实现分页效果，需要传递Pageable对象，该对象中
     * 指定每页最大记录数，以id排序，降序的方式
     * 也可以指定当前页数(number)
     * */
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                               Pageable pageable, Model model) {
        //查询所有的分类，以分页的形式
        Page<Tag> page = tagService.listTag(pageable);
        //将该Page对象传递给页面显示
        //Page中存放的其实就是一个个json，包括：数据内容(content)、每一页的最大条数，一共几页，排序方式......
        model.addAttribute("page", page);
        return "admin/tags";
    }

    /*
     * 新增标签页
     * */
    @GetMapping("/tags/input")
    public String toTagRelease() {
        return "admin/tags-release";
    }

    /*
     * 修改标签页与新增标签页共用一个html
     * 根据访问页面时，是否传入参数，来动态显示页面
     *
     * */
    @GetMapping("/tags/{id}/input")
    public String toTypeUpdate(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag", tag);
        return "admin/tags-release";
    }


    /*
     * 获取新增标签页提交的数据
     * 校验，若数据库中有相同纪录，不保存
     * 否则，保存到数据库中，并重新发送查询所有数据请求
     * */
    @PostMapping("/tags/release")
    public String tagRelease(Tag tag, RedirectAttributes redirectAttributes) {
        Tag t = tagService.getTagByTagName(tag.getName());
        //表示数据库中已有相同的标签
        if (t != null) {
            //不保存数据，并提示错误信息
            redirectAttributes.addFlashAttribute("errormessage", "添加失败,已有重复标签!!!");
        } else if (tag.getName().equalsIgnoreCase("null")) {
            redirectAttributes.addFlashAttribute("errormessage", "添加失败!!!");
        } else if (tag.getName() == null) {
            redirectAttributes.addFlashAttribute("errormessage", "添加失败!!!");
        } else {
            //校验成功，添加一条标签
            Tag success = tagService.saveTag(tag);
            redirectAttributes.addFlashAttribute("message", "添加成功!!!");
        }
        //重新发送查询数据请求，并发送成功信息给页面
        return "redirect:/admin/tags";
    }

    /*
     * 获取修改标签页提交的数据
     * 校验（唯一且不为空）
     * */
    @PostMapping("/tags/update")
    public String tagUpdate(Tag tag, RedirectAttributes redirectAttributes) {
        Tag t = tagService.getTagByTagName(tag.getName());
        //表示数据库中已有相同的标签
        if (t != null) {
            //不更新数据，并提示错误信息
            redirectAttributes.addFlashAttribute("errormessage", "更新失败,已有重复标签!!!");
        } else if (tag.getName().equalsIgnoreCase("null")) {
            redirectAttributes.addFlashAttribute("errormessage", "更新失败!!!");
        } else if (tag.getName() == null) {
            redirectAttributes.addFlashAttribute("errormessage", "更新失败!!!");
        } else {
            //校验成功，通过id来更新这条记录
            tagService.updateTag(tag.getId(), tag);
            redirectAttributes.addFlashAttribute("message", "更新成功!!!");
        }
        //重新发送查询数据请求，并发送成功信息给页面
        return "redirect:/admin/tags";
    }

    /*
     * 删除数据
     * */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message", "删除成功!!!");
        return "redirect:/admin/tags";
    }

}
