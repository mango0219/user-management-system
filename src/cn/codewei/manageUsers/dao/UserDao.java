package cn.codewei.manageUsers.dao;

import cn.codewei.manageUsers.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll();
    public void addUser(User user);
    public User findUser(int id);
    public void updateUser(User user);
    public void delUser(int id);
    public int findTotalCount(Map<String, String[]> codition);
    public List<User> findByPage(int start,int row,Map<String, String[]> codition);
}
