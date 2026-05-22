package org.migel.trsis.lab1.servlets;

import org.migel.trsis.lab1.model.Game;
import org.migel.trsis.lab1.model.GameRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GameServlet", urlPatterns = {"/games"})
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Game> games = GameRepository.findAll();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Магазин Игр</title>");
            out.println("<style>table, th, td { border: 1px solid black; " +
                        "border-collapse: collapse; padding: 5px; }</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Магазин компьютерных игр</h1>");

            // Форма добавления игры
            out.println("<h3>Добавить новую игру</h3>");
            out.println("<form method='POST' action='" + request.getContextPath() + "/games'>");
            out.println("<input type='hidden' name='action' value='add'>");
            out.println("Название: <input type='text' name='title' required> ");
            out.println("Разработчик: <input type='text' name='developer' required> ");
            out.println("Цена ($): <input type='number' step='0.01' name='price' required> ");
            out.println("Год выпуска: <input type='number' name='releaseYear' required> ");
            out.println("<button type='submit'>Добавить</button>");
            out.println("</form><br>");

            // Таблица с играми
            out.println("<table>");
            out.println("<thead><tr><th>ID</th><th>Название</th><th>Разработчик</th>" +
                        "<th>Цена</th><th>Год</th><th>Действие</th></tr></thead>");
            out.println("<tbody>");

            for (Game game : games) {
                out.println("<tr>");
                out.println("<td>" + game.getId() + "</td>");
                out.println("<td>" + game.getTitle() + "</td>");
                out.println("<td>" + game.getDeveloper() + "</td>");
                out.println("<td>$" + game.getPrice() + "</td>");
                out.println("<td>" + game.getReleaseYear() + "</td>");

                // Форма удаления игры
                out.println("<td>");
                out.println("<form method='POST' action='"
                            + request.getContextPath()
                            + "/games' style='margin:0;'>");
                out.println("<input type='hidden' name='action' value='delete'>");
                out.println("<input type='hidden' name='id' value='" + game.getId() + "'>");
                out.println("<button type='submit'>Удалить</button>");
                out.println("</form>");
                out.println("</td>");

                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String developer = request.getParameter("developer");
            Double price = Double.parseDouble(request.getParameter("price"));
            Integer year = Integer.parseInt(request.getParameter("releaseYear"));

            GameRepository.add(title, developer, price, year);
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            GameRepository.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/games");
    }
}