package cn.codewei.manageUsers.test;

import cn.codewei.manageUsers.dao.impl.ManagerDaoImpl;
import cn.codewei.manageUsers.domain.Manager;
import org.junit.Test;

public class ManagerDaoImplTest {
    @Test
    public void loginTest(){
        ManagerDaoImpl managerDao = new ManagerDaoImpl();
        Manager manager = new Manager();
        manager.setUsername("shihongwei");
        manager.setPassword("shw123zxc..");
        boolean login = managerDao.login(manager);
    }
}
