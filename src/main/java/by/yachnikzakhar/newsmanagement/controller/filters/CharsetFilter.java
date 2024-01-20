package by.yachnikzakhar.newsmanagement.controller.filters;
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "utf-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        next.doFilter(request, response);
    }

    public void destroy() {
    }
}