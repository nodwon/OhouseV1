package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.Comment.PostCommentDto;
import com.portfolio.ohousev1.dto.Comment.PostWithCommentDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.entity.PostComment;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.PostCommentRepository;
import com.portfolio.ohousev1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostCommentService {
    private  final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private  final MemberRepository memberRepository;

    public List<PostCommentDto> searchPostComments(Long post_no){
        return postCommentRepository.findByPost_Id(post_no)
                .stream()
                .map(PostCommentDto::from).toList();
    }
    @Transactional
    public  void savePostComment(PostCommentDto dto){
        try {
            Post post = postRepository.getReferenceById(dto.post_no());
            Member member = memberRepository.getReferenceById(dto.memberDto().email());
            PostComment postComment = dto.toEntity(post, member);
            if (dto.parentCommentId() != null) {
                PostComment parentComment = postCommentRepository.getReferenceById(dto.parentCommentId());
                parentComment.addChildComment(postComment);
            } else {
                postCommentRepository.save(postComment);
            }
        }catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패. 댓글 작성에 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }
    public void deletePostComment(Long postCommentId, String email){
        postCommentRepository.deleteByIdAndMember_Email(postCommentId, email);
    }


}
