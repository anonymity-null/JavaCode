package com.maki.web.client;

import com.maki.po.Blog;
import com.maki.po.Type;
import com.maki.service.BlogService;
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

import java.util.List;

/*
 * 注意：不能有多个相同类名的controller
 * */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String typeShow(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model, @PathVariable Long id) {
        //查询所有的分类
        List<Type> types = typeService.listTypeTop(10000);
        //若是从导航栏点击过来的
        if (id == -1) {
            //id设置为查询到的第一个type对象(降序排序)的id
            id = types.get(0).getId();
        }
        //设置一个Blog的查询条件对象
        BlogQuery bq = new BlogQuery();
        bq.setTypeId(id);
        //根据查询条件，分页查询
        Page<Blog> page = blogService.listBlog(pageable, bq);

        model.addAttribute("page", page);
        //将当前分类的id传回，用于区分选中与未选中状态
        model.addAttribute("activeTypeId",id);
        model.addAttribute("types",types);
        return "types";
    }
}
