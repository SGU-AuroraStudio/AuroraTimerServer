package aurora.timer.server.service.iservice;

import aurora.timer.server.vo.AdminData;

import java.sql.Time;
import java.util.Set;

public interface IAdminDataService {
    /**
     * 更新管理员界面数据
     * @param vo 传入新的管理员界面数据
     * @return 成功返回true，否则返回false
     * @throws Exception
     */
    public boolean updateAdminData(AdminData vo) throws Exception;

    /**
     * 获取管理员界面数据
     * @return 管理员界面数据
     * @throws Exception
     */
    public AdminData getAdminData() throws Exception;

//    /**
//     * 获取值日表，格式为 张三｜张三｜……
//     * @return 值日表
//     * @throws Exception
//     */
//    public String getDutyList() throws Exception;
//
//    /**
//     * 获取放挂时间
//     * @return 放挂时间(包含开始和结束)
//     * @throws Exception
//     */
//    public Set<Time> getFreeTime() throws Exception;

}
