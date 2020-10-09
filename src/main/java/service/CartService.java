package service;

import entity.Cart;
import entity.PageBean;

import java.util.List;

public interface CartService {
    List<Cart> findByUid(int uid);
    void deleteCartByUid(int uid);
    void addNum(Cart cart);
    void reduceNum(Cart cart);
    Cart findCart(Cart cart);
    void insertCart(Cart cart);
    void deleteCart(Cart cart);
    PageBean<Cart> findByPage(int uid, int currentPage, int pageSize);

    void addCookie(Cart cart, int number);
}
