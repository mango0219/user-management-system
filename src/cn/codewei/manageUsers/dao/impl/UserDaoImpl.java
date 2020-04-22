package cn.codewei.manageUsers.dao.impl;

import cn.codewei.manageUsers.dao.UserDao;
import cn.codewei.manageUsers.domain.User;
import cn.codewei.manageUsers.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<User> findAll() {
        // 定义sql
        String sql = "select * from user";
        List<User> list = jt.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return list;
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        String name = user.getName();
        String gender = user.getGender();
        int age = user.getAge();
        String address = user.getAddress();
        String qq = user.getQq();
        String email = user.getEmail();

        // 定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        jt.update(sql,name,gender,age,address,qq,email);
    }

    /**
     * 根据id查询用户信息 进行回显
     * @param id
     * @return
     */
    @Override
    public User findUser(int id) {
        // 定义sql
        String sql = "select * from user where id=?";
        User user = jt.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    /**
     * 更改用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        int id = user.getId();
        String name = user.getName();
        int age = user.getAge();
        String gender = user.getGender();
        String address = user.getAddress();
        String qq = user.getQq();
        String email = user.getEmail();

        // 定义sql
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        jt.update(sql,name,gender,age,address,qq,email,id);

    }

    @Override
    public void delUser(int id) {
        // 定义sql
        String sql = "delete from user where id = ?";
        jt.update(sql,id);
    }

    @Override
    public int findTotalCount(Map<String, String[]> codition) {
        // 定义sql
        String sql = "select count(*) from user where 1=1 ";
        StringBuffer sb = new StringBuffer(sql);

        // 遍历Map结合
        Set<String> keyset = codition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<>();
        for (String key : keyset) {
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            // 获取value
            String value = codition.get(key)[0];
            if (value!=null&&!"".equals(value)){
                // 拼接sql
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");
            }
        }
        sql = sb.toString();
        Integer totalCount = jt.queryForObject(sql, Integer.class,params.toArray());
        return totalCount;
    }

    @Override
    public List<User> findByPage(int start, int row,Map<String, String[]> codition) {
        if (start<0){
            start=1;
        }
        // 定义sql
        String sql = "select * from user where 1=1";
        StringBuffer sb = new StringBuffer(sql);

        // 遍历Map结合
        Set<String> keyset = codition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<>();
        for (String key : keyset) {
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            // 获取value
            String value = codition.get(key)[0];
            if (value!=null&&!"".equals(value)){
                // 拼接sql
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");
            }
        }
        sb.append(" limit ?,?");
        params.add(start);
        params.add(row);
        sql = sb.toString();

        List<User> list = jt.query(sql, new BeanPropertyRowMapper<User>(User.class),params.toArray());
        return list;
    }
}
