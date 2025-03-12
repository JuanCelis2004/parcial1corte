/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.reservationModelJpaController;

/**
 *
 * @author HOGAR
 */
@WebServlet(name = "eliminarReservaServlet", urlPatterns = {"/eliminarReservaServlet"})
public class eliminarReservaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    //Instanciamos global la clase para poder hacer uso de los metodos que contiene 
    reservationModelJpaController modelo = new reservationModelJpaController();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //estamos parseando un String a un Int para obtener el id_ de la reserva del formulario 
        int ideliminar = Integer.parseInt(request.getParameter("id_"));
        //Llama el moto eliminar que es encargado de eliminar las reservas de la base de datos
        eliminar(ideliminar);
        //despeus de ser eliminada nos redirige a "reservas.jsp"
        response.sendRedirect("reservas.jsp");

    }

    //Estamos llamando el metodo eliminar para poder ser usando en el doPost.
    //que por seguridad nos indica que se implementado en un try catch
    public void eliminar(int id_) {
        try {
            modelo.destroy(id_);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
