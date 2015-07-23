package edu.ulima.estacionapp.Servicios.Empresa;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.ulima.estacionapp.Bean.Reserve;
import edu.ulima.estacionapp.Servicios.UserController;

public class ConectorParse {

    ReserveController controller;
    ConectorInterface conectorIF;
    SimpleDateFormat parserSDF;
    String nombreUser="";
    public ConectorParse(ConectorInterface conectorIF) {
        this.conectorIF = conectorIF;
        controller = ReserveController.getInstance();
    }

    public void getAllReserve(){
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Reserva");
        Log.e("idEmpr",EmpresaController.getInstance().getEmpresa().getIdEmpresa());
        ParseObject empresa = ParseObject.createWithoutData("Empresa", EmpresaController.getInstance().getEmpresa().getIdEmpresa());
        query.whereEqualTo("idEmpresa",empresa);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null && list.size()>0){
                    ArrayList<Reserve> temp = new ArrayList<>();
                    for(ParseObject object : list){
                        Reserve reserve = null;
                        String idUser=object.getParseObject("idUser").getObjectId().toString();
                        //completeReserveParse(idUser);
                        try {
                            reserve = new Reserve(object.getObjectId().toString()
                                    ,object.getParseObject("idEmpresa").getObjectId().toString()
                                    ,idUser
                                    ,object.get("tipo").toString()
                                    ,parseDate(object.get("fecha_reserva").toString())
                                    //,null
                                    //,formatter.parse(object.get("fecha_expiracion").toString())
                                    ,null
                                    ,object.get("nombreCliente").toString(),object.get("placa").toString());
                            Log.e("Add to array is:", reserve.toString());
                        } catch (java.text.ParseException e1) {
                            e1.printStackTrace();
                        }

                        temp.add(reserve);
                    }
                    controller.setReserve(temp);
                    conectorIF.onFinishParse();
                }else if(e!=null)
                    e.printStackTrace();
                else
                    Log.e("getAllReserve","error en findInBackground");
            }
        });
    }

    private String parseDate(String fechaString) throws java.text.ParseException {
        Date fecha = null;
        parserSDF=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        fecha= parserSDF.parse(fechaString);
        parserSDF = new SimpleDateFormat("MM/dd HH:mm",Locale.ENGLISH);
        String fechas = parserSDF.format(fecha);
        return fechas;
    }

    private void completeReserveParse(String idUser){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cliente");
        ParseObject user = ParseObject.createWithoutData("_User", idUser);
        query.whereEqualTo("idUser", user);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null && list != null) {
                    String nombre = list.get(0).get("nombre").toString();
                    nombreUser = nombre;
                    Log.e("Add to nombre is:", nombre);
                } else
                    e.printStackTrace();
            }
        });
    }
}
