package aurora.timer.server.service;

//import com.mysql.jdbc.Connection;

import com.mysql.jdbc.Driver; //MySql5 用
//import com.mysql.cj.jdbc.ConnectionImpl;
//import com.mysql.cj.jdbc.Driver; //MySql8 用

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by hao on 17-1-17.
 */
public class DBConnection {
    static Connection conn = null;
    static final String ADDRESS = "localhost";
    static final String PORT = "3306";
    static final String DBNAME = "timer";
    //我到笔记本参数
//    static final String USER = "root"; //root
//    static final String PASSWORD = "root"; //Zhouhao6371.
//    static final String URL = "jdbc:mysql://" + ADDRESS + ":" + PORT + "/" + DBNAME + "?" +
//            "user=" + USER + "&password=" + PASSWORD + "&useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai"; //MySql 8 要加serverTimezone

    //服务器参数
    static final String USER = "hao";
    static final String PASSWORD = "797987";
    static final String URL = "jdbc:mysql://" + ADDRESS + ":" + PORT + "/" + DBNAME + "?" +
            "user=" + USER + "&password=" + PASSWORD + "&useUnicode=yes&amp;&characterEncoding=utf8&useSSL=false"; //MySql 5.


    /**
     * 每次获取一个新的Connection实例
     *
     * @return 一个数据库Connection
     * @throws SQLException 连接数据库失败的话会抛出
     */
    public static Connection getConnection() throws SQLException {
        Logger logger = Logger.getLogger("db connection");
        try {
            new Driver();
            conn = (Connection) DriverManager.getConnection(URL);
            logger.fine("成功连接到数据库");
        } catch (SQLException e) {
            logger.warning("连接数据库失败" + e);
        }
        return conn;
    }
//// 连接测试
//   public static void main(String args[]) throws SQLException{
//        Connection c = new DBConnection().getConnection();
//        c.close();
//    }
}
