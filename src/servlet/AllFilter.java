package servlet;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Time;
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
        //超过晚上10点以及小于早上7点不予计时
        LocalTime time = LocalTime.now();
        if (! (time.getHour() > 22 || time.getHour() < 7)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
