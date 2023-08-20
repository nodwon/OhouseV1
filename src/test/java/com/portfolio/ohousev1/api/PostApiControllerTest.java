package com.portfolio.ohousev1.api;

import com.portfolio.ohousev1.controller.PostApiController;
import com.portfolio.ohousev1.service.PaginationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(PostApiController.class)
class PostApiControllerTest {

    @Autowired
    private final MockMvc mvc;

    @MockBean // 테스트에서 배제하기위해 입출력을 보기위해
    private PostApiController postApiController; //필드 주입
    @MockBean private PaginationService paginationService;
    
    PostApiControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    public void newPost() throws Exception {
        //when then
        mvc.perform(get("/posts/form"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("posts/form/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

    }
}