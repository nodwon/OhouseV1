package com.portfolio.ohousev1.service.post;

import com.portfolio.ohousev1.dto.post.PostDto;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.repository.MemberRepository;
import com.portfolio.ohousev1.repository.PostRepository;
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

//    @Transactional
//    public Page<PostDto> searchArticles(Pageable pageable) {
//    }
}
