package com.portfolio.ohousev1.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post extends AuditingFields{

    @Id
    @Column(name = "post_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_type_no")
    private PostType postTypeId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    private Product productId;
    @Column(name = "product_no", updatable = false, insertable = false)
    private Long productNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;
    @Column(name = "member_no", updatable = false, insertable = false)
    private Long memberNo;

    @Setter
    @Column
    private String title;

    @Setter
    @Column(name = "content", length = 10000)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private  final Set<PostComment> postComments = new LinkedHashSet<>();


    @Builder
    public Post(PostType postTypeId, Member member,String title,
                String content) {
        this.postTypeId = postTypeId;
        this.member = member;
        this.title = title;
        this.content = content;

    }


}
