//package servlet;
//
//import aurora.timer.server.factory.ServiceFactory;
//import aurora.timer.server.service.iservice.IUserDataService;
//import aurora.timer.server.service.iservice.IUserOnlineTimeService;
//import aurora.timer.server.vo.UserOnlineTime;
//import org.json.simple.JSONObject;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class TermTotalServlet extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/txt;charset=UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//
////        //获取传过来的dateStart yyyy-MM-dd
////        String dateStartStr = req.getParameter("termStart");
////        if(dateStartStr==null) return ;
////        //转long再转sql.Date——因为util.Date不能直接转sql.Date
////        long dateStartLong = 0l;
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
////        try {
////            dateStartLong = simpleDateFormat.parse(dateStartStr).getTime();
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////        Date termStart = new Date(dateStartLong);
//        // 1598889600000 2020-09-01
//
//        //---------------------准备工作--------------------
//        Date nowDate = new Date(System.currentTimeMillis()); //系统的Date
//
//        Date termStart = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        String year = sdf.format(nowDate); //系统年份，获取到的是+1900的
//        Date termStart1 = new Date(Integer.valueOf(year)-1900,8,1); //月份要-1，传入8，获得的是9月份
//        Date termStart2 = new Date(Integer.valueOf(year)-1900,1,1);
//        if(nowDate.after(termStart1) || nowDate.before(termStart2)){ // 9月份之后，或者，2月份之前，肯定是第一学期
//            termStart=termStart1;
//        }
//        else{
//            termStart=termStart2;
//        }
//        //--------------------准备工作结束--------------------
//        IUserOnlineTimeService iuots = ServiceFactory.getIUserOnlineTimeService();
//        IUserDataService iuds = ServiceFactory.getIUserDataService();
//        Set<UserOnlineTime> set;
//        Map<String, Long> map = new HashMap<>();
//        JSONObject object = new JSONObject();
//        JSONObject temp;
//        try {
//            set = iuots.searchByFromDate2Today(termStart);
//            Iterator<UserOnlineTime> setIt = set.iterator();
//            UserOnlineTime vo;
//            // 这个循环把一个用户查询到的时间加起来, map<id,time>
//            while (setIt.hasNext()) {
//                vo = setIt.next();
//                Long t;
//                if ((t = map.get(vo.getID())) != null) {
//                    map.put(vo.getID(), t + vo.getTodayOnlineTime());
//                } else {
//                    map.put(vo.getID(), vo.getTodayOnlineTime());
//                }
//            }
//
//            Iterator<String> mapIt = map.keySet().iterator();
//            while (mapIt.hasNext()) {
//                String s = mapIt.next();
//                temp = new JSONObject();
//                temp.put("id", s);
//                temp.put("termTime", map.get(s).toString());
//                temp.put("name", iuds.searchUserById(s).getNickName());
//
//                object.put(s, temp);
//            }
//
//            resp.getWriter().println(object.toJSONString());
//            resp.getWriter().flush();
//            resp.getWriter().close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
