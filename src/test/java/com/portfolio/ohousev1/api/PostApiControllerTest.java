package com.portfolio.ohousev1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.ohousev1.config.SecurityConfig;
import com.portfolio.ohousev1.controller.PostApiController;
import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.entity.constant.FormStatus;
import com.portfolio.ohousev1.service.PaginationService;
import com.portfolio.ohousev1.service.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(PostApiController.class)
class PostApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;
    @MockBean
    private SecurityConfig securityConfig;

    @MockBean
    private PaginationService paginationService;


    @Test
    public void newPost() throws Exception {
        // Given
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto("title", "content", "epam", "asdf");
        when(postService.CreatePost(any())).thenReturn(1L); // Mocking postService behavior

        // When
        MvcResult result = mvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        String responseBody = result.getResponse().getContentAsString();
        Long responseId = Long.parseLong(responseBody);
        assertThat(responseId).isGreaterThan(0L);
    }
}


//    mvc.perform(get("/posts"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("/posts"));
