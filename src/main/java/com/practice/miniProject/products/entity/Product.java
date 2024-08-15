package com.practice.miniProject.products.entity;

import com.practice.miniProject.audit.Auditable;
import com.practice.miniProject.cart.entity.Cart;
import com.practice.miniProject.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private boolean soldOut = false;

    @OneToMany(mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();
}
