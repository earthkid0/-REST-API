package com.practice.miniProject.cart.mapper;

import com.practice.miniProject.cart.dto.CartDto;
import com.practice.miniProject.cart.entity.Cart;
import com.practice.miniProject.members.entity.Member;
import com.practice.miniProject.posts.dto.PostDto;
import com.practice.miniProject.posts.entity.Post;
import com.practice.miniProject.products.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
//    @Mapping(target = "cartId", ignore = true)
//    @Mapping(target = "member.memberId", source = "memberId")
//    @Mapping(target = "product.productId", source = "productId")
//    Cart cartPostToCart(CartDto.PostDto requestBody);

    default Cart cartPostToCart(CartDto.PostDto cartPostDto) {
        Cart cart = new Cart();
        cart.setQuantity(cartPostDto.getQuantity());

        Member member = new Member();
        member.setMemberId(cartPostDto.getMemberId());
        cart.setMember(member);

        Product product = new Product();
        product.setProductId(cartPostDto.getProductId());
        cart.setProduct(product);

        return cart;
    }
//    @Mapping(target = "cartId", source = "cartId")
//    @Mapping(target = "username", source = "member.username")
//    @Mapping(target = "productName", source = "product.productName")
//    @Mapping(target = "price", source = "product.price")
//    @Mapping(target = "quantity", source = "quantity")
//    CartDto.ResponseDto cartToCartResponse(Cart cart);

    default CartDto.ResponseDto cartToCartResponse(Cart cart) {
        CartDto.ResponseDto response = new CartDto.ResponseDto();

        response.setCartId(cart.getCartId());
        response.setMember(cart.getMember());
        response.setProduct(cart.getProduct());
        response.setQuantity(cart.getQuantity());

        return response;
    }
}
