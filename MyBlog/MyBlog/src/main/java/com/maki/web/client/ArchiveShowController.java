package com.maki.web.client;

import com.maki.po.Blog;
import com.maki.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        //key表示所有的年份，value表示该年份下所有的博客
        Map<String, List<Blog>> map = blogService.archiveBlog();
        Long countBlog = blogService.countBlog();

        model.addAttribute("archiveMap",map);
        model.addAttribute("countBlog",countBlog);
        return "archives";
    }
}
