<%-- 
    Document   : reservas
    Created on : 12/03/2025, 12:52:18 p. m.
    Author     : HOGAR
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.reservationModel"%>
<%@page import="java.util.List"%>
<%@page import="servlets.listaReservaServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/reservas.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="styles/index.js"></script>
        <title>Lista de reservas</title>
    </head>
    <body>
        <!--  
            Se hizo la creacion de un .jsp llamado reservas en la cual van a aparecer todas las reservas que se han geereado,
        -->
        <div class="main-container">
            <div class="container-xl">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>Lista de reservas</h2>
                                </div>
                                <div class="col-sm-6">
                                    <a class="btn btn-light" id="assig" href="index.jsp">ASIGNAR UNA RESERVA</a>					
                                </div>
                            </div>
                        </div>
                        <table class="table table-striped table-hover" >
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Nombre</th>
                                    <th>Fecha de reserva</th>
                                    <th>Espacio de trabajo</th>
                                    <th>Duración (hh)</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    // Llamar al método para cargar las reservas
                                    listaReservaServlet listServlet = new listaReservaServlet();
                                    listServlet.traerReservas(request);
                                    // Recuperar la lista de la reserva de la sesión
                                    List<reservationModel> listaReservas = (List<reservationModel>) session.getAttribute("listaReservas");
                                    int cont = 1;
                                    if (listaReservas != null && !listaReservas.isEmpty()) {
                                        // Formateadores de fecha
                                        SimpleDateFormat formatoEntrada = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                                        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");

                                        for (reservationModel reserva : listaReservas) {

                                            String fechaFormateada = "";
                                            try {
                                                // Convertir la fecha al formato deseado
                                                Date fechaOriginal = formatoEntrada.parse(reserva.getFecha().toString());
                                                fechaFormateada = formatoSalida.format(fechaOriginal);
                                            } catch (Exception e) {
                                                fechaFormateada = "Fecha inválida"; // Manejo de errores
                                            }
                                %>
                                <tr>
                                    <td><%=cont%></td>
                                    <td><%=reserva.getNombre()%></td>
                                    <td><%= fechaFormateada%></td> <!-- Aquí mostramos la fecha formateada -->
                                    <td><%=reserva.getEspacioTrabajo()%></td>
                                    <td><%=reserva.getDuracionReserva()%> horas</td>
                                    <td >

                                        <a href="#deleteEmployeeModal" class="deleteU" data-toggle="modal" data-id="<%=reserva.getId()%>">
                                            <i class="material-icons" data-toggle="tooltip" title="Delete" style="color: red;">&#xE872;</i>
                                        </a>
                                    </td>
                                </tr>
                                <%
                                            cont++;
                                        }
                                    } else {
                                    }
                                %>

                            </tbody>
                        </table>  
                    </div>
                </div>        
            </div>
        </div>
        <!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="eliminarReservaServlet" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Eliminar reserva</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <input type="hidden" id="editId" name="id_">
                        <div class="modal-body">					
                            <p>¿Esta seguro de eliminar este dato?</p>
                            <p class="text-warning"><small>!Esta acción no se puede deshacer¡.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
