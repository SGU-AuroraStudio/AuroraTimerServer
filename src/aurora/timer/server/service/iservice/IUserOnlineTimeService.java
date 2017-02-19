package aurora.timer.server.service.iservice;

import aurora.timer.server.vo.UserOnlineTime;

import java.sql.Date;
import java.util.Set;

/**
 * Created by hao on 17-1-18.
 */
public interface IUserOnlineTimeService {
    /**
     * 增加时间就靠这个了！每5分钟上传一次，如果距离最近在线时间小于10分钟的话则加时<br>
     * @param id 在线的用户的ID
     * @return 加时成功返回true，失败返回false
     * @throws Exception
     */
    public boolean addTime(String id) throws Exception;

    /**
     * 查询这周所有在线过的用户的这周的总时间
     * @return 返回所有这周数据的集合，统计交给个客户端
     * @throws Exception
     */
    public Set<UserOnlineTime> thisWeekData() throws Exception;

    /**
     * 查询前第x周的在线用户数据
     * @param x x为0是代表本周，1代表上周，以此类推
     * @return 返回第前x周的数据的集合
     * @throws Exception
     */
    public Set<UserOnlineTime> lastXWeekData(int x) throws Exception;

    /**
     * 返回今天米那桑的情况
     * @return 啊啊啊啊啊啊啊哈哈哈哈哈哈哈呵呵呵呵
     * @throws Exception
     */
    public Set<UserOnlineTime> todayData() throws Exception;

    /**
     * 查找by id。
     * @param id id
     * @return 呵呵。。。。
     * @throws Exception
     */
    public UserOnlineTime searchByUnique(String id, Date date) throws Exception;
}
