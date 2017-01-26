package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.vo.UserData;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hao on 17-1-25.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        String params = sb.toString();

        Object get = JSONValue.parse(params);
        JSONObject object = (JSONObject) get;
        String id = (String) object.get("id");
        String pwd = (String) object.get("pwd");

        IUserDataService iuds = ServiceFactory.getIUserDataService();
        UserData vo = null;
        try {
            vo = iuds.searchUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (vo != null && vo.getPassWord().equals(pwd)) {
            resp.getWriter().println("true");
        } else {
            resp.getWriter().println("false");
        }
        resp.flushBuffer();
    }
}
