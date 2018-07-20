package com.zyq.controller;

import com.zyq.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyq
 * @date 2018/7/20 11:15
 */
@Controller
@RequestMapping("/user")
public class HelloController {

    /*前--->后(地址栏发起请求)*/
    @GetMapping("/hello")
    public String add() {
        System.out.println("add....");
        return "hello";
    }

    /*前--->后（地址栏发起请求）*/
    /*获得id的值,多个参数，单个参数*/
    /*其中默认的页数为第一页 p=1*/
    @GetMapping("/hello/{type:v-\\d+}/{id:\\d+}")
    public String add(
            @RequestParam(defaultValue = "1") Integer p,
            @PathVariable String type,
            @PathVariable Integer id) {
        System.out.println("p:" + p);
        System.out.println("id:" + id);
        System.out.println("type" + type);
        return "hello";
    }

    /*后--->前台进行传值*/
    /*这里的路径不能是hello，和第一个路径重复了，则抛500*/
    @GetMapping("/map")
    public String save(Model model) {
        System.out.println("......");
        model.addAttribute("key", 123);
        return "hello";
    }

    /*第二种进行传值的方式ModelAndView*/
    @GetMapping("/hi")
    public ModelAndView map() {
        System.out.println(".....");
        ModelAndView modelAndView = new ModelAndView("hello");
        System.out.println("zzz");
        modelAndView.addObject("key1", 456);
        return modelAndView;
    }

    /*后--->后*/
    /*重定向跳转-->请求转发*/
    @GetMapping("/siri")
    public String save1() {
        return "redirect:/user/hi";
    }

    /*表单传值 前-->后 post提交*/
    /*单个的传值，以及传入的是对象*/
    @PostMapping("/zyq")
    /*public String display(String username,String password){
        System.out.println("...");
        System.out.println("username:" + username);
        System.out.println("password" + password);
        return "hello";
        }*/
    public String display(User user) {
        System.out.println("...");
        System.out.println("username:" + user.getUsername());
        System.out.println("password:" + user.getPassword());
        return "hello";
    }

    /*解决表单post提交的中文乱码的问题 --> Spring*/

    /*解决get提交，url？传值的问题，以及乱码 uriencoding*/
    /*前--》后传值*/
    @GetMapping("/get")
    public String SetValue(
            @RequestParam(name = "username") String name) {
        System.out.println("....");
        System.out.println("name:" + name);
        return "hello";
    }


    /*后--》前 传json,返回对象，进行配置maven*/
    @GetMapping("/user.json")
    @ResponseBody
   public User UserJson() {
        User user = new User();
        user.setUsername("amy");
        user.setPassword("1234");
        return user;
        }

    @GetMapping("/list.json")
    @ResponseBody
    public List<User> ListJson(){
      /*  User user1 = new User();
        user1.setUsername("tom");
        user1.setPassword("123");

        User user2 = new User();
        user2.setUsername("mike");
        user2.setPassword("456");

        List<User> userList = Arrays.asList(user1,user2);*/

       List<User> userList = Arrays.asList(
               /*对象之间，逗号*/
               new User("tom","123"),
               new User("mike","456"),
               new User("apple","789")
       );
              return userList;
    }

    /*后-->前*/
    /*向前台传字符串,并且解决中文乱码*/
    @GetMapping(value="/string",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String StingUser(){
        System.out.println("...");
       /* return "success";*/
        return "保存成功";
    }

    /*向前台传map集合*/
    @GetMapping("/map.json")
    @ResponseBody
    public Map<String,Object> SaveMap(){
        Map<String,Object> map = new HashMap<>();
      /*  User user = new User();*/
        map.put("tom",123);
        return map;
    }

    /*传List的Map集合*/
    @GetMapping("listmap")
    @ResponseBody
    public List<Map<String,Object>> MapOfList(){
        Map<String,Object> map1 = new HashMap<>();
        map1.put("key",78);
        map1.put("key1",90);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("key1",90);
        map2.put("key2",90);
        List<Map<String,Object>> mapList = Arrays.asList(map1,map2);

        return mapList;
    }


}
