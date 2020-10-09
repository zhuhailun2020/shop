package dao;

import entity.Goods;

import java.util.List;


public interface GoodsDao {

    int findCount(int lid);

    int findCount(String str);

    List<Goods> findByPage(int lid, int start, int pageSize);

    List<Goods> findByPage(String str, int start, int pageSize);

    List<Goods> findByPage( int start, int pageSize);

    Goods findById(int gid);

    public int findCountBrand(int bid);

    public List<Goods> findByPageBrand(int bid, int start, int pageSize);
}
