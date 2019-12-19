package com.majiang.commounity.controller;


import com.majiang.commounity.mapper.QuestionMapper;
import com.majiang.commounity.mapper.UserMaper;
import com.majiang.commounity.pojo.Question;
import com.majiang.commounity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PulishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMaper userMaper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model){

        if(question.getTitle() == null || question.getTitle() == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(question.getDescription() == null || question.getDescription() == ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(question.getTag() == null || question.getTag() == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null && cookies.length != 0 ) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMaper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
//        8834509
//                YY4xH2
        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionMapper.create(question);
        return  "redirect:/";
    }

}
