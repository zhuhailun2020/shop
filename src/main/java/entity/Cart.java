package entity;

import java.util.Comparator;

public class Cart {
    private int uid;
    private int gid;
    private int number;
    private User user;
    private Goods goods;

    public Cart() {
    }

    public Cart(int uid, int gid, int number, User user, Goods goods) {
        this.uid = uid;
        this.gid = gid;
        this.number = number;
        this.user = user;
        this.goods = goods;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "uid=" + uid +
                ", gid=" + gid +
                ", number=" + number +
                '}';
    }

}
