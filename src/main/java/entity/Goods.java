package entity;

import java.util.List;

public class Goods {
    private int gid;
    private double price;
    private String gname;
    private int bid;
    private int lid;
    private String gimage;
    private String description;
    private List<GoodsPic> picList;

    public Goods() {
    }

    public String getGimage() {
        return gimage;
    }

    public void setGimage(String gimage) {
        this.gimage = gimage;
    }

    public Goods(int gid, double price, String gname, int bid, int lid, String gimage, String description, List<GoodsPic> picList) {
        this.gid = gid;
        this.price = price;
        this.gname = gname;
        this.bid = bid;
        this.lid = lid;
        this.gimage = gimage;
        this.description = description;
        this.picList = picList;
    }

    public List<GoodsPic> getPicList() {
        return picList;
    }

    public void setPicList(List<GoodsPic> picList) {
        this.picList = picList;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
