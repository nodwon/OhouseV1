package com.portfolio.ohousev1.repository;

import com.portfolio.ohousev1.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findByPost_Id(Long post_no);

    void deleteByIdAndMember_Email(Long commentId, String email);
}
