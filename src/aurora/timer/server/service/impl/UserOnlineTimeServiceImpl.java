package aurora.timer.server.service.impl;

import aurora.timer.server.dao.idao.IUserOnlineTimeDAO;
import aurora.timer.server.factory.DAOFactory;
import aurora.timer.server.service.DBConnection;
import aurora.timer.server.service.iservice.IUserOnlineTimeService;

import aurora.timer.server.vo.UserOnlineTime;
import com.mysql.jdbc.Connection;

import java.sql.Date;
import java.sql.Time;
import java.util.logging.Logger;


/**
 * Created by hao on 17-1-18.
 */
public class UserOnlineTimeServiceImpl implements IUserOnlineTimeService {
    Logger logger = Logger.getLogger("UserOnlineTime");
    /**
     * 增加时间就靠这个了！每5分钟上传一次，如果距离最近在线时间小于10分钟的话则加时<br>
     * <li>否则更新最近在线时间并加上5分钟</li>
     * @param id 在线的用户的ID
     * @return 加时成功返回true，失败返回false
     * @throws Exception
     */
    @Override
    public boolean addTime(String id) throws Exception {
        long intervalTime = 10*60*1000;
        Connection conn = DBConnection.getConnection();
        IUserOnlineTimeDAO iUserOnlineTimeDAO = DAOFactory.getIUserOnlineTimeDAOInstance(conn);
        UserOnlineTime vo = null;
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        time = Time.valueOf(time.toString());
        vo = iUserOnlineTimeDAO.findByUnique(id, date);
        try {
            if (vo == null) {
                logger.info("今日在线表没有找到");
                vo = new UserOnlineTime();
                vo.setID(id);
                vo.setTodayOnlineTime(Long.decode("0"));
                vo.setLastOnlineTime(time);
                vo.setTodayDate(date);
                iUserOnlineTimeDAO.doCreate(vo);
            } else if ((time.getTime() - vo.getLastOnlineTime().getTime()) < intervalTime) {
                logger.info("正常情况");
                UserOnlineTime voUpdate = new UserOnlineTime();
                voUpdate.setID(id);
                voUpdate.setTodayDate(date);
                voUpdate.setLastOnlineTime(time);
                voUpdate.setTodayOnlineTime(vo.getTodayOnlineTime() + (voUpdate.getLastOnlineTime().getTime() -
                        vo.getLastOnlineTime().getTime()));
                iUserOnlineTimeDAO.doUpdate(voUpdate);
            } else {
                logger.info("今日上线过，又下线后的情况");
                UserOnlineTime voUpdate = new UserOnlineTime();
                voUpdate.setID(id);
                voUpdate.setTodayOnlineTime(vo.getTodayOnlineTime());
                voUpdate.setLastOnlineTime(time);
                voUpdate.setTodayDate(date);
                iUserOnlineTimeDAO.doUpdate(voUpdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            return false;
        }
        conn.close();
        return true;
    }

    public static void main(String args[]) throws Exception{
        UserOnlineTimeServiceImpl userOnlineTimeService = new UserOnlineTimeServiceImpl();
        try{
            userOnlineTimeService.addTime("15115072043");
            System.err.println("成功了！！！");
        } catch (Exception e) {
            Logger.getLogger("hehe").warning("失败了");
            e.printStackTrace();
        }
    }
}
