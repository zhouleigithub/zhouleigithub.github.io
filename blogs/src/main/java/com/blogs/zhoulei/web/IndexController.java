package com.blogs.zhoulei.web;

import com.blogs.zhoulei.NotFoundExceptions;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
   @GetMapping("/")
    public String index()  {
        return "admin/login";
    }

}
