package aurora.timer.server.dao.impl;

import aurora.timer.server.dao.idao.IUserOnlineTimeDAO;
import aurora.timer.server.vo.UserOnlineTime;
import com.mysql.jdbc.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by hao on 17-1-16.
 */
public class UserOnlineTimeDAOImpl implements IUserOnlineTimeDAO {
    private Connection conn;
    private PreparedStatement pstmt;

    public UserOnlineTimeDAOImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * 增加一条一个人一天的时间记录
     * @param vo 增加的时间记录表
     * @return 成功则返回true，否则返回false
     * @throws SQLException
     */
    public boolean doCreate(UserOnlineTime vo) throws SQLException {
        String sql = "INSERT INTO UserOnlineTime(id,todaydate,lastonlinetime," +
                "todayonlinetime) VALUES(?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, vo.getID());
        pstmt.setDate(2, vo.getTodayDate());
        pstmt.setTime(3, vo.getLastOnlineTime());
        pstmt.setLong(4, vo.getTodayOnlineTime());
        return pstmt.executeUpdate() > 0;
    }

    /**
     * 更新一条UserOnlineTime的记录
     * @param vo 需要更新的事件记录表
     * @return 成功则返回true，否则返回false
     * @throws SQLException
     */
    public boolean doUpdate(UserOnlineTime vo) throws SQLException{
        String sql = "UPDATE UserOnlineTime SET todayonlinetime=?,lastonlinetime=? " +
                "WHERE todaydate=?&&id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, vo.getTodayOnlineTime());
        pstmt.setTime(2, vo.getLastOnlineTime());
        pstmt.setDate(3, vo.getTodayDate());
        pstmt.setString(4, vo.getID());
        return pstmt.executeUpdate() > 0;
    }

    /**
     * 用id检索删除时间记录
     * @param id 需要删除全部记录的id
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean doRemoveById(String id) throws SQLException {
        String sql = "DELETE FROM UserOnlineTime WHERE id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate() > 0;
    }

    /**
     * 使用日期删除时间记录
     * @param date 需要删除全部记录的日期
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean doRemoveByData(Date date) throws Exception {
        String sql = "DELETE FROM UserOnlineTime WHERE data=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, date);
        return pstmt.executeUpdate() > 0;
    }

    /**
     * 删除一个用户在某一天的时间记录
     * @param id   需要删除一条记录的id
     * @param date 需要删除的那一日期
     * @return 成功则返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean doRemoveUnique(String id, Date date) throws SQLException {
        String sql = "DELETE FROM UserOnlineTime WHERE id=? && date=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setDate(2, date);
        return pstmt.executeUpdate() > 0;
    }

    /**
     * 根据id查找一个用户的在线记录
     * @param id 用户的id
     * @return 查找到的集合
     * @throws Exception
     */
    @Override
    public Set<UserOnlineTime> findById(String id) throws SQLException {
        UserOnlineTime vo = null;
        String sql = "SELECT todaydate,lastonlinetime,todayonlinetime FROM UserOnlineTime WHERE id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs= pstmt.executeQuery();
        Set<UserOnlineTime> set = new HashSet<>();
        if (rs.next()) {
            vo = new UserOnlineTime();
            vo.setID(id);
            vo.setTodayDate(rs.getDate(1));
            vo.setLastOnlineTime(rs.getTime(2));
            vo.setTodayOnlineTime(rs.getLong(3));
            set.add(vo);
        }
        return set;
    }

    /**
     * 根据日期查找在线记录
     * @param date 需要查找的日期
     * @return 查找到的所有vo的集合
     * @throws Exception
     */
    @Override
    public  Set<UserOnlineTime> findByData(Date date) throws SQLException {
        UserOnlineTime vo = null;
        String sql = "SELECT id,lastonlinetime,todayonlinetime FROM UserOnlineTime WHERE todaydate=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, date);
        ResultSet rs= pstmt.executeQuery();
        Set<UserOnlineTime> set = new HashSet<>();
        while (rs.next()) {
            vo = new UserOnlineTime();
            vo.setTodayDate(date);
            vo.setID(rs.getString(1));
            vo.setLastOnlineTime(rs.getTime(2));
            vo.setTodayOnlineTime(rs.getLong(3));
            set.add(vo);
        }
        return set;
    }

    /**
     * 根据用户和日期查找在线记录
     * @param id   需要查找的id
     * @param date 需要查找的日期
     * @return 成功则返回查找结果，否则返回null
     * @throws Exception
     */
    @Override
    public UserOnlineTime findByUnique(String id, Date date) throws SQLException {
        UserOnlineTime vo = null;
        String sql = "SELECT lastonlinetime,todayonlinetime FROM UserOnlineTime " +
                "WHERE id=?&&todaydate=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setDate(2, date);
        ResultSet rs= pstmt.executeQuery();
        if (rs.next()) {
            vo = new UserOnlineTime();
            vo.setID(id);
            vo.setTodayDate(date);
            vo.setLastOnlineTime(rs.getTime(1));
            vo.setTodayOnlineTime(rs.getLong(2));
        }
        return vo;
    }
}
