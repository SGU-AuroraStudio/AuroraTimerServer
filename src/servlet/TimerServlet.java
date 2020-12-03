package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.service.iservice.IUserOnlineTimeService;
import aurora.timer.server.vo.UserData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hao on 17-1-30.
 */
public class TimerServlet extends HttpServlet {
    private static final double ALLOW_VER = 4.4;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String ver = req.getParameter("ver");
        // 检查版本
        if (ver == null || Double.parseDouble(ver)<ALLOW_VER){
            resp.getWriter().println("请使用版本大于"+ALLOW_VER+"的计时器\n建议重启检测是否有更新版本");
            return;
        }
        if (id == null) {
            resp.getWriter().println("false");
            return;
        }

        IUserOnlineTimeService iuots = ServiceFactory.getIUserOnlineTimeService();
        IUserDataService iuds = ServiceFactory.getIUserDataService();
        boolean flag = false;
        try {
            if (iuds.searchUserById(id) != null)
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
