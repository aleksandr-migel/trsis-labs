package org.migel.trsis.lab1.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;

@WebFilter("/games")
public class GameFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        final StringWriter writer = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(writer);
        HttpServletResponseWrapper newResponseWrapper =
                new GameResponseWrapper((HttpServletResponse) response, printWriter);

        chain.doFilter(request, newResponseWrapper);
        printWriter.flush();

        StringBuffer httpText = writer.getBuffer();

        int idx = httpText.indexOf("</body>");
        if (idx != -1) {
            httpText.insert(idx, "<p><i>Страница сгенерирована фильтром в: " + Instant.now() + "</i></p>");
        }

        response.getWriter().write(httpText.toString());
    }
}