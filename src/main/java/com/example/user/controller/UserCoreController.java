package com.example.user.controller;

import com.example.common.controller.BaseController;
import com.example.user.UUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@Scope(value="prototype")
@RequestMapping("user")
public class UserCoreController extends BaseController {

    @Resource
    UUserService userService;

    @RequestMapping(value="index",method= RequestMethod.GET)
    public ModelAndView userIndex(){

        return new ModelAndView("user/index");
    }
}
