package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IUserOnlineTimeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hao on 17-1-30.
 */
public class TimerServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        IUserOnlineTimeService iuots = ServiceFactory.getIUserOnlineTimeService();
        boolean flag = false;
        try {
            flag = iuots.addTime(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag) {
            resp.getWriter().println("true");
        } else {
            resp.getWriter().println("false");
        }
    }
}
