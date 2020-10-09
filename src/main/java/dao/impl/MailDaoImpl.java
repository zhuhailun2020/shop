package dao.impl;

import dao.MailDao;
import entity.Goods;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Date;

public class MailDaoImpl implements MailDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    //发送邮箱并存储至数据库
    @Override
    public void sendMail(String ename, String email, String suject, String message) {
        String sql="insert into tab_feedback values(null,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Date(),ename,email,suject,message);
    }


}
