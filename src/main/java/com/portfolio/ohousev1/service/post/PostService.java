package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.Comment.PostWithCommentDto;
import com.portfolio.ohousev1.dto.post.PostDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    //조회수
    @Transactional
    public Integer updateView(Long post_no) {return postRepository.updateView(post_no);}
    // 검색
    @Transactional
    public Page<PostDto> searchPosts(String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return postRepository.findAll(pageable).map(PostDto::from);
        }

        Page<PostDto> titleResults = postRepository.findByTitleContaining(searchKeyword, pageable).map(PostDto::from);
        Page<PostDto> contentResults = postRepository.findByContentContaining(searchKeyword, pageable).map(PostDto::from);
        Page<PostDto> nicknameResults = postRepository.findByMember_NicknameContaining(searchKeyword, pageable).map(PostDto::from);

        // 이제 이 세 가지 결과를 병합할 차례입니다.
        // Page 인터페이스를 직접 조작할 수 없으므로, 리스트로 변환하여 병합합니다.
        List<PostDto> mergedResults = Stream.concat(
                        Stream.concat(titleResults.getContent().stream(), contentResults.getContent().stream()),
                        nicknameResults.getContent().stream()
                )
                .distinct() // 중복 항목 제거
                .collect(Collectors.toList());

        // 마지막으로, 페이지로 변환하여 반환합니다.
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), mergedResults.size());
        return new PageImpl<>(mergedResults.subList(start, end), pageable, mergedResults.size());
    }


    public Page<PostDto> AllPost(Pageable pageable){
        return postRepository.findAll(pageable).map(PostDto::from);
    }

    public PostWithCommentDto getPostWithComments(Long postId){
        return postRepository.findById(postId)
                .map(PostWithCommentDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 -postId" + postId));
    }

    public PostDto getPost(Long postId) {
        return postRepository.findById(postId)
                .map(PostDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - postId: " + postId));
    }

    @Transactional
    public Long savePost(PostDto dto) { //게시물 생성
        Member member = memberRepository.getReferenceById(dto.memberDto().email());
        Post post = dto.toEntity(member);

        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public long updatePost(Long postId, PostDto dto) {
            Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당게시글은 없습니다. id" + postId));
            Member member = memberRepository.getReferenceById(dto.memberDto().email());
            if(post.getMember().equals(member)){
                if (dto.title() != null){ post.setTitle(dto.title());}
                if (dto.content() != null){post.setContent(dto.content());}
                postRepository.flush();
            }
            postRepository.save(post);


            return post.getId();
    }

    @Transactional
    public void deletePost(Long postNo, String email) {
        postRepository.getReferenceById(postNo);

        postRepository.deleteByIdAndMember_email(postNo, email);
    }

    public long getPostCount() {
        return postRepository.count();
    }

}
