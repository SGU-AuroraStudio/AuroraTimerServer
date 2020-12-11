package servlet;

import servlet.until.ClientAddr;

import javax.servlet.*;
import java.io.*;
import java.time.LocalTime;

/**
 * Created by hao on 17-2-20.
 */
public class TimerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding("gbk");
        //超过晚上11点以及小于早上6点不予计时
        LocalTime time = LocalTime.now();
        boolean flag = false;
        for (int i = 0; i < ClientAddr.addr.length; i++) {
            if (ClientAddr.addr[i].equals(servletRequest.getRemoteAddr()) || servletRequest.getRemoteAddr().contains(ClientAddr.addr[i])) {
                flag = true;
            }
        }
        if (!(time.getHour() > 22 || time.getHour() < 7) && flag) {
            filterChain.doFilter(servletRequest, servletResponse); //放行
        }
        if (!flag) {
            ServletContext servletContext = servletRequest.getServletContext();
            servletContext.log(servletRequest.getRemoteAddr() + "请求计时失败");
            servletResponse.getWriter().println("请在工作室计时。（出bug了，先检查工作室ip有没有变。\n如果您已经在工作室，请联系管理员。\n）");
        }
    }

    @Override
    public void destroy() {
    }
}
