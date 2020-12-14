package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.service.iservice.IUserOnlineTimeService;
import aurora.timer.server.vo.UserData;
import aurora.timer.server.vo.UserOnlineTime;
import org.json.simple.JSONObject;
import servlet.until.AdminId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by hao on 17-1-31.
 */
public class FindByIdServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/txt;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        IUserDataService iuds = ServiceFactory.getIUserDataService();
        IUserOnlineTimeService iuots = ServiceFactory.getIUserOnlineTimeService();

        try {
            UserData data = iuds.searchUserById(id);
            UserOnlineTime time = iuots.searchByUnique(id, new Date(System.currentTimeMillis()));

            JSONObject object = new JSONObject();
            if (data != null) {
                object.put("id", data.getID());
                for(String aId: AdminId.adminId) {
                    object.put("isAdmin", String.valueOf(data.getID().equals(aId)));
                    if(data.getID().equals(aId))
                        break;
                }
                object.put("name", data.getNickName());
                object.put("tel", data.getTelNumber());
                object.put("stel", data.getShortTelNumber());
                object.put("disp", data.getDisplayURL());
            }

            if (time != null) {
                object.put("totime", time.getTodayOnlineTime().toString());
                object.put("laslog", time.getLastOnlineTime().getTime());
            }

            resp.getWriter().println(object.toJSONString());
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
