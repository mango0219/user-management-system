package cn.codewei.manageUsers.dao.impl;

import cn.codewei.manageUsers.dao.ManagerDao;
import cn.codewei.manageUsers.domain.Manager;
import cn.codewei.manageUsers.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class ManagerDaoImpl implements ManagerDao {

    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public boolean login(Manager manager) {
        String username = manager.getUsername();
        String password = manager.getPassword();
        // 定义sql 查询username是否存在
        String sql = "select if(? in (select username from manager),1,2)";
        Integer integer = jt.queryForObject(sql, Integer.class, username);
        if (integer==2){
            // 用户名不存在
            return false;
        }
        // 用户名存在
        // 判断密码是否正确
        String sql2 = "select if(md5(?)=(select password from manager where username=?),1,2)";
        Integer integer1 = jt.queryForObject(sql2, Integer.class, password, username);
        if (integer1 == 2){
            // 密码错误
            return false;
        }
        // 密码正确 成功登录
        return true;
    }
}
