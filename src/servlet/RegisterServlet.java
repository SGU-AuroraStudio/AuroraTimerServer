package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.impl.UserDataServiceImpl;
import aurora.timer.server.vo.UserData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hao on 17-1-24.
 */
public class RegisterServlet extends HttpServlet {
    public RegisterServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        boolean flag = false;
        UserData vo = new UserData(name,id,pwd);
        UserDataServiceImpl udsi = (UserDataServiceImpl) ServiceFactory.getIUserDataService();
        try {
            flag = udsi.register(vo);
        } catch (Exception e) {
            flag = false;
//            e.printStackTrace();
        }
        if (flag) {
            resp.getWriter().println("<!DOCTYPE html>\n");
            resp.getWriter().println("<html>\n<head>\n");
            resp.getWriter().println("<meat http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\";></meat>\n");
            resp.getWriter().println("<title>注册成功</title>\n</head>\n");
            resp.getWriter().println("<body>\n<h1>注册成功, "+ name + "</h1>\n</body>\n</html>\n");
        } else {
            resp.getWriter().println("<!DOCTYPE html>\n");
            resp.getWriter().println("<html>\n<head>\n");
            resp.getWriter().println("<meat http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\";></meat>\n");
            resp.getWriter().println("<title>注册失败</title>\n</head>\n");
            resp.getWriter().println("<body>\n<h1>注册失败</h1>\n</body>\n</html>\n");
        }
    }

}
