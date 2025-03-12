/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.reservationModel;
import persistence.reservationModelJpaController;

/**
 *
 * @author HOGAR
 */
@WebServlet(name = "reservaServlet", urlPatterns = {"/reservaServlet"})
public class reservaServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    //instanciamos global la clase para poder hacer uso de los metodos que contiene 
    reservationModelJpaController reservaJpa = new reservationModelJpaController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //en este servlet vamos a crear una reserva
        
        // Variables para traer los datos
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String espacio = request.getParameter("espacio");
        String duracionStr = request.getParameter("duracion");

        // Variable para el mensaje de error o éxito
        String mensaje = null; 

        // acontinuacion encontraremos las validaciones
        
        // Validar que los campos no estén vacíos
        if (nombre == null || nombre.trim().isEmpty()
                || fecha == null || fecha.trim().isEmpty()
                || espacio == null || espacio.trim().isEmpty()
                || duracionStr == null || duracionStr.trim().isEmpty()) {

            mensaje = "Todos los campos son obligatorios.";
            request.setAttribute("error", mensaje);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Validar la fecha
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_parse = null;
        Date fechaActual = new Date(); // Fecha de hoy

        try {
            fecha_parse = formato.parse(fecha);
            if (fecha_parse.before(fechaActual)) {
                mensaje = "No puedes seleccionar una fecha anterior a hoy.";
                request.setAttribute("error", mensaje);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        } catch (ParseException ex) {
            Logger.getLogger(reservaServlet.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Formato de fecha inválido.";
            request.setAttribute("error", mensaje);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Validar duración entre 1 y 6 horas
        int duracion;
        try {
            duracion = Integer.parseInt(duracionStr);
            if (duracion < 1 || duracion > 6) {
                mensaje = "La duración debe ser entre 1 y 6 horas.";
                request.setAttribute("error", mensaje);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException ex) {
            mensaje = "Duración inválida.";
            request.setAttribute("error", mensaje);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Crear la reserva si todas las validaciones pasan
        reservationModel rest = new reservationModel();
        rest.setNombre(nombre);
        rest.setFecha(fecha_parse);
        rest.setEspacioTrabajo(espacio);
        rest.setDuracionReserva(duracionStr);
        createReserva(rest);

        // Mensaje de éxito
        mensaje = "Reserva creada exitosamente.";
        request.setAttribute("success", mensaje);
        request.getRequestDispatcher("reservas.jsp").forward(request, response);
        
    }
    //Estamos llamando el metodo crear para poder ser usando en el doPost
    public void createReserva(reservationModel rest) {
        reservaJpa.create(rest);
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
