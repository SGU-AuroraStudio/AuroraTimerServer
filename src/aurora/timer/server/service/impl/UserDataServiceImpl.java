package aurora.timer.server.service.impl;

import aurora.timer.server.dao.idao.IUserDataDAO;
import aurora.timer.server.factory.DAOFactory;
import aurora.timer.server.service.DBConnection;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.vo.*;

//import com.mysql.jdbc.Connection;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static aurora.timer.server.service.DBConnection.getConnection;

/**
 * Created by hao on 17-1-17.
 */
public class UserDataServiceImpl implements IUserDataService {
    Logger logger = Logger.getLogger("UserDateService");

    /**
     * 注册一个新用户
     *
     * @param vo 新用户的信息
     * @return 注册成功返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean register(UserData vo) throws Exception {
        boolean flag = false;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            IUserDataDAO iUserDataDAO = DAOFactory.getIUserDataDAOInstance(conn);
            flag = iUserDataDAO.doCreate(vo);
            logger.fine("创建用户成功");
            conn.close();
        } catch (SQLException e) {
            logger.warning("创建用户失败");
            //e.printStackTrace();
        } finally {
            conn.close();
        }
        return flag;
    }

    /**
     * 修改用户资料
     *
     * @param vo 修改后的用户表
     * @return 成功返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean changeData(UserData vo) throws Exception {
        boolean flag = false;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            IUserDataDAO iUserDataDAO = DAOFactory.getIUserDataDAOInstance(conn);
            flag = iUserDataDAO.doUpdate(vo);
            logger.fine("修改用户资料成功");
        } catch (Exception e) {
            logger.warning("修改用户资料失败");
        } finally {
            conn.close();
        }
        return flag;
    }

    /**
     * 注销账户：使一个账户变为离开状态，不录入统计
     *
     * @param id 需要注销的账户的ID
     * @return 注销成功返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean logout(String id) throws Exception {
        boolean flag = false;
        UserData voUpdate = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            IUserDataDAO iUserDataDAO = DAOFactory.getIUserDataDAOInstance(conn);
            voUpdate = iUserDataDAO.findById(id);
            if (voUpdate.getIsLeave() == true) {
                logger.info("该用户已经是离开的");
                return false;
            }
            voUpdate.setIsLeave(true);
            flag = iUserDataDAO.doUpdate(voUpdate);
            logger.fine("已设置用户离开状态");
        } catch (Exception e) {
            logger.warning("设置用户离开状态失败");
        } finally {
            conn.close();
        }
        return flag;
    }

    /**
     * 删除账号：彻底删除账号，包括之前的记录
     *
     * @param id 需要删除的账号的ID
     * @return 删除成功返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean deleteAccount(String id) throws Exception {
        boolean flag = false;
        try (Connection conn = DBConnection.getConnection()) {
            IUserDataDAO iUserDataDAO = DAOFactory.getIUserDataDAOInstance(conn);
            Set<String> set = new HashSet<>();
            set.add(id);
            flag = iUserDataDAO.doRemoveBatch(set);
        }
        return flag;
    }

    /**
     * 使用id查找用户，并返回他的非隐私信息，隐私用默认的填表
     * * @param id 用户的id
     *
     * @return 返回用户的信息
     * @throws Exception
     */
    @Override
    public UserData searchUserById(String id) throws Exception {
        UserData vo = null;
        try (Connection conn = DBConnection.getConnection()) {
            IUserDataDAO iUserDataDAO = DAOFactory.getIUserDataDAOInstance(conn);
            vo = iUserDataDAO.findById(id);
            if (vo != null)
                logger.fine("查找成功");
        } catch (Exception e) {
            logger.warning("查找用户信息失败");
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public boolean updateBgById(String id, InputStream bg) throws Exception {
        boolean flag = false;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            IUserDataDAO iUserDataDAO = DAOFactory.getIUserDataDAOInstance(conn);
            flag = iUserDataDAO.updateBgById(id, bg);
            if (flag)
                logger.info("用户:" + id + " 修改背景图片成功");
        } catch (Exception e) {
            logger.warning("用户:" + id + "修改背景图片失败");
        } finally {
            conn.close();
        }
        return flag;
    }

    @Override
    public InputStream searchBgById(String id) throws Exception {
        Connection conn = null;
        InputStream bg = null;
        try {
            conn = DBConnection.getConnection();
            IUserDataDAO iUserDataDAO = DAOFactory.getIUserDataDAOInstance(conn);
            bg = iUserDataDAO.findBgById(id);
            if (bg != null)
                logger.info("用户:" + id + " 查找背景图片成功");
        } catch (Exception e) {
            logger.warning("用户:" + id + "查找背景图片失败");
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return bg;
    }
}
