package com.oooo.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by aaaa on 2016/9/21.
 */
public class TestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter before");
        chain.doFilter(req, resp);
        System.out.println("filter after");
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("filter init");
    }

}
