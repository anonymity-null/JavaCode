package com.maki.web.admin;

import com.maki.myexception.PageNotFoundException;
import com.maki.po.Type;
import com.maki.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    /*
     * 实现分页效果，需要传递Pageable对象，该对象中
     * 指定每页最大记录数，以id排序，降序的方式
     * 也可以指定当前页数(number)
     * */
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        //查询所有的分类，以分页的形式
        Page<Type> types = typeService.listType(pageable);
        //将该Page对象传递给页面显示
        //Page中存放的其实就是一个个json，包括：数据内容、每一页的最大条数，一共几页，排序方式......
        model.addAttribute("page", types);
        return "admin/types";
    }

    /*
     * 新增分类页
     * */
    @GetMapping("/types/input")
    public String toTypeRelease() {
        return "admin/types-release";
    }

    /*
     * 修改分类页
     * */
    @GetMapping("/types/{id}/input")
    public String toTypeUpdate(@PathVariable Long id, Model model) {
        Type t = typeService.getType(id);
        model.addAttribute("type", t);
        return "admin/types-release";
    }

    /*
     * 获取新增分类页提交的数据
     * 校验，若数据库中有相同纪录，不保存
     * 否则，保存到数据库中，并重新发送查询所有数据请求
     * */
    @PostMapping("/types/release")
    public String typeRelease(Type type, RedirectAttributes redirectAttributes) {
        Type t = typeService.getTypeByName(type.getName());
        //表示数据库中已有相同的分类
        if (t != null) {
            //不保存数据，并提示错误信息
            redirectAttributes.addFlashAttribute("errormessage", "添加失败，已有重复的分类!!!");
        } else if (type.getName() == null) {
            redirectAttributes.addFlashAttribute("errormessage", "添加失败!!!");
        } else if (type.getName().equalsIgnoreCase("null")) {
            redirectAttributes.addFlashAttribute("errormessage", "添加失败!!!");
        } else {
            //校验成功，添加一条分类
            Type success = typeService.saveType(type);
            //重新发送查询数据请求，并发送成功信息给页面
            redirectAttributes.addFlashAttribute("message", "添加成功!!!");
        }
        return "redirect:/admin/types";
    }

    /*
     * 获取修改分类页提交的数据
     * 校验（唯一且不为空）
     * */
    @PostMapping("/types/update")
    public String typeUpdate(Type type, RedirectAttributes redirectAttributes) {
        Type t = typeService.getTypeByName(type.getName());
        //修改，若修改后的数据和原数据相同，提示错误信息
        if (t != null) {
            //不修改数据，并提示错误信息
            redirectAttributes.addFlashAttribute("errormessage", "更新失败，已有重复的分类!!!");
        } else if (type.getName() == null) {
            redirectAttributes.addFlashAttribute("errormessage", "更新失败!!!");
        } else if (type.getName().equalsIgnoreCase("null")) {
            redirectAttributes.addFlashAttribute("errormessage", "更新失败!!!");
        } else {
            //根据id来更新该条记录
            Type success = typeService.updateType(type.getId(), type);
            //重新发送查询数据请求，并发送成功信息给页面
            redirectAttributes.addFlashAttribute("message", "更新成功!!!");
        }
        return "redirect:/admin/types";
    }

    /*
    * 删除数据
    * */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message","删除成功!!!");
        return "redirect:/admin/types";
    }
}
