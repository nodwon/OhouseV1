package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.PostRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

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


}
