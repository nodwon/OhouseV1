package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.Comment.PostCommentDto;
import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.entity.PostComment;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.PostCommentRepository;
import com.portfolio.ohousev1.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import static com.portfolio.ohousev1.entity.QPost.post;
import static org.assertj.core.api.BDDAssertions.tuple;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("비지니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class PostCommentServiceTest {

    @InjectMocks
    private PostCommentService postCommentService;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostCommentRepository postCommentRepository;
    @Mock
    private MemberRepository memberRepository;

    @DisplayName("게시글 조회하면 댓글리스트")
    @Test
    public void commentList () throws Exception {
        //given
        Long postno = 1L;
        PostComment ParentComment = createPostComment(1L, "parent content");
        PostComment ChildComment = createPostComment(2L,"child content");
        BDDMockito.given(postCommentRepository.findByPost_Id(postno)).willReturn(List.of(
                ParentComment,
                ChildComment
        ));
        //when
        List<PostCommentDto> actual = postCommentService.searchPostComments(postno);
        //then
        assertThat(actual).hasSize(2);
        assertThat(actual)
                .extracting("id", "post_no", "parentCommentId", "content")
                .containsExactlyInAnyOrder(
                        tuple(1L, 1L, 1L, "parent content"),
                        tuple(2L, 1L, 1L, "child content")
                );
        then(postCommentRepository).should().findByPost_Id(postno);
    }
    private PostComment createPostComment(Long id, String content) {
        PostComment postComment = PostComment.of(
                createPost(),
                createMember(),
                content
        );
        ReflectionTestUtils.setField(postComment, "id", id);

        return postComment;
    }
    @DisplayName("댓글정보를 입력하면, 댓글을 저장한다.")
    @Test
    public void savecomment() throws Exception {
        //given
        PostCommentDto dto = createPostCommentDto("댓글");
        BDDMockito.given(postRepository.getReferenceById(dto.post_no())).willReturn(createPost());
        BDDMockito.given(memberRepository.getReferenceById(dto.memberDto().email())).willReturn(createMember());
        BDDMockito.given(postCommentRepository.save(any(PostComment.class))).willReturn(null);
        //when
        postCommentService.savePostComment(dto);
        //then
        then(postRepository).should().getReferenceById(dto.post_no());
        then(memberRepository).should().getReferenceById(dto.memberDto().email());
        then(postCommentRepository).should().getReferenceById(dto.post_no());
        then(postCommentRepository).should().save(any(PostComment.class));

    }

    private PostCommentDto createPostCommentDto(String content){
        return createPostCommentDto(null,content);
    }private PostCommentDto createPostCommentDto(Long parentCommendId,String content){
        return createPostCommentDto(1L,null,content);
    }
    private Member createMember() {
        return Member.of(
                "test@email.com",
                "password",
                "test-name",
                "test-nickname",
                null
        );
    }
    private MemberDto createMemberDto() {
        return MemberDto.of(
                "test@email.com","asdf","testname","nick",
                LocalDate.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
    private PostCommentDto createPostCommentDto(Long id, Long parentCommentId, String content) {
        return PostCommentDto.of(
                id,
                createMemberDto(),
                    1L,
                content,
                parentCommentId,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private Post createPost() {
        Post article = Post.of(
                createMember(),
                "title",
                "content"
        );
        ReflectionTestUtils.setField(article, "id", 1L);
        return article;
    }
}