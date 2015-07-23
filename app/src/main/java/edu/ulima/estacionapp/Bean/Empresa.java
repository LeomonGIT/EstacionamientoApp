package edu.ulima.estacionapp.Bean;

/**
 * Created by Leonardo on 28/06/2015.
 */
public class Empresa {

    String idEmpresa;
    String nombre;
    String tarifa;
    String capacidad;
    int disponibilidad;
    String markerId;
    int position;

    public Empresa() {
    }

    public Empresa(String idEmpresa, String nombre) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMarkerId() {
        return markerId;
    }

    public void setMarkerId(String markerId) {
        this.markerId = markerId;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa='" + idEmpresa + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tarifa='" + tarifa + '\'' +
                ", capacidad='" + capacidad + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", markerId='" + markerId + '\'' +
                '}';
    }
}
