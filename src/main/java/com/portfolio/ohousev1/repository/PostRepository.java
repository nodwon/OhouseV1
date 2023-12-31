package com.portfolio.ohousev1.repository;

import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.entity.QPost;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends
        JpaRepository<Post, Long>,
        QuerydslPredicateExecutor<Post>,
        QuerydslBinderCustomizer<QPost> // querydsl 제작
{
    Page<Post> findByTitleContaining(String title, Pageable pageable);
    Page<Post> findByContentContaining(String content, Pageable pageable);
    Page<Post> findByMember_NicknameContaining(String nickname, Pageable pageable);
    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.id = :post_no")
    Integer updateView(@Param("post_no") Long post_no);

    void deleteByIdAndMember_email(Long post_no, String email);
    @Override
    default void customize(QuerydslBindings bindings, QPost root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.createdAt, root.member);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); //대소문자 구분 없이 찾기
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); //대소문자 구분 없이 찾기
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    }
}
