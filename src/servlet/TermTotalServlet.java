package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.service.iservice.IUserOnlineTimeService;
import aurora.timer.server.vo.UserOnlineTime;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TermTotalServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/txt;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //获取传过来的dateStart
        String dateStartStr = req.getParameter("dateStart");
        if(dateStartStr==null) return ;
        long dateStartLong = 0l;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        try {
            dateStartLong = simpleDateFormat.parse(dateStartStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateStart = new Date(dateStartLong);
        System.out.println(dateStart);
        IUserOnlineTimeService iuots = ServiceFactory.getIUserOnlineTimeService();
        IUserDataService iuds = ServiceFactory.getIUserDataService();

        Set<UserOnlineTime> set;
        Map<String, Long> map = new HashMap<>();
        JSONObject object = new JSONObject();
        JSONObject temp;
        try {
            set = iuots.searchByFromDate2Today(dateStart);
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

            resp.getWriter().println(object.toJSONString());
            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
