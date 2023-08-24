package com.portfolio.ohousev1.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="order_no")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email", insertable = false, updatable = false)
    private Member member;
    @Column(name = "member_no")
    private Long memberNo;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL) // persist를 전파함
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING) // 무조건 String
    private  OrderStatus status; // 주문 상태  order cancel

    @Builder
    public Order(Long id, Member member, Long memberNo, OrderStatus status){
        this.id =id;
        this.member = member;
        this.memberNo = memberNo;
        this.status = status;

    }



}
