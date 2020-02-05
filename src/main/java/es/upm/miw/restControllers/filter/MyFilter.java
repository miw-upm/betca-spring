package es.upm.miw.restControllers.filter;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // pre process
        LogManager.getLogger(this.getClass().getName()).debug(">>> FILTER MyFilter...");
        // passing chain
        filterChain.doFilter(request, response);
        // post process
    }
}
