package xyz.herther.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.herther.service.UserService;

/**
 * 测试页面
 * @Herther
 * 时间2020/7/5
 */
@Controller
public class TestController {
//    注入Service
//    @Autowired
//    private UserService userService;


    @GetMapping("/hello")
    @ResponseBody
    public String _Hello(){
        return "hello word";
    }

//    测试thymeleaf页面
    @GetMapping("/")
    public String testThymeleaf(Model model){
        model.addAttribute("username","Herther");
        return "/login";
    }
    @GetMapping("/index")
    public String Index(){
        return "/index";
    }
    @GetMapping("/add")
    public String addUser(){
        return "/user/AddUser";
    }
    @GetMapping("/update")
    public String updateUser(){
        return "/user/UpdateUser";
    }
    @GetMapping("/tologin")
    public String tologin(){
        return "/login";
    }
    @RequestMapping("/login")
    public String login(String username,String password,Model model) {
        System.out.println("username：" + username + ",password：" + password);
        /**
         * shiro编写认证操作
         */
        //1、获取subject
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //3、执行登录方法
        try {
            System.out.println("用户名："+token.getUsername()+"密码: "+token.getPassword());
            subject.login(token);
            model.addAttribute("username",token.getUsername());
            return "/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","用户名密码错误");
            return "login";
        }

    }
    @GetMapping("/unAuth")
    public String unAuth(){
        return "/unAuth";
    }
}
