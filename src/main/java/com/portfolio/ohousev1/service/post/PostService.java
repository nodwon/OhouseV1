package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.exception.NotFoundException;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.post.PostRepository;
import com.portfolio.ohousev1.service.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PaginationService paginationService;

    @Transactional
    public Long CreatePost(PostsSaveRequestDto request){ //게시물 생성

        Post savedPost = postRepository.save(request.toEntity());
        return savedPost.getId();
    }
    public void updatePost(Long postId, PostsUpdateRequestDto dto){

        Post posts = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당게시글은 없습니다. id"+postId));

        posts.update(dto.getTitle(), dto.getContent());
        postRepository.save(posts);
    }
    public void deletePost(Long postId, String email){
        postRepository.getReferenceById(postId);
        postRepository.deleteByIdAndMember_Email(postId, email);
    }

}
