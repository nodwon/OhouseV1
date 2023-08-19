package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.entity.PostType;
import com.portfolio.ohousev1.exception.NotFoundException;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.post.PostRepository;
import com.portfolio.ohousev1.repository.post.PostTypePepository;
import com.portfolio.ohousev1.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostTypePepository postTypePepository;
    private final MemberRepository memberRepository;
    private final PaginationService paginationService;

    @Transactional
    public Long CreatePost(PostsSaveRequestDto request){ //게시물 생성
        PostType postType = postTypePepository.findById(request.getPostTypeNo())
                .orElseThrow(() -> new NotFoundException(String.valueOf(PostType.class), "post not foound"));
        Member writer = memberRepository.findById(request.getMemberNo())
                .orElseThrow(() -> new NotFoundException(Member.class, "member not found"));


        Post post = Post.builder()
                .postTypeId(postType)
                .member(writer)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }
    public void updatePost(Long postId, PostsUpdateRequestDto dto){

        Post posts = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당게시글은 없습니다. id"+postId));
        posts.setTitle(dto.getTitle());
        posts.setContent(dto.getContent());

           postRepository.save(posts);
    }
    public void deletePost(Long postId, String email){
        postRepository.getReferenceById(postId);
        postRepository.deleteByIdAndUserAccount_UserId(postId, email);
    }
}
