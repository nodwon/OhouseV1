package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.post.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("비지니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostService postService;

    @DisplayName("게시글 생성")
    @Test
    public void testCreatePost() throws Exception {
        //given
        Member member = Member.builder()
                .email("test@email.com")
                .password("password")
                .nickname("testuser")
                .name("testname")
                .build();
        memberRepository.save(member);
        PostsSaveRequestDto requestDto = createsaveto("sdfa","sd");

        // When
        Long postId = postService.CreatePost(requestDto);

        // Then
        Post savedPost = postRepository.findById(postId).orElse(null);
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo("Test Post");
        assertThat(savedPost.getContent()).isEqualTo("This is a test post.");
        assertThat(savedPost.getMember()).isEqualTo(member);
    }
    private PostsSaveRequestDto createsaveto(String title, String content) {
        return new PostsSaveRequestDto(1L,1L,"email","title","test");

    }

}