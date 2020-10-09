package dao.impl;

import dao.UserDao;
import entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    public void registUser(User user){
        String sql="insert into tab_user values(null,?,?,?,?,?,?,?,?)";
        //System.out.println(user.getUname()+user.getPassword()+","+user.getSex()+","+user.getEmail()+","+user.getPhone()+","+user.getAddress()+","+user.getStatus()+","+user.getCode());
         template.update(sql,user.getUname(),user.getPassword(),user.getSex(),user.getEmail(),user.getPhone(),user.getAddress(),user.getStatus(),user.getCode());

    }

    @Override
    public User findUserByEmailAndPassword(String email,String password) {
        String sql="select * from tab_user where email=? and password=?";
        try {
            User user1=template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),email,password);
            return user1;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public User checkUser(String uname) {
        String sql="select * from tab_user where uname=?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), uname);
        return user;
    }

    @Override
    public User checkEmail(String email) {
        String sql="select * from tab_user where email=?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
        return user;
    }

    @Override
    public int updateUserStatus(String code) {
        String sql="update tab_user set status='Y' where code=?";
        int update1 = template.update(sql, code);
        return update1;
    }
}
