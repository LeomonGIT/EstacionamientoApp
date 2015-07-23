package edu.ulima.estacionapp.Bean;

import java.util.Date;

/**
 * Created by Administrador on 22/07/2015.
 */
public class Reserve {

    String idReserve,idEmpresa,idCliente,tipoReserva,inicioReserva;
    Date expiraReserva;
    String nombreCliente,placaAuto;

    public Reserve(String idReserve, String idEmpresa, String idCliente, String tipoReserva, String inicioReserva, Date expiraReserva, String nombreCliente, String placaAuto) {
        this.idReserve = idReserve;
        this.idEmpresa = idEmpresa;
        this.idCliente = idCliente;
        this.tipoReserva = tipoReserva;
        this.inicioReserva = inicioReserva;
        this.expiraReserva = expiraReserva;
        this.nombreCliente = nombreCliente;
        this.placaAuto = placaAuto;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "idReserve='" + idReserve + '\'' +
                ", idEmpresa='" + idEmpresa + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", tipoReserva='" + tipoReserva + '\'' +
                ", inicioReserva='" + inicioReserva + '\'' +
                ", expiraReserva=" + expiraReserva +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", placaAuto='" + placaAuto + '\'' +
                '}';
    }

    public String getInicioReserva() {
        return inicioReserva;
    }

    public void setInicioReserva(String inicioReserva) {
        this.inicioReserva = inicioReserva;
    }

    public String getIdReserve() {
        return idReserve;
    }

    public void setIdReserve(String idReserve) {
        this.idReserve = idReserve;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }


    public Date getExpiraReserva() {
        return expiraReserva;
    }

    public void setExpiraReserva(Date expiraReserva) {
        this.expiraReserva = expiraReserva;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getPlacaAuto() {
        return placaAuto;
    }

    public void setPlacaAuto(String placaAuto) {
        this.placaAuto = placaAuto;
    }
}
