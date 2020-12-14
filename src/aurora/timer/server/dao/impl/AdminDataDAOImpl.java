package aurora.timer.server.dao.impl;

import aurora.timer.server.dao.idao.IAdminDataDAO;
import aurora.timer.server.vo.AdminData;

import java.sql.*;

public class AdminDataDAOImpl implements IAdminDataDAO {
    private PreparedStatement pstmt;
    private Connection conn;

    public AdminDataDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doUpdate(AdminData vo) throws SQLException {
        String sql = "UPDATE AdminData SET announcement=?,dutyList=?,freeTimeStart=?," +
                "freeTimeEnd=? WHERE id=1";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, vo.getAnnouncement());
        pstmt.setString(2, vo.getDutylist());
        pstmt.setTime(3, vo.getFreeTimeStart());
        pstmt.setTime(4, vo.getFreeTimeEnd());
        return pstmt.executeUpdate() > 0;
    }

    @Override
    public AdminData findById1() throws Exception {
        String sql = "SELECT announcement,dutyList,freeTimeStart,freeTimeEnd FROM AdminData WHERE id=1";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs= pstmt.executeQuery();
        AdminData vo = new AdminData();
        if(rs.next()){
            vo.setAnnouncement(rs.getString(1));
            vo.setDutylist(rs.getString(2));
            vo.setFreeTimeStart(Time.valueOf(rs.getString(3)));
            vo.setFreeTimeEnd(Time.valueOf(rs.getString(4)));
        }
        return vo;
    }

    @Override
    public String findAnnouncement() throws SQLException {
        String sql = "SELECT announcement FROM AdminData WHERE id=1";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs= pstmt.executeQuery();
        if (rs.next()) {
            return (rs.getString(1));
        }
        return null;
    }

    @Override
    public String findDutyList() throws SQLException {
        String sql = "SELECT dutyList FROM AdminData WHERE id=1";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs= pstmt.executeQuery();
        if (rs.next()) {
            return (rs.getString(1));
        }
        return null;
    }

    @Override
    public Time findFreeTimeStart() throws SQLException {
        String sql = "SELECT freeTimeStart FROM AdminData WHERE id=1";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs= pstmt.executeQuery();
        if (rs.next()) {
            return (rs.getTime(1));
        }
        return null;
    }

    @Override
    public Time findFreeTimeEnd() throws SQLException {
        String sql = "SELECT freeTimeEnd FROM AdminData WHERE id=1";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs= pstmt.executeQuery();
        if (rs.next()) {
            return (rs.getTime(1));
        }
        return null;
    }
}
