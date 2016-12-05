package aurora.timer.server.vo;

import com.sun.xml.internal.ws.developer.Serialization;

import java.sql.Date;

/**
 * Created by hao on 16-12-1.
 */
public class UserOnlineTime {
    private String ID;
    private Date lastLoginTime;
    private Long todayOnlineTime; //存放今天在线秒数

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setTodayOnlineTime(Long todayOnlineTime) {
        this.todayOnlineTime = todayOnlineTime;
    }

    public String getID() {
        return ID;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public Long getTodayOnlineTime() {
        return todayOnlineTime;
    }
}
