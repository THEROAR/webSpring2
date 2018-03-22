package com.example;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 主要controller代码
 */
@Controller
public class UserController {

    private Logger logger= Logger.getLogger(this.getClass());

    @Autowired
    private User user;

    @Autowired
    @Qualifier("test1")
    private TestInterface testT;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                              Model model) throws Exception {
        if (username.equals("admin") && password.equals("admin")) {
            model.addAttribute("username", username);
            testT.getReflectField();
            TestInterface testInterface = (TestInterface)SpringContextHelper.getBean("test1");
            System.out.println(testInterface);
            /*if (username.equals("admin")) {
                throw new Exception("test Exception");
            }*/
            logger.error("用户:" + username);
            return "ok.jsp";
        } else {
            model.addAttribute("username", username);
            return "no.jsp";
        }

    }


}
