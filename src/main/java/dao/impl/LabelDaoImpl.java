package dao.impl;

import dao.LabelDao;
import entity.Label;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class LabelDaoImpl implements LabelDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Label> findAll() {
        String sql = "select * from tab_label";
        List<Label> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Label.class));
        return list;
    }
}
