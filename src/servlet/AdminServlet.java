package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IAdminDataService;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.vo.AdminData;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Time;

public class AdminServlet extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json;charset=UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        String id = req.getParameter("id");
//        System.out.println("service");
//
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("x") == null)
            returnAdminData(req, resp);
        else if (req.getParameter("x").equals("freeTime")) {
            resp.getWriter().println(isFreeTime(req, resp)); //返回是否在放挂时间内
            resp.getWriter().close();
        } else
            returnAdminData(req, resp);
    }

    /**
     * 返回管理员数据,返回的数据里时间是long类型。
     * 即SQL里Time类型的数据在传递的时候用Long
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:检查id是否为管理员
        resp.setContentType("text/txt;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8"); //注意：req也要设置编码，否者中文会乱码

        boolean flag = false;
        IAdminDataService iads = ServiceFactory.getIAdminDataService();
        IUserDataService iuds = ServiceFactory.getIUserDataService();
        try {
            AdminData data = new AdminData();
            data.setAnnouncement(req.getParameter("announcement"));
            data.setDutylist(req.getParameter("dutyList"));
            System.out.println(data.getDutylist());
            data.setFreeTimeStart(new Time(Long.parseLong(req.getParameter("freeTimeStart"))));
            data.setFreeTimeEnd(new Time(Long.parseLong(req.getParameter("freeTimeEnd"))));

            flag = iads.updateAdminData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag)
            resp.getWriter().println("true");
        else
            resp.getWriter().println("false");
    }

    private void returnAdminData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/txt;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        IAdminDataService iads = ServiceFactory.getIAdminDataService();
        try {
            AdminData data = iads.getAdminData();
            JSONObject object = new JSONObject();
            if (data != null) {
                object.put("announcement", data.getAnnouncement());
                object.put("dutyList", data.getDutylist());
                object.put("freeTimeStart", data.getFreeTimeStart().getTime());
                object.put("freeTimeEnd", data.getFreeTimeEnd().getTime());
            }
            resp.getWriter().println(object.toJSONString());
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isFreeTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAdminDataService iads = ServiceFactory.getIAdminDataService();
        try {
            AdminData adminData = iads.getAdminData();
            // 系统时间和 数据库存的 设置的时间对比
            long systemCurrentTimeLong = System.currentTimeMillis();
            return systemCurrentTimeLong > adminData.getFreeTimeStart().getTime() && systemCurrentTimeLong < adminData.getFreeTimeEnd().getTime() + 1200000; //向后移20分钟，防止在例如17：01分的时候返回true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
