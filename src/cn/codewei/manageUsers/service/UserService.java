package cn.codewei.manageUsers.service;

import cn.codewei.manageUsers.domain.PageBean;
import cn.codewei.manageUsers.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();
    public void addUser(User user);
    public User findUser(int id);
    public void updateUser(User user);
    public void delUser(int id);
    public void delSelect(String[] ids);
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> codition);
}
