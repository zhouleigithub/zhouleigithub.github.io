package com.blogs.zhoulei.web.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
@RequestMapping("/admin")
public class BlogsController {

    @GetMapping("/blogs")
    public String blogs(){
        return "/admin/blogs";
    }
}
