package aurora.timer.server.dao.idao;

import aurora.timer.server.vo.UserOnlineTime;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

/**
 * Created by hao on 17-1-16.
 */
public interface IUserOnlineTimeDAO{
    /**
     * 增加一条一个人一天的时间记录
     * @param vo 增加的时间记录表
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    public boolean doCreate(UserOnlineTime vo) throws Exception;

    /**
     * 更新一条UserOnlineTime的记录
     * @param vo 需要更新的事件记录表
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    public boolean doUpdate(UserOnlineTime vo) throws Exception;

    /**
     * 用id检索删除时间记录
     * @param id 需要删除全部记录的id
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    public boolean doRemoveById(String id) throws Exception;

    /**
     * 使用日期删除时间记录
     * @param date 需要删除全部记录的日期
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    public boolean doRemoveByData(Date date) throws Exception;

    /**
     * 删除一个用户在某一天的时间记录
     * @param id 需要删除一条记录的id
     * @param date 需要删除的那一日期
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    public boolean doRemoveUnique(String id, Date date) throws Exception;

    /**
     * 根据id查找一个用户的在线记录
     * @param id 用户的id
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    public Set<UserOnlineTime> findById(String id) throws Exception;

    /**
     * 根据日期查找在线记录
     * @param date 需要查找的日期
     * @return 查找到的vo的集合
     * @throws Exception
     */
    public Set<UserOnlineTime> findByData(Date date) throws Exception;

    /**
     * 根据用户和日期查找在线记录
     * @param id 需要查找的id
     * @param date 需要查找的日期
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    public UserOnlineTime findByUnique(String id, Date date) throws Exception;

    /**
     * 根据用户查询一段时间的在线记录（dateStart-今天）
     * @param dateStart 本学期第一天
     * @return 阿巴阿巴
     * @throws Exception
     */
    public Set<UserOnlineTime> findByFromDate2Today(Date dateStart) throws Exception;
}
