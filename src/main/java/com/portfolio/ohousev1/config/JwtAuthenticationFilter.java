package com.portfolio.ohousev1.config;

import jakarta.servlet.*;

import java.io.IOException;

public class JwtAuthenticationFilter implements Filter {
    public JwtAuthenticationFilter(String secret) {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
