package dao;

import entity.GoodsPic;

import java.util.List;

public interface GoodsPicDao {
    List<GoodsPic> findListByGid(int gid);
}
