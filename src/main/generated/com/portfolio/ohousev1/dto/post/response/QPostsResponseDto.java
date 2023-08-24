package com.portfolio.ohousev1.dto.post.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.portfolio.ohousev1.dto.post.response.QPostsResponseDto is a Querydsl Projection type for PostsResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostsResponseDto extends ConstructorExpression<PostsResponseDto> {

    private static final long serialVersionUID = 1774757608L;

    public QPostsResponseDto(com.querydsl.core.types.Expression<? extends com.portfolio.ohousev1.entity.Post> posts) {
        super(PostsResponseDto.class, new Class<?>[]{com.portfolio.ohousev1.entity.Post.class}, posts);
    }

}

