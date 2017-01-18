package aurora.timer.server.service.iservice;

/**
 * Created by hao on 17-1-18.
 */
public interface IUserOnlineTimeService {
    /**
     * 增加时间就靠这个了！每5分钟上传一次，如果距离最近在线时间小于10分钟的话则加时<br>
     * <li>否则更新最近在线时间并加上5分钟</li>
     * @param id 在线的用户的ID
     * @return 加时成功返回true，失败返回false
     * @throws Exception
     */
    public boolean addTime(String id) throws Exception;
}
