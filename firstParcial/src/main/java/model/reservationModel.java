/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author HOGAR
 */
//Es una entidad JPA, se mapeará a una tabla de bd
@Entity
//Definira que las tabla se llamara "reservas" en la bd
@Table(name = "reservas")
public class reservationModel implements Serializable {

    @Id
    //hara que la base de datos genere automaticamente el ID
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //nombre que va tomar una columna en la bd
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    //Va a definir que guardara la fecha sin hora
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Column(name = "espacioTrabajo")
    private String espacioTrabajo;
    @Column(name = "duracionReserva")
    private String duracionReserva;

    //metodo constructor vacio
    public reservationModel() {
    }

    public reservationModel(int id, String nombre, Date fecha, String espacioTrabajo, String duracionReserva) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.espacioTrabajo = espacioTrabajo;
        this.duracionReserva = duracionReserva;
    }

    //todos lo setters y getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEspacioTrabajo() {
        return espacioTrabajo;
    }

    public void setEspacioTrabajo(String espacioTrabajo) {
        this.espacioTrabajo = espacioTrabajo;
    }

    public String getDuracionReserva() {
        return duracionReserva;
    }

    public void setDuracionReserva(String duracionReserva) {
        this.duracionReserva = duracionReserva;
    }

    @Override
    public String toString() {
        return "model.reservationModel[ id=" + id + " ]";
    }

}
