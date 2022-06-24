package test01;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncoderFilter implements Filter {
    ServletContext context = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 호출");
        context = filterConfig.getServletContext();
    }

    @Override
    public void destroy() {
        System.out.println("destroy 호출");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("dofilter 호출");
        servletRequest.setCharacterEncoding("utf-8");
        long begin = System.currentTimeMillis();
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        System.out.println("작업시간 = " + (end-begin) + "ms");



    }
}
