package edu.ulima.estacionapp.ui.items;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import edu.ulima.estacionapp.Bean.Empresa;
import edu.ulima.estacionapp.R;
import edu.ulima.estacionapp.Servicios.UserController;

public class MapActivity extends Fragment {

    private GoogleMap googleMap; // Might be null if Google Play services APK is not available.
    String temp="";
    List<Empresa> empresas = new ArrayList<>();
    Empresa empresaDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.activity_map, container, false);
        googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map))
                .getMap();
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if(marker.getId().equalsIgnoreCase(temp)){
                    //Intent i =new Intent(getActivity(),ReservarActivity.class);
                    for(int i=0;i<empresas.size();i++)
                        if (marker.getId().equalsIgnoreCase(empresas.get(i).getMarkerId()))
                            empresaDialog=empresas.get(i);
                    Dialog d = createDialog();
                    d.show();
                   // startActivity(i);
                }
                temp = marker.getId();



                return false;
            }
        });
        iniciarPosition();
        getParkingFromParse();
        return myFragmentView;
    }

    private void iniciarPosition() {
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 13));
            }
        });
    }

    private void getParkingFromParse(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Empresa");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        addMarker(objects.get(i), i);

                        //UserController.getInstance().setEmpresaFromParse(objects.get(i));
                    }
                } else {
                    Log.e("parseEmpresa", "error buscando objectos de empresa");
                    // something went wrong
                }
            }
        });
    }

    private void addMarker(final ParseObject parseObject,int pos) {
        Marker addMarker=null;
        MarkerOptions marker;
        try{
                marker = new MarkerOptions().position(
                        new LatLng(parseObject.getParseGeoPoint("ubicacion").getLatitude(),
                                parseObject.getParseGeoPoint("ubicacion").getLongitude()))
                        .title(parseObject.get("nombre").toString())
                        .snippet("Disponibles: " + parseObject.get("disponible").toString());
            addMarker = googleMap.addMarker(marker);

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        Empresa temp= new Empresa();
        temp.setIdEmpresa(parseObject.getObjectId());
        temp.setNombre(parseObject.get("nombre").toString());
        temp.setTarifa(parseObject.get("tarifa").toString());
        temp.setCapacidad(parseObject.get("capacidad").toString());
        temp.setDisponibilidad(Integer.parseInt(parseObject.get("disponible").toString()));
        temp.setMarkerId(addMarker.getId());

        empresas.add(temp);
    }

    private Dialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.reservar_dialog, null);
        Log.e("EmpresaDialog",empresaDialog.toString());
        builder.setView(v);
        TextView txtNombre = (TextView) v.findViewById(R.id.txtRevervaEmpresa);
        TextView txtTarifa = (TextView) v.findViewById(R.id.txtRevervaTarifa);
        TextView txtCapacidad = (TextView) v.findViewById(R.id.txtReservaCapacidad);
        TextView txtDisponibilidad = (TextView) v.findViewById(R.id.txtReservaDisponible);

        txtCapacidad.setText(empresaDialog.getCapacidad());
        txtDisponibilidad.setText(""+empresaDialog.getDisponibilidad());
        txtNombre.setText(empresaDialog.getNombre());
        txtTarifa.setText(empresaDialog.getTarifa());

        builder.setPositiveButton("Reservar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                registrarReserva();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        return builder.create();
    }

    private void registrarReserva(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Empresa");
        query.getInBackground(empresaDialog.getIdEmpresa(), new GetCallback<ParseObject>() {
            public void done(ParseObject estacionamiento, ParseException e) {
                if (e == null) {
                    Log.e("asdas", "asdasd");
                    estacionamiento.put("disponible",empresaDialog.getDisponibilidad()-1);
                    estacionamiento.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                empresas.get(empresaDialog.getPosition()).setDisponibilidad(empresaDialog.getDisponibilidad()-1);
                                Toast.makeText(getActivity(), "Reservado", Toast.LENGTH_LONG).show();
                                //onBackPressed();
                            }
                            else
                                Toast.makeText(getActivity(), "No se pudo Reservar", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Ocurrio un error al reservar", Toast.LENGTH_SHORT).show();
                }}
        });

    }

}
