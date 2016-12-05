package aurora.timer.server.vo;

import com.sun.xml.internal.ws.developer.Serialization;

/**
 * 用户的
 * Created by hao on 16-12-1.
 */
public class UserLoginInfo {
    private String ID;
    private String passWord;

    public String getID() {
        return ID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
