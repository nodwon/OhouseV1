package com.portfolio.ohousev1.repository;

import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
//@Import(PostRepositoryTest.TestJpaConfig.class)
@SpringBootTest
public class PostRepositoryTest {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostRepositoryTest(@Autowired PostRepository postRepository,
                              @Autowired MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @After
    public void cleanUp(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장및불러오기() throws Exception {
        //given
        String title ="test-title";
        String content ="test-content";
        //when

        //then
        List<Post> postList = postRepository.findAll();
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }
//    @DisplayName("insert 테스트")
//    @Test
//    public void insert() throws Exception {
//        //given
//        long previousCount = postRepository.count();
////        Member member = memberRepository.save(Member.of("newEmail.com","new password","newname","newnickname", LocalDate.now()));
//        //when
////        Post post = Post.of(member, "newtitle", "newContent");
//        postRepository.save(post);
//        //then
//        assertThat(postRepository.count()).isEqualTo(previousCount+1);
//    }
    @DisplayName("DELETE 테스트")
    @Test
    public void Delete() throws Exception {
        //given
        Post post = postRepository.findById(1L).orElseThrow();
        long previous = postRepository.count();
        //when
        postRepository.delete(post);
        //then
        assertThat(postRepository.count()).isEqualTo(previous-1);
    }
}