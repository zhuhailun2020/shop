package service.impl;

import dao.GoodsDao;
import dao.GoodsPicDao;
import dao.impl.GoodsDaoImpl;
import dao.impl.GoodsPicDaoImpl;
import entity.GoodsPic;
import entity.PageBean;
import entity.Goods;
import service.GoodsService;

import java.util.List;

public  class GoodsServiceImpl implements GoodsService{
    private GoodsDao GoodsDao=new GoodsDaoImpl();
    private GoodsPicDao goodsPicDao=new GoodsPicDaoImpl();

    @Override
    public PageBean<Goods> searchByPage(String str, int currentPage, int pageSize) {
        PageBean<Goods> pageBean=new PageBean<>();
        int totalCount=GoodsDao.findCount(str);
        int start =(currentPage-1)*pageSize;
        List<Goods> byPage = GoodsDao.findByPage(str, start, pageSize);
        int totalPage = (totalCount + pageSize - 1) / pageSize;
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(byPage);
        pageBean.setTotalCount(totalCount);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public PageBean<Goods> findByPage(int lid, int currentPage, int pageSize) {
        PageBean<Goods> pageBean=new PageBean<>();
        int totalCount=GoodsDao.findCount(lid);
        int start =(currentPage-1)*pageSize;
        List<Goods> byPage = GoodsDao.findByPage(lid, start, pageSize);
        int totalPage = (totalCount + pageSize - 1) / pageSize;
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(byPage);
        pageBean.setTotalCount(totalCount);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
    @Override
    public PageBean<Goods> findByPageBrand(int bid, int currentPage, int pageSize) {
        PageBean<Goods> pageBean=new PageBean<>();
        int totalCount=GoodsDao.findCountBrand(bid);
        int start =(currentPage-1)*pageSize;
        List<Goods> byPage = GoodsDao.findByPageBrand(bid, start, pageSize);
        int totalPage = (totalCount + pageSize - 1) / pageSize;
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(byPage);
        pageBean.setTotalCount(totalCount);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public Goods findOne(int gid) {
        Goods goods=GoodsDao.findById(gid);
        List<GoodsPic> list=goodsPicDao.findListByGid( gid);
        goods.setPicList(list);
        return goods;
    }



}
