package com.example.oauthclient.config.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;


/***
 *
 * note the filter is a component, which means it will be invoked for every coming ServletRequest
 * */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //HttpServletResponse httpResponse = (HttpServletResponse) response;
        LocalDateTime date = LocalDateTime.now();
        System.err.println("LogFilter: " + date + " - " + httpRequest.getLocalAddr() + ":" + httpRequest.getLocalPort() + httpRequest.getServletPath());
        Enumeration<String> headers = httpRequest.getHeaderNames();
        while(headers.hasMoreElements()) {
            String headerName = (String)headers.nextElement();
            System.out.println("\tHeader: " + headerName + ":" + httpRequest.getHeader(headerName));
        }
        System.out.println("\n\n");
        chain.doFilter(request, response);

    }

}






























































































