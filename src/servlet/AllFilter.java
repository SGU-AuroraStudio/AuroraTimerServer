package servlet;

import servlet.until.ClientAddr;

import javax.servlet.*;
import java.io.*;
import java.time.LocalTime;

/**
 * Created by hao on 17-2-20.
 */
public class AllFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //超过晚上11点以及小于早上6点不予计时
        LocalTime time = LocalTime.now();
        boolean flag = false;
        for (int i = 0; i < ClientAddr.addr.length; i ++) {
            if (ClientAddr.addr[i].equals(servletRequest.getRemoteAddr())) {
                flag = true;
            }
        }
        if (!(time.getHour() > 22 || time.getHour() < 7) && flag) {
            filterChain.doFilter(servletRequest, servletResponse); //放行
        }
    }

    @Override
    public void destroy() {
    }
}
