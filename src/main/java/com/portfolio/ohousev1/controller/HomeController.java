package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.post.response.PostsResponse;
import com.portfolio.ohousev1.entity.constant.SearchType;
import com.portfolio.ohousev1.service.post.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Stream;

@Controller
@Slf4j
public class HomeController {

    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "fragments/main";
    }
    @GetMapping("/")
    public  String PostsList(ModelMap map ,Pageable pageable){
        Page<PostsResponse> posts = postService.AllPost(pageable).map(PostsResponse::from);

        map.addAttribute("Posts", posts);
        return "fragments/main";
    }
    @GetMapping("/postAllList")
    public  String PostsALLList(ModelMap map ,Pageable pageable){
        Page<PostsResponse> posts = postService.AllPost(pageable).map(PostsResponse::from);

        map.addAttribute("Posts", posts);
        return "posts/PostList";
    }
    @GetMapping("/shop")
    public  String ShopALLList(ModelMap map ,Pageable pageable){
        Page<PostsResponse> posts = postService.AllPost(pageable).map(PostsResponse::from);

        map.addAttribute("Posts", posts);
        return "shop/ShopList";
    }
    @GetMapping("/login")
    public String login() {
        log.info("login controller");
        return "user/login_form";
    }

    @GetMapping("/signup")
    public String signup() {
        log.info("signup controller");
        return "user/sign_up";
    }


}
