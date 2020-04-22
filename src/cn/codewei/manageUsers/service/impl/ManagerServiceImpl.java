package cn.codewei.manageUsers.service.impl;

import cn.codewei.manageUsers.dao.ManagerDao;
import cn.codewei.manageUsers.dao.impl.ManagerDaoImpl;
import cn.codewei.manageUsers.domain.Manager;
import cn.codewei.manageUsers.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
    private ManagerDao managerDao = new ManagerDaoImpl();

    /**
     * 判断登录用户名密码是否正确
     * @param manager
     * @return
     */
    @Override
    public boolean login(Manager manager) {
        // 调用dao
        return managerDao.login(manager);
    }
}
