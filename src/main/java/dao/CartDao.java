package dao;

import entity.Cart;

import java.util.List;

public interface CartDao {
    Cart findCart(Cart cart);
    List<Cart> findByUid(int uid);
    void deleteCartByUid(int uid);
    void addNum(Cart cart);
    void reduceNum(Cart cart);
    void insertCart(Cart cart);
    void deleteCart(Cart cart);
    int findCount(int uid);
    List<Cart> findByPage(int uid, int start, int pageSize);

    void addCookie(Cart cart, int number);
}
