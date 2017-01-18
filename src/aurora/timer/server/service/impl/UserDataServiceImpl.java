package aurora.timer.server.service.impl;

import aurora.timer.server.dao.idao.IUserDataDAO;
import aurora.timer.server.factory.DAOFactory;
import aurora.timer.server.service.DBConnection;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.vo.*;

import com.mysql.jdbc.Connection;

import java.sql.SQLException;
import java.util.logging.Logger;

import static aurora.timer.server.service.DBConnection.getConnection;

/**
 * Created by hao on 17-1-17.
 */
public class UserDataServiceImpl implements IUserDataService {
    Logger logger = Logger.getLogger("UserDateService");
    /**
     * 注册一个新用户
     * @param vo 新用户的信息
     * @return 注册成功返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean register(UserData vo) throws Exception {
        boolean flag = false;
        Connection conn = DBConnection.getConnection();
        IUserDataDAO iUserDataDAO = DAOFactory.getIUserDataDAOInstance(conn);
        try {
            iUserDataDAO.doCreate(vo);
            logger.info("创建用户成功");
            flag = true;
        } catch (SQLException e){
            logger.warning("创建用户失败");
            //e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改用户资料
     * @param vo 修改后的用户表
     * @return 成功返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean changeData(UserData vo) throws Exception {
        return false;
    }

    /**
     * 注销账户：使一个账户变为离开状态，不录入统计
     * @param id 需要注销的账户的ID
     * @return 注销成功返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean logout(String id) throws Exception {
        return false;
    }

    /**
     * 删除账号：彻底删除账号，包括之前的记录
     * @param id 需要删除的账号的ID
     * @return 删除成功返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean deleteAccount(String id) throws Exception {
        return false;
    }
//    测试
    public static void main(String args[]) throws Exception{
        UserData userData = new UserData("ARiKi","15115072042","8990890");
        if (new UserDataServiceImpl().register(userData)){
            System.out.println("呵呵");
        }
    }
}
