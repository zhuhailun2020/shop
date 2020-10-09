package dao.impl;

import dao.BrandDao;
import entity.Brand;
import entity.Label;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class BrandDaoImpl implements BrandDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Brand> findAll() {
        String sql = "select * from tab_brand";
        List<Brand> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Brand.class));
        return list;
    }
}
