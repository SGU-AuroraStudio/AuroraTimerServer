package aurora.timer.server.factory;

import aurora.timer.server.service.impl.AdminDataService;
import aurora.timer.server.service.impl.UserDataServiceImpl;
import aurora.timer.server.service.impl.UserOnlineTimeServiceImpl;
import aurora.timer.server.service.iservice.IAdminDataService;
import aurora.timer.server.service.iservice.IUserDataService;

/**
 * Created by hao on 17-1-18.
 */
public class ServiceFactory {
    public static UserOnlineTimeServiceImpl getIUserOnlineTimeService() {
        return new UserOnlineTimeServiceImpl();
    }

    public static IUserDataService getIUserDataService() {
        return new UserDataServiceImpl();
    }

    public static IAdminDataService getIAdminDataService() { return new AdminDataService(); }
}
