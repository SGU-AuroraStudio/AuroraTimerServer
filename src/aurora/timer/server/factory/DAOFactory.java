package aurora.timer.server.factory;

import aurora.timer.server.dao.idao.IUserDataDAO;
import aurora.timer.server.dao.impl.UserDataDAOImpl;
import com.mysql.jdbc.Connection;

/**
 * Created by hao on 17-1-15.
 */
public class DAOFactory {
    public static IUserDataDAO getIUserDataDAOInstance(Connection coon) {
        return new UserDataDAOImpl(coon);
    }
}
