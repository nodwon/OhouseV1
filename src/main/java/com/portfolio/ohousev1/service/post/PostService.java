package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.Comment.PostWithCommentDto;
import com.portfolio.ohousev1.dto.post.PostDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.entity.constant.SearchType;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Page<PostDto> searchPosts(SearchType searchType, String searchKeyword, Pageable pageable){
        if(searchKeyword ==null|| searchKeyword.isBlank()){
            return  postRepository.findAll(pageable).map(PostDto::from);
        }
        return switch (searchType){
            case TITLE -> postRepository.findByTitleContaining(searchKeyword, pageable).map(PostDto::from);
            case CONTENT -> postRepository.findByContentContaining(searchKeyword,pageable).map(PostDto::from);
            case NICKNAME -> postRepository.findByMember_NicknameContaining(searchKeyword,pageable).map(PostDto::from);
        };
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
