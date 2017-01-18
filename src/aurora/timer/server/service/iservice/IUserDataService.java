package aurora.timer.server.service.iservice;

import aurora.timer.server.vo.UserData;

/**
 * Created by hao on 17-1-17.
 */
public interface IUserDataService {
    /**
     * 注册一个新用户
     * @param vo 新用户的信息
     * @return 注册成功返回true，否则返回false
     * @throws Exception
     */
    public boolean register(UserData vo) throws Exception;

    /**
     * 修改用户资料
     * @param vo 修改后的用户表
     * @return 成功返回true，否则返回false
     * @throws Exception
     */
    public boolean changeData(UserData vo) throws Exception;

    /**
     * 注销账户：使一个账户变为离开状态，不录入统计
     * @param id 需要注销的账户的ID
     * @return 注销成功返回true，否则返回false
     * @throws Exception
     */
    public boolean logout(String id) throws Exception;

    /**
     * 删除账号：彻底删除账号，包括之前的记录
     * @param id 需要删除的账号的ID
     * @return 删除成功返回true，否则返回false
     * @throws Exception
     */
    public boolean deleteAccount(String id) throws Exception;
}
