package aurora.timer.server.factory;

import aurora.timer.server.dao.idao.IAdminDataDAO;
import aurora.timer.server.dao.idao.IUserDataDAO;
import aurora.timer.server.dao.idao.IUserOnlineTimeDAO;
import aurora.timer.server.dao.impl.AdminDataDAOImpl;
import aurora.timer.server.dao.impl.UserDataDAOImpl;
import aurora.timer.server.dao.impl.UserOnlineTimeDAOImpl;
import com.mysql.jdbc.Connection;

/**
 * Created by hao on 17-1-15.
 */
public class DAOFactory {
    public static IUserDataDAO getIUserDataDAOInstance(Connection conn) { return new UserDataDAOImpl(conn); }

    public static IUserOnlineTimeDAO getIUserOnlineTimeDAOInstance(Connection conn) { return new UserOnlineTimeDAOImpl(conn); }

    public static IAdminDataDAO getIAdminDataDAOInstance(Connection conn) { return new AdminDataDAOImpl(conn); }
}
