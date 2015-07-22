package edu.ulima.estacionapp.Bean;

import java.util.Date;

/**
 * Created by Administrador on 22/07/2015.
 */
public class Reserve {

    String idReserve,idEmpresa,idCliente,tipoReserva;
    Date inicioReserva,expiraReserva;
    String nombreCliente;

    public Reserve(String idReserve,String idEmpresa, String idCliente, String tipoReserva, Date inicioReserva, Date expiraReserva, String nombreCliente) {
        this.idReserve = idReserve;
        this.idEmpresa = idEmpresa;
        this.idCliente = idCliente;
        this.tipoReserva = tipoReserva;
        this.inicioReserva = inicioReserva;
        this.expiraReserva = expiraReserva;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "  idReserve='" + idReserve+  '\'' +
                ", idEmpresa='" + idEmpresa + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", tipoReserva='" + tipoReserva + '\'' +
                ", inicioReserva=" + inicioReserva +
                ", expiraReserva=" + expiraReserva +
                ", nombreCliente='" + nombreCliente + '\'' +
                '}';
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

    public Date getInicioReserva() {
        return inicioReserva;
    }

    public void setInicioReserva(Date inicioReserva) {
        this.inicioReserva = inicioReserva;
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
}
