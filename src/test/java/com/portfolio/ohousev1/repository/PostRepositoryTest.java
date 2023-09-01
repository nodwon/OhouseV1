package com.portfolio.ohousev1.repository;

import com.portfolio.ohousev1.entity.Post;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void cleanUp(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장및불러오기() throws Exception {
        //given
        String title ="테스트게시글";
        String content ="테스트 본문";
        //when

        //then
        List<Post> postList = postRepository.findAll();
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

}