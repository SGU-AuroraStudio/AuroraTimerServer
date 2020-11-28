package servlet;

import aurora.timer.server.factory.ServiceFactory;
import aurora.timer.server.service.iservice.IUserDataService;
import aurora.timer.server.vo.UserData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;


import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


public class BgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        IUserDataService iuds = ServiceFactory.getIUserDataService();
        try {
            // 检查密码
            if (iuds.searchUserById(id).getPassWord().equals(password)) {
                InputStream bg = iuds.searchBgById(id);
                if (bg == null) {
                    resp.getWriter().write("false"); // Writer和流不能同时用，但是这里不同时
                } else {
                    // 转为byte用流输出
                    byte bgByte[] = new byte[bg.available()];
                    bg.read(bgByte);
                    resp.getOutputStream().write(bgByte);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/txt;charset=UTF-8");
        boolean flag = false;
        IUserDataService iuds = ServiceFactory.getIUserDataService();
        try {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> list = upload.parseRequest(new ServletRequestContext(req));
            String id = null;
            String password = null;
            for (FileItem item : list) {
                // multipart 每个item的name
                String name = item.getFieldName();
                // 客户端传来的id和password
                if (name.equals("id")) {
                    id = item.getString();
                } else if (name.equals("password")) {
                    password = item.getString();
                } else if (id != null && password != null && name.contains("bg")) {
                    UserData userData = iuds.searchUserById(id);
                    // 密码正确才可以改壁纸
                    if (userData.getPassWord().equals(password)) {
                        InputStream in = item.getInputStream();
                        flag = iuds.updateBgById(id, in);
                    }
//                    //写入数据库和保存图片只能同时用一个，(inputstream被读取就没了)
//                    String path = "d://test1.png";
//                    File file = new File(path);
//                    BufferedInputStream fi = new BufferedInputStream(in);
//                    FileOutputStream fo = new FileOutputStream(file);
//                    int f;
//                    while ((f = fi.read()) != -1) {
//                        fo.write(f);
//                    }
//                    fo.flush();
//                    fo.close();
//                    fi.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag)
            resp.getWriter().println("true");
        else
            resp.getWriter().println("false");
    }
}
