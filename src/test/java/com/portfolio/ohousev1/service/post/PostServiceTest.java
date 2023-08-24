package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.post.PostRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PostRepository postRepository;

    private EntityManager em;

    @Test
    @Transactional
    public void testCreatePost() {
        // Given
        String email = "example@example.com";
        String title = "Test Title";
        String content = "Test Content";

        PostsSaveRequestDto requestDto = new PostsSaveRequestDto("email", "title", "content", "paht");
        Member writer = new Member("re", "123", "nick", "name", LocalDate.now(), "sda");

      //  em.persist(writer); // Member 엔티티를 영속성 컨텍스트에 저장

//        Post savePost = Post.builder()
//                .member(writer)
//                .title(title)
//                .content(content)
//                .build();
//        em.persist(savePost); // Post 엔티티를 영속성 컨텍스트에 저장

        // When
        long result = postService.CreatePost(requestDto);

        // Then
        assertThat(result).isNotNull();

        // 여기에서 추가적인 검증을 수행하면 됩니다.
    }
}
