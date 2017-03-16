package servlet;

import sun.rmi.runtime.Log;

import javax.servlet.*;
import java.io.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.logging.Logger;

/**
 * Created by hao on 17-2-20.
 */
public class AllFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //超过晚上10点以及小于早上7点不予计时
//        LocalTime time = LocalTime.now();
//        boolean flag = false;
//        for (int i = 0; i < ClientAddr.addr.length; i ++) {
//            if (ClientAddr.addr[i].equals(servletRequest.getRemoteAddr())) {
//                flag = true;
//            }
//        }
//        if (!(time.getHour() > 22 || time.getHour() < 7) && flag) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//        throw new ServletException();
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
