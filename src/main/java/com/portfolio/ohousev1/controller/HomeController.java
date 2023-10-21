package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.post.response.PostsResponse;
import com.portfolio.ohousev1.service.PaginationService;
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

@Controller
@Slf4j
public class HomeController {

    private final PostService postService;
    private final PaginationService paginationService;

    public HomeController(PostService postService, PaginationService paginationService) {
        this.postService = postService;
        this.paginationService = paginationService;
    }

    @RequestMapping("/")
    public String home() {

        log.info("home controller");
        return "fragments/main";
    }
    @GetMapping("/") //상품일부분가져오기
    public  String PostsList(ModelMap map ,Pageable pageable){
        Page<PostsResponse> posts = postService.AllPost(pageable).map(PostsResponse::from);
        map.addAttribute("Posts", posts);
        return "fragments/main";
    }
    @GetMapping("/postAllList")  //게시글 전체 가져오기
    public  String PostsALLList(ModelMap map ,Pageable pageable){
        Page<PostsResponse> posts = postService.AllPost(pageable).map(PostsResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), posts.getTotalPages());
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("Posts", posts);
        return "posts/PostList";
    }
    @GetMapping("/shop") // 임시 쇼핑몰 상품 가져오기
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
    @GetMapping("/search")
    public String MyPage(
            @RequestParam(required = false) String searchKeyword,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ) {
        // 여기서 searchKeyword를 이용하여 검색을 수행합니다.
        Page<PostsResponse> posts = postService.searchPosts(searchKeyword, pageable).map(PostsResponse::from);

        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), posts.getTotalPages());

        if (posts.isEmpty()) {
            map.addAttribute("noResults", "찾는 검색 결과가 없습니다.");
        }

        map.addAttribute("Posts", posts);
        map.addAttribute("paginationBarNumbers", barNumbers);

        return "posts/SearchList";
    }








}
