package com.portfolio.ohousev1.repository;

import com.portfolio.ohousev1.entity.PostComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("JPA  테스트")
@SpringBootTest
class PostCommentRepositoryTest {

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Test
    public void testGetReferenceById() {
        PostComment postComment = postCommentRepository.getReferenceById(1L);

        // 기대 결과 확인
        assertNotNull(postComment); // 반환된 값이 null이 아닌지 확인
        assertEquals(1L, postComment.getId()); // ID가 예상한 값과 일치하는지 확인
        // 기타 필요한 확인 작업 수행
    }
}