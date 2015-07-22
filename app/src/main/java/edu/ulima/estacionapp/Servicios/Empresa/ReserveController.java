package edu.ulima.estacionapp.Servicios.Empresa;

import java.util.ArrayList;
import java.util.List;

import edu.ulima.estacionapp.Bean.Reserve;

/**
 * Created by Administrador on 22/07/2015.
 */
public class ReserveController {

    private static List<Reserve> reserve;
    private static ReserveController instance;

    public static ReserveController getInstance(){
        if(instance==null){
            instance=new ReserveController();
            reserve = new ArrayList<>();
        }
        return instance;
    }

    public static List<Reserve> getReserve() {
        return reserve;
    }

    public static void setReserve(List<Reserve> reserve) {
        ReserveController.reserve = reserve;
    }
}
