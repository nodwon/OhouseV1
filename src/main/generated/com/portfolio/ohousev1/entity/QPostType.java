package com.portfolio.ohousev1.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostType is a Querydsl query type for PostType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostType extends EntityPathBase<PostType> {

    private static final long serialVersionUID = 722846614L;

    public static final QPostType postType = new QPostType("postType");

    public final NumberPath<Integer> postTypeId = createNumber("postTypeId", Integer.class);

    public final StringPath type = createString("type");

    public QPostType(String variable) {
        super(PostType.class, forVariable(variable));
    }

    public QPostType(Path<? extends PostType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostType(PathMetadata metadata) {
        super(PostType.class, metadata);
    }

}

