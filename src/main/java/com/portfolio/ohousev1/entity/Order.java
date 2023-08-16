package com.portfolio.ohousev1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @JoinColumn(name = "member_no", insertable = false, updatable = false)
    private Member member;
    @Column(name = "member_no")
    private Long memberNo;




    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL) // persist를 전파함
    private List<OrderProduct> orderProducts = new ArrayList<>();

}
