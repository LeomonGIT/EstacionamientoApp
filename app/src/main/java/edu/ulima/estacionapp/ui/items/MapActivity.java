package edu.ulima.estacionapp.ui.items;


import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import edu.ulima.estacionapp.R;

public class MapActivity extends Fragment {

    private GoogleMap googleMap; // Might be null if Google Play services APK is not available.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.activity_map, container, false);
        googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map))
                .getMap();

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
                        addMarker(objects.get(i));
                    }
                } else {
                    Log.e("parseEmpresa","error buscando objectos de empresa");
                    // something went wrong
                }
            }
        });
    }

    private void addMarker(final ParseObject parseObject) {
        try{
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(parseObject.getParseGeoPoint("ubicacion").getLatitude(),
                                parseObject.getParseGeoPoint("ubicacion").getLongitude()))
                        .title(parseObject.get("nombre").toString())
                        .snippet("Disponibles: "+parseObject.get("disponible").toString());
                googleMap.addMarker(marker);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
