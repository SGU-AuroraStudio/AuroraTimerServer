package aurora.timer.server.service.iservice;

import aurora.timer.server.vo.UserData;

import java.io.InputStream;
import java.sql.Blob;

/**
 * Created by hao on 17-1-17.
 */
public interface IUserDataService {
    /**
     * 注册一个新用户
     *
     * @param vo 新用户的信息
     * @return 注册成功返回true，否则返回false
     * @throws Exception
     */
    public boolean register(UserData vo) throws Exception;

    /**
     * 修改用户资料
     *
     * @param vo 修改后的用户表
     * @return 成功返回true，否则返回false
     * @throws Exception
     */
    public boolean changeData(UserData vo) throws Exception;

    /**
     * 注销账户：使一个账户变为离开状态，不录入统计
     *
     * @param id 需要注销的账户的ID
     * @return 注销成功返回true，否则返回false
     * @throws Exception
     */
    public boolean logout(String id) throws Exception;

    /**
     * 删除账号：彻底删除账号，包括之前的记录
     *
     * @param id 需要删除的账号的ID
     * @return 删除成功返回true，否则返回false
     * @throws Exception
     */
    public boolean deleteAccount(String id) throws Exception;

    /**
     * 使用id查找用户，并返回他的非隐私信息，隐私用默认的填表
     *
     * @param id 用户的id
     * @return 返回用户的信息
     * @throws Exception
     */
    public UserData searchUserById(String id) throws Exception;

    /**
     * 根据id更新背景图片
     *
     * @param id 传入用户的ID
     * @param bg 传入背景图片 Blob
     * @return 成功则返回true，有一个或一个以上删除失败则返回false
     * @throws Exception
     */
    public boolean updateBgById(String id, InputStream bg) throws Exception;

    /**
     * 根据id查询背景图片
     *
     * @param id 传入用户的ID
     * @return 背景图片的InputStream
     * @throws Exception
     */
    public InputStream searchBgById(String id) throws Exception;
}
