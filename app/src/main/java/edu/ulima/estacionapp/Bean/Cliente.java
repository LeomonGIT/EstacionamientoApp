package edu.ulima.estacionapp.Bean;

public class Cliente {

    String idCliente,nombre,placa;

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente='" + idCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente(String idCliente, String nombre, String placa) {

        this.idCliente = idCliente;
        this.nombre = nombre;
        this.placa = placa;
    }
}
