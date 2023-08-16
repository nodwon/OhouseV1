package com.portfolio.ohousev1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
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
    private Long title;

    @Column(name = "description")
    private String Description;


    @Column(name = "created_at")
    @Setter
    private LocalDateTime createdAt;
}
