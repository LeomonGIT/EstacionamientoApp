package edu.ulima.estacionapp.Servicios.Empresa;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.ulima.estacionapp.Bean.Reserve;

/**
 * Created by Administrador on 22/07/2015.
 */
public class ConectorParse {

    ReserveController controller;
    ConectorInterface conectorIF;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public ConectorParse(ConectorInterface conectorIF) {
        this.conectorIF = conectorIF;
        controller = ReserveController.getInstance();
    }

    public void getAllReserve(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Reserva");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null && list.size()>0){
                    ArrayList<Reserve> temp = new ArrayList<>();
                    for(ParseObject object : list){
                        Reserve reserve = null;
                        try {
                            reserve = new Reserve(object.getObjectId().toString()
                                    ,object.getParseObject("idEmpresa").getObjectId().toString()
                                    ,object.getParseObject("idUser").getObjectId().toString()
                                    ,object.get("tipo").toString()
                                    ,formatter.parse(object.get("fecha_reserva").toString())
                                    //,null
                                    //,formatter.parse(object.get("fecha_expiracion").toString())
                                    ,null
                                    ,"cliente");
                            Log.e("Add to array is:", reserve.toString());
                        } catch (java.text.ParseException e1) {
                            e1.printStackTrace();
                            //Log.e("ParseException","error en: " + object.toString());
                        }
                        temp.add(reserve);
                    }
                    controller.setReserve(temp);
                    conectorIF.onFinishParse();
                }else
                    Log.e("getAllReserve","error en findInBackground");
            }
        });
    }
}
