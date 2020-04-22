package cn.codewei.manageUsers.service.impl;

import cn.codewei.manageUsers.dao.UserDao;
import cn.codewei.manageUsers.dao.impl.UserDaoImpl;
import cn.codewei.manageUsers.domain.PageBean;
import cn.codewei.manageUsers.domain.User;
import cn.codewei.manageUsers.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<User> findAll() {
        List<User> list = dao.findAll();
        return list;
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    /**
     * 根据id查询用户信息，进行回显
     * @param id
     * @return
     */
    @Override
    public User findUser(int id) {
        return dao.findUser(id);
    }

    /**
     * 更改用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    /**
     * 根据用户的id删除单个用户
     * @param id
     */
    @Override
    public void delUser(int id) {
        dao.delUser(id);
    }

    /**
     * 批量删除用户信息
     * @param ids
     */
    @Override
    public void delSelect(String[] ids) {
        for (String i : ids) {
            int id = Integer.parseInt(i);
            dao.delUser(id);
        }
    }

    /**
     * 分页条件查询
     * @param currentpage
     * @param rows
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String currentpage, String rows, Map<String, String[]> codition) {
        int currentPage = Integer.parseInt(currentpage);  //当前页数
        int row = Integer.parseInt(rows);  //每页显示几条数据
        if (currentPage<=0){
            currentPage=1;
        }
        int totalCount = dao.findTotalCount(codition);  //总数据条数
        int totalPage = (totalCount%row)==0?totalCount/row:(totalCount/row)+1; // 计算出总页码数
        if (currentPage>totalPage){
            currentPage=totalPage;
        }
        // 查询出list集合
        int start = (currentPage-1)*row;
        List<User> list = dao.findByPage(start,row,codition);
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(row);
        pageBean.setList(list);
        return pageBean;
    }
}
