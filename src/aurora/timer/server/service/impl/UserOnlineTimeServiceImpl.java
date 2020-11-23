package aurora.timer.server.service.impl;

import aurora.timer.server.dao.idao.IUserOnlineTimeDAO;
import aurora.timer.server.factory.DAOFactory;
import aurora.timer.server.service.DBConnection;
import aurora.timer.server.service.iservice.IUserOnlineTimeService;

import aurora.timer.server.vo.UserOnlineTime;
//import com.mysql.jdbc.Connection; 原本是这个，因为MySQL版本不一样在本地用不了，服务器的是5我的是8

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


/**
 * Created by hao on 17-1-18.
 */
public class UserOnlineTimeServiceImpl implements IUserOnlineTimeService {
    Logger logger = Logger.getLogger("UserOnlineTime");
    /**
     * 增加时间就靠这个了！每5分钟上传一次，如果距离最近在线时间小于10分钟的话则加时
     * @param id 在线的用户的ID
     * @return 加时成功返回true，失败返回false
     * @throws Exception
     */
    @Override
    public boolean addTime(String id) throws Exception {
        if(id==null) return false;
        long intervalTime = 15*60*1000; //间隔判断时间，大于这个时间的提交都算重新上线
        UserOnlineTime vo = null;
        Date dateNow = new Date(System.currentTimeMillis());
        Time timeNow = new Time(System.currentTimeMillis());
        timeNow = Time.valueOf(timeNow.toString());
        try (Connection conn = DBConnection.getConnection()){
            IUserOnlineTimeDAO iUserOnlineTimeDAO = DAOFactory.getIUserOnlineTimeDAOInstance(conn);
            vo = iUserOnlineTimeDAO.findByUnique(id, dateNow);
            if (vo == null) {
                logger.info("今日在线表没有找到");
                vo = new UserOnlineTime();
                vo.setID(id);
                vo.setTodayOnlineTime(Long.decode("0"));
                vo.setLastOnlineTime(timeNow);
                vo.setTodayDate(dateNow);
                iUserOnlineTimeDAO.doCreate(vo);
            } else if ((timeNow.getTime() - vo.getLastOnlineTime().getTime()) < intervalTime) {
                logger.info("正常情况");
                UserOnlineTime voUpdate = new UserOnlineTime();
                voUpdate.setID(id);
                voUpdate.setTodayDate(dateNow);
                voUpdate.setLastOnlineTime(timeNow);
                voUpdate.setTodayOnlineTime(vo.getTodayOnlineTime() + (voUpdate.getLastOnlineTime().getTime() -
                        vo.getLastOnlineTime().getTime()));
                iUserOnlineTimeDAO.doUpdate(voUpdate);
            } else {
                logger.info("今日上线过，又下线后的情况");
                UserOnlineTime voUpdate = new UserOnlineTime();
                voUpdate.setID(id);
                voUpdate.setTodayOnlineTime(vo.getTodayOnlineTime());
                voUpdate.setLastOnlineTime(timeNow);
                voUpdate.setTodayDate(dateNow);
                iUserOnlineTimeDAO.doUpdate(voUpdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 查询前第x周的在线用户数据
     * @param x x为0是代表本周，1代表上周，以此类推
     * @return 返回第前x周的数据的集合
     * @throws Exception
     */
    @Override
    public Set<UserOnlineTime> lastXWeekData(int x) throws Exception {
        Set<UserOnlineTime> set = new HashSet<>();
        Date date = new Date(System.currentTimeMillis());
        int dayOfWeek = date.toLocalDate().getDayOfWeek().getValue();
        try (Connection conn = DBConnection.getConnection()){
            IUserOnlineTimeDAO iUserOnlineTimeDAO = DAOFactory.getIUserOnlineTimeDAOInstance(conn);
            // 从星期天开始
            if (x == 0) {
                for (int i = 0; i < (dayOfWeek % 7) + 1; i++) {
                    set.addAll(iUserOnlineTimeDAO.findByData(Date.valueOf(date.toLocalDate().minusDays(i))));
                }
            } else {
                int j = (dayOfWeek % 7) + 1 + (x - 1) * 7;
                for (int i = j; i < j + 7; i ++) {
                    set.addAll(iUserOnlineTimeDAO.findByData(Date.valueOf(date.toLocalDate().minusDays(i))));
                }
            }
            logger.fine("完成查找");
        } catch (Exception e) {
            logger.warning("查找周数据失败");
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 返回今天米那桑的情况
     * @return 啊啊啊啊啊啊啊哈哈哈哈哈哈哈呵呵呵呵
     * @throws Exception
     */
    @Override
    public Set<UserOnlineTime> todayData() throws Exception {
        Set<UserOnlineTime> set = new HashSet<>();
        try (Connection conn = DBConnection.getConnection()){
            IUserOnlineTimeDAO iuotd = DAOFactory.getIUserOnlineTimeDAOInstance(conn);
            set.addAll(iuotd.findByData(new Date(System.currentTimeMillis())));
            logger.fine("完成查找");
        } catch (Exception e) {
            logger.warning("查找周数据失败");
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 查找by id。
     * @param id id
     * @return 呵呵。。。。
     * @throws Exception
     */
    @Override
    public UserOnlineTime searchByUnique(String id, Date date) throws Exception {
        UserOnlineTime userOnlineTime = null;
        try (Connection conn = DBConnection.getConnection()) {
            IUserOnlineTimeDAO iuotd = DAOFactory.getIUserOnlineTimeDAOInstance(conn);
            userOnlineTime = iuotd.findByUnique(id, date);
            logger.fine("查找某天某人");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userOnlineTime;
    }

    @Override
    public Set<UserOnlineTime> searchByFromDate2Today(Date dateStart) throws Exception {
        Set<UserOnlineTime> set = new HashSet<>();
        try (Connection conn = DBConnection.getConnection()){
            IUserOnlineTimeDAO iuotd = DAOFactory.getIUserOnlineTimeDAOInstance(conn);
            set=iuotd.findByFromDate2Today(dateStart);
            logger.fine("查找查找某段时间某人");
        } catch (Exception e) {
            logger.warning("查找查找某段时间某人失败");
            e.printStackTrace();
        }
        return set;
    }
}
