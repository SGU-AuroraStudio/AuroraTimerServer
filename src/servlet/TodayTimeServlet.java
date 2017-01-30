package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IUserOnlineTimeService;
import aurora.timer.server.vo.UserOnlineTime;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hao on 17-1-30.
 */
public class TodayTimeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        IUserOnlineTimeService iuots = ServiceFactory.getIUserOnlineTimeService();
        Set<UserOnlineTime> set;
        JSONObject object = new JSONObject();
        JSONObject temp;
        try {
            set = iuots.todayData();
            Iterator<UserOnlineTime> iterator = set.iterator();
            UserOnlineTime vo;
            while (iterator.hasNext()) {
                vo = iterator.next();
                temp = new JSONObject();
                temp.put("id", vo.getID());
                temp.put("time", vo.getTodayOnlineTime().toString());
                object.put(vo.getID(), temp);
            }

            resp.getWriter().println(object.toJSONString());
            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
