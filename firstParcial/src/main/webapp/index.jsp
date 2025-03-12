<%@page import="persistence.reservationModelJpaController"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : index
    Created on : 12/03/2025, 12:52:18 p. m.
    Author     : HOGAR
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/index.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>ReservasCoworking</title>
    </head>
    <body>

        <!--
            Volvimos un .jsp el index. luego se creo un formulario para poder hacer la creacion de la reserva y colección de los datos
        -->
        <%
            //Instanciamos la clase para que se creen las tablas en la bd
            reservationModelJpaController reservaController = new reservationModelJpaController();

            // Mensajes de error o éxito
            String mensajeError = (String) request.getAttribute("error");
            String mensajeExito = (String) request.getAttribute("success");

            // Obtener la fecha actual en formato YYYY-MM-DD
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaHoy = sdf.format(new Date());
        %>

        <div class="container">
            <form action="reservaServlet" method ="POST" class="form">
                <div class="header-container">
                    <div class="btn-container">
                        <a class="btn btn-dark" href="reservas.jsp">Ver reservas</a>
                    </div>
                    <div class="tittLe_reserv">
                        <h2 class="t1">Realizar una reserva</h2>
                    </div>
                </div>
                <input type="text" placeholder="Name" class="form__input" id="name" name="nombre" />
                <label for="name" class="form__label">Nombre</label>

                <!-- Input de fecha con restricción de días pasados -->
                <input type="date" placeholder="Fecha" class="form__input" id="fecha" name="fecha" min="<%= fechaHoy%>" />
                <label for="fecha" class="form__label">Fecha de reserva</label>

                <label for="espacio" class="form-label">Espacio de Trabajo</label>
                <select class="custom-select" id="espacio_" name="espacio">
                    <option selected disabled>Seleccione un espacio...</option>
                    <option value="Escritorio">Escritorio</option>
                    <option value="Sala de reuniones">Sala de reuniones</option>
                    <option value="Oficina privada">Oficina privada</option>
                </select>

                <input type="number" placeholder="duración" class="form__input" id="duracion" name="duracion" min="1" max="6"/>
                <label for="duracion" class="form__label">Duración de la reserva (hh)</label>

                <p class="note">Nota: las reuniones no podrán tener una duración superior a 6 horas o inferior a 1 hora</p>

                <div class="btn-send">
                    <button type="submit" name="enviar" class="btn btn-success" id="send">Enviar</button>
                </div>
            </form>
        </div>

        <!-- 
            Se hace uso de los SweetAlert para dar al usuario ciertos mensajes cuando este completando el formulario y que le puedan servir de ayuda
        -->        

        <!-- SweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <%-- Mostrar mensaje de error con SweetAlert --%>
        <% if (mensajeError != null && !mensajeError.isEmpty()) {%>
        <script>
            Swal.fire({
                title: "Error",
                text: "<%= mensajeError%>",
                icon: "error"
            });
        </script>
        <% } %>

        <%-- Mostrar mensaje de éxito con SweetAlert --%>
        <% if (mensajeExito != null && !mensajeExito.isEmpty()) {%>
        <script>
            Swal.fire({
                title: "Éxito",
                text: "<%= mensajeExito%>",
                icon: "success"
            });
        </script>
        <% }%>

    </body>
</html>
