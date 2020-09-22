package com.maki.web.client;

import com.maki.po.Blog;
import com.maki.po.Tag;
import com.maki.po.Type;
import com.maki.service.BlogService;
import com.maki.service.TagService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String toIndex(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                  Pageable pageable, Model model) {
        //分页的形式，查询所有的博客
        Page<Blog> page = blogService.listBlog(pageable);
        //只查询6条分类，通过分类中的博客数量进行降序排序
        List<Type> types = typeService.listTypeTop(6);
        //只查询10条标签，通过标签中的博客数量进行降序排序
        List<Tag> tags = tagService.listTagTop(10);
        //只查询5条被推荐的博客，通过博客中的更新时间进行降序排序
        List<Blog> recommendBlog = blogService.listRecommendBlogTop(5);

        model.addAttribute("page", page);
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
        model.addAttribute("recommendBlogs", recommendBlog);
        return "index";
    }

    /*
     * 获取搜索框提交的数据，进行全局搜索；分页的形式
     * */
    @GetMapping("/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                 Pageable pageable, @RequestParam String query, Model model) {
        Page<Blog> page = blogService.listBlog("%"+query+"%", pageable);
        model.addAttribute("page",page);
        model.addAttribute("query",query);
        return "search";
    }

    /*
    * 博客详情页
    * */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlogConvertContent(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

}
