package aurora.timer.server.dao.impl;

import aurora.timer.server.dao.idao.IUserDataDAO;
import aurora.timer.server.vo.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hao on 17-1-16.
 */
public class UserDataDAOImpl implements IUserDataDAO {
    private Connection coon;
    private PreparedStatement pstmt;

    public UserDataDAOImpl(Connection coon) {
        this.coon = coon;
    }
    @Override
    public boolean doCreate(UserData vo) throws SQLException {
        String sql = "INSERT INTO UserData(nickname,id,telnumber,shorttelnumber," +
                "displayurl,loginstatus,isleave,password) VALUES (?,?,?,?,?,?,?,?)";
        pstmt = coon.prepareStatement(sql);
        pstmt.setString(1, vo.getNickName());
        pstmt.setString(2, vo.getID());
        pstmt.setString(3, vo.getTelNumber());
        pstmt.setString(4, vo.getShortTelNumber());
        pstmt.setString(5, vo.getDisplayURL());
        pstmt.setBoolean(6, vo.getLoginStatus());
        pstmt.setBoolean(7, vo.getIsLeave());
        pstmt.setString(8, vo.getPassWord());
        return pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(UserData vo) throws SQLException {
        String sql = "UPDATE UserData SET nickname=?,telnumber=?,shorttelnumber=?," +
                "displayurl=?,loginstatus=?,isleave=?,password=? WHERE id=?";
        pstmt = coon.prepareStatement(sql);
        pstmt.setString(1, vo.getNickName());
        pstmt.setString(2, vo.getTelNumber());
        pstmt.setString(3, vo.getShortTelNumber());
        pstmt.setString(4, vo.getDisplayURL());
        pstmt.setBoolean(5, vo.getLoginStatus());
        pstmt.setBoolean(6, vo.getIsLeave());
        pstmt.setString(7, vo.getPassWord());
        pstmt.setString(8, vo.getID());
        return pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws SQLException {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM UserData WHERE id IN(");
        Iterator<String> iter = ids.iterator();
        while (iter.hasNext()) {
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length()-1, sql.length()).append(")");
        pstmt = coon.prepareStatement(sql.toString());
        return pstmt.executeUpdate() == ids.size();
    }

    @Override
    public UserData findById(String id) throws SQLException {
        UserData vo = null;
        String sql = "SELECT nickname,telnumber,shorttelnumber," +
                "displayurl,loginstatus,isleave,password FROM UserData WHERE id=?";
        pstmt = coon.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet result = pstmt.executeQuery();
        if (result.next()) {
            vo = new UserData();
            vo.setID(id);
            vo.setNickName(result.getString(1));
            vo.setTelNumber(result.getString(2));
            vo.setShortTelNumber(result.getString(3));
            vo.setDisplayURL(result.getString(4));
            vo.setLoginStatus(result.getBoolean(5));
            vo.setIsLeave(result.getBoolean(6));
            vo.setPassWord(result.getString(7));
        }
        return vo;
    }
}
