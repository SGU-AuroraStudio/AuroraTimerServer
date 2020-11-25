package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IAdminDataService;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.vo.AdminData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;

public class BgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/txt;charset=UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        req.setCharacterEncoding("UTF-8"); //注意：req也要设置编码，否者中文会乱码

        boolean flag = false;
        IUserDataService iuds = ServiceFactory.getIUserDataService();
        try {
            InputStream inputStream = req.getInputStream();
            byte[] bgByte = new byte[inputStream.available()];
            inputStream.read(bgByte);
            int bytes = bgByte.length-1;
            while(bytes>=0){
                System.out.print(bgByte[bytes--]);
            }

            System.out.println(inputStream.available());
            System.out.println(bgByte.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag)
            resp.getWriter().println("true");
        else
            resp.getWriter().println("false");
    }
}
