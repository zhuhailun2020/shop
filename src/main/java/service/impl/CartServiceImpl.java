package service.impl;

import dao.CartDao;
import dao.GoodsDao;
import dao.GoodsPicDao;
import dao.impl.CartDaoImpl;
import dao.impl.GoodsDaoImpl;
import dao.impl.GoodsPicDaoImpl;
import entity.Cart;
import entity.Goods;
import entity.GoodsPic;
import entity.PageBean;
import service.CartService;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cd = new CartDaoImpl();
    private GoodsDao gd = new GoodsDaoImpl();
    private GoodsPicDao gpd = new GoodsPicDaoImpl();

    @Override
    public List<Cart> findByUid(int uid) {
        List<Cart> _cartList = cd.findByUid(uid);
        //将goods对象设置进入cart对象中
        List<Cart> cartList = new ArrayList<>();
        //如果_cartList不为空
        if (_cartList != null){
            for (Cart cart: _cartList){
                Goods goods = gd.findById(cart.getGid());
                //将图片列表设置近goods中
                List<GoodsPic> picList = gpd.findListByGid(goods.getGid());
                goods.setPicList(picList);
                cart.setGoods(goods);
                cartList.add(cart);
            }
        }
        return cartList;
    }

    @Override
    public void deleteCartByUid(int uid) {
        cd.deleteCartByUid(uid);
    }

    @Override
    public void addNum(Cart cart) {
        cd.addNum(cart);
    }

    @Override
    public void reduceNum(Cart cart) {
        cd.reduceNum(cart);
    }

    @Override
    public Cart findCart(Cart cart) {

        Cart _cart = cd.findCart(cart);
        //如果此cart存在 设置goods对象
        if (_cart != null){
            Goods goods = gd.findById(_cart.getGid());
            //将图片列表设置近goods中
            List<GoodsPic> picList = gpd.findListByGid(goods.getGid());
            goods.setPicList(picList);
            _cart.setGoods(goods);
        }
        return _cart;
    }

    @Override
    public void insertCart(Cart cart) {
        cd.insertCart(cart);
    }

    @Override
    public void deleteCart(Cart cart) {
        cd.deleteCart(cart);
    }

    @Override
    public PageBean<Cart> findByPage(int uid, int currentPage, int pageSize) {
        //1.计算开始条数
        int start = (currentPage - 1) * pageSize;
        //1.1获取cart列表
        List<Cart> _cartList = cd.findByPage(uid, start, pageSize);
        //将goods对象设置进入cart对象中
        List<Cart> cartList = new ArrayList<>();
        //如果_cartList不为空
        if (_cartList != null){
            for (Cart cart: _cartList){
                Goods goods = gd.findById(cart.getGid());
                //将图片列表设置近goods中
                List<GoodsPic> picList = gpd.findListByGid(goods.getGid());
                goods.setPicList(picList);
                cart.setGoods(goods);
                cartList.add(cart);
            }
        }

        //2.获取总记录条数
        int totalCount = cd.findCount(uid);
        //3.计算总页数
        int totalPage = (totalCount + (pageSize - 1)) / pageSize;

        //封装PageBean
        PageBean<Cart> pageBean = new PageBean<>();
        pageBean.setList(cartList);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public void addCookie(Cart cart, int number) {
        cd.addCookie(cart, number);
    }
}
