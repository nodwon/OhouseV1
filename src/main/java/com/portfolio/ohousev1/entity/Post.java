package com.portfolio.ohousev1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post extends AuditingFields{

    @Id
    @Column(name = "post_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_no")
//    private Product productId;
//    @Column(name = "product_no", updatable = false, insertable = false)
//    private Long productNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private Member member;

    @Column
    private String title;

    @Column(name = "content", length = 10000)
    private String content;

    @Column(nullable = false)
    private String imgPath;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private  final Set<PostComment> postComments = new LinkedHashSet<>();

    private Post(Member member, String title, String content) {
        this.member = member; // 이메일 값 설정
        this.title = title;
        this.content = content;
    }

    public static Post of(Member member, String title, String content) {
        return new Post(member, title, content);
    }


}
