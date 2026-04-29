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

/**
 * Controlador encargado de inicializar la sesión de juego.
 * Recibe los parámetros iniciales (jugadores y caras del dado), configura la
 * estructura de datos circular y redirige el flujo hacia la vista principal
 * del juego.
 * 
 * @author Manuel Salazar
 * @author Sebastian Guzman
 */
@WebServlet(name = "StartServlet", urlPatterns = {"/StartServlet"})
public class StartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Centraliza la lógica de inicio:
     * 1. Captura el número de jugadores y caras del dado desde el formulario.
     * 2. Crea e instancia la {@code ListaCircular} y el objeto {@code Dado}.
     * 3. Almacena los objetos en la sesión del usuario.
     * 4. Prepara los atributos necesarios para la renderización en el JSP.
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
            int numeroJugadores = Integer.parseInt(request.getParameter("numPlayers"));
            int numeroCaras = Integer.parseInt(request.getParameter("numFaces"));

            HttpSession session = request.getSession();

            Dado dado = new Dado(numeroCaras);

            ListaCircular jugadores = null;

            for (int i = 1; i <= numeroJugadores; i++) {
                String nombreJugador = "Jugador " + i;

                if (i == 1) {
                    jugadores = new ListaCircular(nombreJugador);
                } else {
                    jugadores.add(nombreJugador);
                }
            }
            jugadores.next();

            session.setAttribute("ListaJugadores", jugadores);
            session.setAttribute("Dado", dado);

            request.setAttribute("Actual", jugadores.getActual());
            request.setAttribute("ArregloJugadores", jugadores.getAll());

            request.getRequestDispatcher("game.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("index.html?error=datos_invalidos");
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("index.html?error=error_interno");
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
