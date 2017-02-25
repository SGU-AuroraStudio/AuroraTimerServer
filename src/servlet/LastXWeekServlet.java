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

        IUserOnlineTimeService iuots = ServiceFactory.getIUserOnlineTimeService();
        IUserDataService iuds = ServiceFactory.getIUserDataService();

        Set<UserOnlineTime> set;
        Map<String, Long> map = new HashMap<>();
        JSONObject object = new JSONObject();
        JSONObject temp;
        try {
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

            resp.getWriter().println(object.toJSONString());
            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
