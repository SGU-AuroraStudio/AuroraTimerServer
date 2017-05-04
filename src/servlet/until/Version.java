package servlet.until;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

/**
 * Created by hao on 17-4-18.
 */
public class Version extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Properties version = new Properties();
        version.load(getClass().getResourceAsStream("version.properties"));

        try {
            JSONObject verObject = new JSONObject();
            verObject.put("version", version.get("version"));
            resp.getWriter().println(verObject.toString());
        } catch (Exception e) {
            resp.getWriter().println("error");
        }
        resp.getWriter().close();
    }
}
