package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.service.iservice.IUserOnlineTimeService;
import aurora.timer.server.vo.UserOnlineTime;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by hao on 17-1-30.
 */
public class LastXWeekServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int lastX = Integer.valueOf(req.getParameter("x"));

        //---------------------准备工作--------------------
        Date nowDate = new Date(System.currentTimeMillis()); //系统的Date

        Date termStart = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(nowDate); //系统年份，获取到的是+1900的
        Date termStart1 = new Date(Integer.valueOf(year)-1900,8,1); //月份要-1，传入8，获得的是9月份
        Date termStart2 = new Date(Integer.valueOf(year)-1900,1,1);
        if(nowDate.after(termStart1) || nowDate.before(termStart2)){ // 9月份之后，或者，2月份之前，肯定是第一学期
            termStart=termStart1;
        }
        else{
            termStart=termStart2;
        }
        //--------------------准备工作结束--------------------

        IUserOnlineTimeService iuots = ServiceFactory.getIUserOnlineTimeService();
        IUserDataService iuds = ServiceFactory.getIUserDataService();
        Set<UserOnlineTime> set;
        Set<UserOnlineTime> termSet;
        Map<String, Long> map = new HashMap<>();
        Map<String, Long> termMap = new HashMap<>();
        JSONObject object = new JSONObject();
        JSONObject temp;
        try {
            //第x周时间
            set = iuots.lastXWeekData(lastX);
            Iterator<UserOnlineTime> setIt = set.iterator();
            UserOnlineTime vo;
            while (setIt.hasNext()) {
                vo = setIt.next();
                Long t;
                if ((t = map.get(vo.getID())) != null) {
                    map.put(vo.getID(), t + vo.getTodayOnlineTime());
                } else {
                    map.put(vo.getID(), vo.getTodayOnlineTime());
                }
            }
            Iterator<String> mapIt = map.keySet().iterator();
            while (mapIt.hasNext()) {
                String s = mapIt.next();
                temp = new JSONObject();
                temp.put("id", s);
                temp.put("time", map.get(s).toString());
                temp.put("name", iuds.searchUserById(s).getNickName());

                object.put(s, temp);
            }

            //学期时间
            termSet = iuots.searchByFromDate2Today(termStart);
            Iterator<UserOnlineTime> termSetIt = termSet.iterator();
            while (termSetIt.hasNext()) {
                vo = termSetIt.next();
                Long t;
                if ((t = termMap.get(vo.getID())) != null) {
                    termMap.put(vo.getID(), t + vo.getTodayOnlineTime());
                } else {
                    termMap.put(vo.getID(), vo.getTodayOnlineTime());
                }
            }
            Iterator<String> termMapIt = termMap.keySet().iterator();
            while (termMapIt.hasNext()) {
                String s = termMapIt.next();
                temp = (JSONObject) object.get(s);
                if(temp==null) {//本周没上线
//                    temp = new JSONObject();
//                    temp.put("id", s);
//                    temp.put("name", iuds.searchUserById(s).getNickName());
                    continue;
                }
                temp.put("termTime", termMap.get(s).toString());
                object.put(s, temp);
            }

            resp.getWriter().println(object.toJSONString());
            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
