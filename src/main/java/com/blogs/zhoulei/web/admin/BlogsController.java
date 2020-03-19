package com.blogs.zhoulei.web.admin;

import com.blogs.zhoulei.po.Blog;
import com.blogs.zhoulei.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
@RequestMapping("/admin")
public class BlogsController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size=2,sort = {"updatetime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model){

        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return "/admin/blogs";
    }
}
