/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.reservationModel;
import persistence.reservationModelJpaController;

/**
 *
 * @author HOGAR
 */
@WebServlet(name = "listaReservaServlet", urlPatterns = {"/listaReservaServlet"})
public class listaReservaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    //de igualmanera vamos a instaciar la clase para poder hacer uso de los metodos
    reservationModelJpaController ModelJPA = new reservationModelJpaController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public void traerReservas(HttpServletRequest request) {

        //Acontinuacion llamo el metodo listar() donde se guardara la lista en la variable listaR
        //donde mas adelante la usaremos para alamcenar en la sesion y mostrar en las vistas
        List<reservationModel> listarRes = listar();

        // Guardar la lista en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("listaReservas", listarRes);
    }

    //Estamos llamando el metodo listar para poder ser usando en el doPost
    public List<reservationModel> listar() {
        return ModelJPA.findreservationModelEntities();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
