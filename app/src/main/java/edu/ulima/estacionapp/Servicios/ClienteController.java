package edu.ulima.estacionapp.Servicios;

import edu.ulima.estacionapp.Bean.Cliente;
import edu.ulima.estacionapp.Bean.Usuario;

/**
 * Created by Leonardo on 22/07/2015.
 */
public class ClienteController {
    private static Cliente cliente;
    private static ClienteController instance;

    public static ClienteController getInstance(){
        if(instance==null)
            instance=new ClienteController();
        return instance;
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        ClienteController.cliente = cliente;
    }
}
