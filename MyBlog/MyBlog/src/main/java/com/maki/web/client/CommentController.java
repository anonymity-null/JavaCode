package com.maki.web.client;


import com.maki.po.Comment;
import com.maki.po.User;
import com.maki.service.BlogService;
import com.maki.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /*
     * 根据某一个博客详情页中的博客id
     * 查找该条博客对应的所有的comment对象
     * */
    @GetMapping("/comments/{blogId}")
    public String showCommentsList(@PathVariable("blogId") Long id, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);

        //返回blog页面的commentList片段
        return "blog :: commentList";
    }

    /*
     * 新增或者回复，都由该控制器处理
     * 处理的逻辑在CommentService中判断
     * 若是新增，则父评论的id为-1，该comment的parentComment对象为null
     * 若是回复，则前端传来父评论的id，设置该comment对象时，也设置parentComment属性
     * */
    @PostMapping("/comments")
    public String postComment(Comment comment, HttpSession session) {
        //设置该Comment对象的blog属性
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));

        //判断该评论是否为作者
        User user = (User) session.getAttribute("user");
        if (user != null) {
            //设置头像，用户名，为作者的
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setAdminComment(true);
        }else{
            comment.setAdminComment(false);
            //客户使用静态的头像
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);

        //重定向到/comments/{blogId}
        //blogID已经在隐藏域中定义好了
        return "redirect:/comments/" + blogId;
    }
}
