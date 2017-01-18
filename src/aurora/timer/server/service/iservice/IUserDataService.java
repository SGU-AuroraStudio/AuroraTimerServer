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
     * 增加时间就靠这个了！每5分钟上传一次，如果距离最近在线时间小于10分钟的话则加时<br>
     * <li>否则更新最近在线时间并加上5分钟</li>
     * @param id 在线的用户的ID
     * @return 加时成功返回true，失败返回false
     * @throws Exception
     */
    public boolean addTime(String id) throws Exception;

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
