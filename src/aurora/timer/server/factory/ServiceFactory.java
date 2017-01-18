package aurora.timer.server.factory;

import aurora.timer.server.service.impl.UserDataServiceImpl;
import aurora.timer.server.service.iservice.IUserDataService;

/**
 * Created by hao on 17-1-18.
 */
public class ServiceFactory {
    public static IUserDataService getIUserOnlineTimeService() {
        return new UserDataServiceImpl();
    }

    public static IUserDataService getIUserDataService() {
        return new UserDataServiceImpl();
    }
}
