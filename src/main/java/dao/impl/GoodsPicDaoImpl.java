package dao.impl;

import dao.GoodsPicDao;
import entity.GoodsPic;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class GoodsPicDaoImpl implements GoodsPicDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<GoodsPic> findListByGid(int gid) {
        String sql="select * from tab_picture where gid=?";
        List<GoodsPic> list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(GoodsPic.class),gid);
        return list;
    }
}
