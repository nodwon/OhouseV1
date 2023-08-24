package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.post.PostDto;
import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.exception.NotFoundException;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.post.PostRepository;
import com.portfolio.ohousev1.service.PaginationService;
import jakarta.persistence.EntityNotFoundException;
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
    public PostDto getPost(Long postId) {
        return postRepository.findById(postId)
                .map(PostDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - postId: " + postId));
    }

    @Transactional
    public Long CreatePost(PostsSaveRequestDto request){ //게시물 생성
        Member writer = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(Member.class, "member not found"));
        Post post = Post.builder()
                .member(writer)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }
    @Transactional
    public long updatePost(Long postId, PostsUpdateRequestDto dto){

        Post posts = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당게시글은 없습니다. id"+postId));

        posts.update(dto.getTitle(), dto.getContent());
        postRepository.save(posts);
        return posts.getId();

    }
    @Transactional
    public void deletePost(Long postNo) {
        postRepository.getReferenceById(postNo);
        postRepository.deleteById(postNo);
    }

    public long getPostCount(){
        return  postRepository.count();
    }
}
