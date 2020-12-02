package aurora.timer.server.service.impl;

import aurora.timer.server.dao.idao.IAdminDataDAO;
import aurora.timer.server.dao.idao.IUserDataDAO;
import aurora.timer.server.factory.DAOFactory;
import aurora.timer.server.service.DBConnection;
import aurora.timer.server.service.iservice.IAdminDataService;
import aurora.timer.server.vo.AdminData;
//import com.mysql.jdbc.Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AdminDataService implements IAdminDataService {
    Logger logger = Logger.getLogger("AdminDateService");

    @Override
    public boolean updateAdminData(AdminData vo) throws Exception {
        boolean flag = false;
        try (Connection conn = DBConnection.getConnection()) {
            IAdminDataDAO iAdminDataDAODataDAO = DAOFactory.getIAdminDataDAOInstance(conn);
            flag = iAdminDataDAODataDAO.doUpdate(vo);
            logger.fine("更新管理员数据成功\n" + vo.getAnnouncement() + "\n" + vo.getDutylist() + "\n" + vo.getFreeTimeStart() + "\n" + vo.getFreeTimeEnd());
        } catch (SQLException e) {
            logger.warning("更新管理员数据失败" + e);
        }
        return flag;
    }

    @Override
    public AdminData getAdminData() throws Exception {
        AdminData adminData = new AdminData();
        try (Connection conn = DBConnection.getConnection()) {
            IAdminDataDAO iAdminDataDAODataDAO = DAOFactory.getIAdminDataDAOInstance(conn);
            adminData = iAdminDataDAODataDAO.findById1();
        } catch (SQLException e) {
            logger.warning("查询管理员数据失败" + e);
        }
        return adminData;
    }
}
