package dao.impl;

import dao.GoodsDao;
import entity.Goods;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

//    //查询数据库所有商品
//    @Override
//    public Goods findAllGoods() {
//        String sql="select * from tab_goods";
//        Goods goods = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Goods.class));
//        return goods;
//    }

    @Override
    //查找商品总数
    public int findCount(int lid) {
        String sql="select count(*) from tab_goods where lid=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,lid);
    }

    @Override
    public int findCountBrand(int bid) {
        String sql="select count(*) from tab_goods where bid=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,bid);
    }


    //查找商品总数(overwrite)
    public int findCount(String str) {
        String sql="select count(*) from tab_goods where gname like concat('%',?,'%') ";
        return jdbcTemplate.queryForObject(sql,Integer.class,str);
    }

    //查询出某页要显示的商品页面
    @Override
    public List<Goods> findByPage(int lid, int start, int pageSize) {
        String sql="select * from tab_goods where lid=? limit ?,?";
        List<Goods> list=  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Goods.class),lid,start,pageSize);

        return list;
    }

    @Override
    public List<Goods> findByPageBrand(int bid, int start, int pageSize) {
        String sql="select * from tab_goods where bid=? limit ?,?";
        List<Goods> list=  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Goods.class),bid,start,pageSize);
        return list;
    }


    //模糊查询出某页要显示的商品页面(overwrite)
    @Override
    public List<Goods> findByPage(String str, int start, int pageSize) {
        String sql="select * from tab_goods g join tab_label l on g.lid=l.lid  join tab_brand b on g.bid=b.bid " +
                " where gname like concat('%',?,'%') or lname like concat('%',?,'%') or bname like concat('%',?,'%') limit ?,?";
        List<Goods> list=  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Goods.class),str,str,str,start,pageSize);
        return list;
    }

    //模糊查询出某页要显示的商品页面(overwrite)
    @Override
    public List<Goods> findByPage(int start, int pageSize) {
        String sql="select * from tab_goods g join tab_label l on g.lid=l.lid limit ?,?";
        List<Goods> list=  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Goods.class),start,pageSize);
        return list;
    }

    //根据商品id查询所有信息
    @Override
    public Goods findById(int gid) {
        String sql = "select * from tab_goods where gid = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Goods.class), gid);
    }
}
