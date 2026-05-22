package org.migel.trsis.lab1.filters;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.PrintWriter;

public class GameResponseWrapper extends HttpServletResponseWrapper {
    private final PrintWriter printWriter;

    public GameResponseWrapper(HttpServletResponse response, PrintWriter printWriter) {
        super(response);
        this.printWriter = printWriter;
    }

    @Override
    public PrintWriter getWriter() {
        return printWriter;
    }
}
