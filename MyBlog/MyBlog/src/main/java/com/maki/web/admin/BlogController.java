package com.maki.web.admin;

import com.maki.po.Blog;
import com.maki.po.Type;
import com.maki.po.User;
import com.maki.service.BlogService;
import com.maki.service.TagService;
import com.maki.service.TypeService;
import com.maki.vo.BlogQuery;
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

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    /*
     * 规定：每一页展示3条数据，按照updateTime降序排序
     * 返回的page对象中，就会按照pageable对象中的规定，将查询数据库中的信息保存在content中
     * */
    public String toBlogs(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                  Pageable pageable, Model model, BlogQuery blog) {

        Page<Blog> page = blogService.listBlog(pageable, blog);
        System.out.println(page.getContent());
        //获取所有的分类，用于搜索框中展示
        List<Type> types = typeService.listType();
        model.addAttribute("types", types);
        model.addAttribute("page", page);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                 Pageable pageable, Model model, BlogQuery blog) {

        Page<Blog> page = blogService.listBlog(pageable, blog);
        model.addAttribute("page", page);
        /*
         * 返回admin/blogs页面中的 blogList片段
         * 这样就不会重新刷新整个页面，搜索框中的数据不会被刷新，只会更新显示blog的table标签
         * */
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String toBlogsReleaseByInsert(Model model) {
        //  将数据库中的所有的分类，标签，回显到选择框中
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        return "admin/blogs-release";
    }

    /*
     * 更新页与新增页，共用页面
     * 通过有无blog类型传递，来动态显示新增or修改
     * */
    @GetMapping("/blogs/{id}/input")
    public String toBlogsReleaseByUpdate(@PathVariable Long id, Model model) {
        //将数据库中的所有的分类，标签，回显到选择框中
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());

        //将要更新的数据，回显到输入框中
        Blog blog = blogService.getBlog(id);
        //因为tagIds不被保存在数据库中，所以要初始化该值
        blog.initTagIds();
        model.addAttribute("blog", blog);
        return "admin/blogs-release";
    }

    /*
     * 获取新增博客页或者修改页提交上来的数据，保存至数据库
     * 保存时，根据blog对象中有无ID，来判断是新增or修改
     * 设置该blog对象的user属性（该blog是哪一个用户发表的），从session中获取
     * */
    @PostMapping("/blogs/release")
    public String blogsRelease(Blog blog, HttpSession session, RedirectAttributes redirectAttributes) {
        //设置该blog对象的user属性
        blog.setUser((User) session.getAttribute("user"));

        //设置该blog对象的type属性（该type对象是通过提交博客时，将分类的id也提交过来，请求参数自动封装到blog.type.id中）
        //简而言之：提交博客时，只是将该博客所属的分类的id提交，通过该分类id查找到一个type对象，再放到blog对象中
        blog.setType(typeService.getType(blog.getType().getId()));

        //设置该blog对象的tag属性（通过页面传上来的标签id，请求参数自动封装到blog.tagIds中）
        blog.setTags(tagService.listTag(blog.getTagIds()));

        //将该blog对象保存至数据库，在保存该blog对象时，需要初始化一些数据
        Blog b = blogService.saveBlog(blog);

        if (b == null) {
            redirectAttributes.addFlashAttribute("errormessage", "操作失败!!!");
        } else{
            redirectAttributes.addFlashAttribute("message", "操作成功!!!");
        }
        return "redirect:/admin/blogs";
    }

    /*
    * 删除数据
    * */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message","删除成功!!!");
        return "redirect:/admin/blogs";
    }
}
