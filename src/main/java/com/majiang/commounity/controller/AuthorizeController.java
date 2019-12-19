package com.majiang.commounity.controller;

import com.majiang.commounity.dto.AccessTokenDTO;
import com.majiang.commounity.dto.GithubUser;
import com.majiang.commounity.mapper.UserMaper;
import com.majiang.commounity.pojo.User;
import com.majiang.commounity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.sercret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redrectUri;

    @Autowired
    private UserMaper userMaper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String  code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        //eb0810783e0b5929cfda84984dd4456293d4a47b  access_token Id

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redrectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName() + "    ============ User  ");

        if(githubUser != null){
            //登录成功 ，写 Cookie
            request.getSession().setAttribute("user",githubUser);
            //实例化对象
            User user = new User();
            user.setName(githubUser.getName());
            //  UUID生成 token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            user.setAvatar_url(githubUser.getAvatar_url());
            //存储到数据库中
            userMaper.insert(user);
            response.addCookie(new Cookie("token",token));

            return  "redirect:/";

        }else{
            //登录失败
            return  "redirect:/";
        }
    }

}
