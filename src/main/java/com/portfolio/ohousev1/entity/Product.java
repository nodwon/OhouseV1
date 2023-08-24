package com.portfolio.ohousev1.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="product_no")
    @Setter
    private Long id;

    @Column
    private Long price;

    @Column
    private Long stock;

    @Column
    private String title;

    @Column(name = "description")
    private String description;


    @Column(name = "created_at")
    @Setter
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String imgPath;

    @Builder
    public Product(Long id, Long price, Long stock, String title, String imgPath, String description){
        this.id=id;
        this.price =price;
        this.stock = stock;
        this.title = title;
        this.imgPath =imgPath;
        this.description =description;
    }
}
