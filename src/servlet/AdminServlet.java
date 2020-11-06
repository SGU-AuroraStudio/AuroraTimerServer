package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IAdminDataService;
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
        resp.setContentType("text/txt;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        IAdminDataService iads = ServiceFactory.getIAdminDataService();
        try {
            AdminData data = iads.getAdminData();
            JSONObject object = new JSONObject();
            if(data!=null){
                object.put("announcement",data.getAnnouncement());
                object.put("dutyList",data.getDutylist());
                object.put("freeTimeStart",data.getFreeTimeStart().getTime());
                object.put("freeTimeEnd",data.getFreeTimeEnd().getTime());
            }
            resp.getWriter().println(object.toJSONString());
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:检查id是否为管理员
        resp.setContentType("text/txt;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        boolean flag=false;
        IAdminDataService iads = ServiceFactory.getIAdminDataService();
        try {
            AdminData data = new AdminData();
            data.setAnnouncement(req.getParameter("announcement"));
            data.setDutylist(req.getParameter("dutyList"));
            data.setFreeTimeStart(new Time(Long.parseLong(req.getParameter("freeTimeStart"))));
            data.setFreeTimeEnd(new Time(Long.parseLong(req.getParameter("freeTimeEnd"))));

            flag = iads.updateAdminData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag)
            resp.getWriter().println("true");
        else
            resp.getWriter().println("false");
    }
}
