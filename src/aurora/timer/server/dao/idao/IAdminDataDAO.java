package aurora.timer.server.dao.idao;

import aurora.timer.server.vo.AdminData;
import java.sql.Time;
public interface IAdminDataDAO {

    /**
     * 更新AdminData信息
     * @param vo 传入AdminData对象
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    public boolean doUpdate(AdminData vo) throws Exception;

    /**
     * 获取所有内容
     * @return 所有内容
     * @throws Exception
     */
    public AdminData findById1() throws Exception;

    /**
     * 获取公告内容
     * @return 公告内容
     * @throws Exception
     */
    public String findAnnouncement() throws Exception;

    /**
     * 获取值日表，格式为 张三｜张三｜……
     * @return 值日表
     * @throws Exception
     */
    public String findDutyList() throws Exception;

    /**
     * 获取放挂开始时间
     * @return 放挂开始时间
     * @throws Exception
     */
    public Time findFreeTimeStart() throws Exception;

    /**
     * 获取放挂结束时间
     * @return 放挂结束时间
     * @throws Exception
     */
    public Time findFreeTimeEnd() throws Exception;

}
