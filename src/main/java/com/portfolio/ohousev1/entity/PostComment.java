package com.portfolio.ohousev1.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@ToString
@Table(name = "post_comment")
@Entity
@NoArgsConstructor
public class PostComment extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_no")
    private Post post;

    @Setter
    @JoinColumn(name = "email")
    @ManyToOne(optional = false)
    private Member member; // 유저 정보 (ID)

    @Setter
    @Column(name = "parent_comment_id",updatable = false)
    private Long parentCommentId; // 부모 댓글 ID

    @ToString.Exclude
    @OrderBy("createdAt ASC")
    @OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL)
    private Set<PostComment> childComments = new LinkedHashSet<>();
    @Setter @Column(name = "content", length = 500) private String content; // 본문

    private PostComment(Post post,Member member, String content, Long parentCommentId) {
        this.member = member; // 이메일 값 설정
        this.post =post;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }
    public static PostComment of(Post post, Member member, String content){
        return new PostComment(post,member,content, null);
    }
    public void addChildComment(PostComment child){
        child.setParentCommentId(this.getId());
        this.getChildComments().add(child);
    }

}
