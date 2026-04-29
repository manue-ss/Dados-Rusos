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
 * Controlador encargado de gestionar la mecánica principal del juego.
 * Administra el lanzamiento de datos, la eliminación de jugadores basada en el
 * resultado y la determinación del estado de victoria.
 * 
 * @author Manuel Salazar
 * @author Sebastian Guzman
 */
@WebServlet(name = "ThrowDiceServlet", urlPatterns = {"/ThrowDiceServlet"})
public class ThrowDiceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Procesa la lógica de cada turno en el juego de la siguiente manera:
     * 1. Recupera los objetos {@code Dado} y {@code ListaCircular} de la 
     * sesión.
     * 2. Genera el valor del dado y aplica la regla de eliminación (si el
     * jugador es impar).
     * 3. Gestiona el avance de turno si no hubo eliminación.
     * 4. Verifica si solo queda un jugador activo para declarar un ganador.
     * 5. Actualiza la vista {@code game.jps} o redirige a {@code winnerPage.jsp}.
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
                request.setAttribute("Ganador", jugadores.getActual());
                request.getRequestDispatcher("winnerPage.jsp").forward(request, response);

                return;
            }

            session.setAttribute("ListaJugadores", jugadores);
            request.setAttribute("ResultadoDado", valor);

            if (!activos.isEmpty()) {
                request.setAttribute("Actual", jugadores.getActual());
            }
            request.setAttribute("ArregloJugadores", activos);

            request.getRequestDispatcher("game.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
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
