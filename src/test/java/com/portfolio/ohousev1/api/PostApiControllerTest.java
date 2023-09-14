package com.portfolio.ohousev1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.ohousev1.config.SecurityConfig;
import com.portfolio.ohousev1.controller.PostApiController;
import com.portfolio.ohousev1.dto.post.request.PostsRequest;
import com.portfolio.ohousev1.service.PaginationService;
import com.portfolio.ohousev1.service.post.PostService;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Autowired
    private UserDetailsService userDetailsService;
    @Test
    @PreAuthorize("isAuthenticated()")
    public void newPost() throws Exception {
        // Given
        PostsRequest request = new PostsRequest("title", "content");
        when(postService.savePost(any())).thenReturn(1L); // Mocking postService behavior

        // When
        MvcResult result = mvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        String responseBody = result.getResponse().getContentAsString();
        Long responseId = Long.parseLong(responseBody);
        assertThat(responseId).isGreaterThan(0L);
    }
    @Test
    @WithMockUser(roles = "USER")
    @PreAuthorize("hasRole('ROLE_USER')") // ROLE_USER 권한이 필요한 경우
    public void newPost2() throws Exception {
// Given
        PostsRequest request = new PostsRequest("title", "content");
        when(postService.savePost(any())).thenReturn(1L); // Mocking postService behavior

// When
        MvcResult result = (MvcResult) mvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" + " \"id\": 1,\n" + " \"title\": \"title\",\n" + " \"content\": \"content\",\n" + " \"epam\": \"epam\"\n" + "}"));
// Then
        String responseBody = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(responseBody);
        assertThat(jsonObject.get("id")).isEqualTo(1L);
        assertThat(jsonObject.get("title")).isEqualTo("title");
        assertThat(jsonObject.get("content")).isEqualTo("content");
        assertThat(jsonObject.get("epam")).isEqualTo("epam");
    }
}


//    mvc.perform(get("/posts"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("/posts"));
