/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.udistrital.controler;

import co.edu.udistrital.model.Dado;
import co.edu.udistrital.model.ListaCircular;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Acer-Pc
 */
@WebServlet(name = "ThrowDiceServlet", urlPatterns = {"/ThrowDiceServlet"})
public class ThrowDiceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();

            String actual = request.getParameter("jugadorActual");

            Dado dado = (Dado) session.getAttribute("Dado");
            ListaCircular jugadores = (ListaCircular) session.getAttribute("ListaJugadores");

            int valor = dado.lanzar();
            boolean fueEliminado = false;

            if ((valor % 2) == 1) {
                fueEliminado = jugadores.remove(actual);
                request.setAttribute("MensajeEliminacion", "¡" + actual + " fue eliminado!");
            }

            if (!fueEliminado) {
                jugadores.next();
            }

            List<String> activos = jugadores.getAll();

            if (activos.size() == 1) {
               //request.setAttribute("MensajeGanador", "¡" + activos.get(0) + " ha sobrevivido y es el ganador!");
            }

            session.setAttribute("ListaJugadores", jugadores);
            request.setAttribute("ResultadoDado", valor);

            if (!activos.isEmpty()) {
                request.setAttribute("Actual", jugadores.getActual());
            }
            request.setAttribute("ArregloJugadores", activos);

            request.getRequestDispatcher("game.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=error_juego");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
