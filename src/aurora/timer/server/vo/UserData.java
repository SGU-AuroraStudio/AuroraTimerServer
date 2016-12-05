package aurora.timer.server.vo;

import com.sun.xml.internal.ws.developer.Serialization;

import java.sql.Date;

/**
 * 用户的基本信息,还要实现Serialization 以及加备注
 * Created by hao on 16-12-1.
 */
public class UserData {
    private String actualName;
    private String nickName;
    private String ID;
    private String telNumber;
    private String shortTelNumber;
    private String displayURL;
    private Date birthday; //使用 年月日(int) 格式赋值
    private boolean loginStatus; //是不是已经登录
    private boolean isLeave; //是不是离开了

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public boolean isLoginStatus() {

        return loginStatus;
    }

    public void setLeave(boolean leave) {
        isLeave = leave;
    }

    public boolean isLeave() {

        return isLeave;
    }

    public String getActualName() {
        return actualName;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNickName() {
        return nickName;
    }

    public String getID() {
        return ID;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getShortTelNumber() {
        return shortTelNumber;
    }

    public String getDisplayURL() {
        return displayURL;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setShortTelNumber(String shortTelNumber) {
        this.shortTelNumber = shortTelNumber;
    }

    public void setDisplayURL(String displayURL) {
        this.displayURL = displayURL;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
