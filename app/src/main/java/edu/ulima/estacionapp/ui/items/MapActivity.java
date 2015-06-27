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

import edu.ulima.estacionapp.R;

public class MapActivity extends Fragment {

    private GoogleMap googleMap; // Might be null if Google Play services APK is not available.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.activity_map, container, false);
        googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map))
                .getMap();

        return myFragmentView;
    }
    private SupportMapFragment getMapFragment() {
        FragmentManager fm = null;

        Log.d("mapAcitvity", "sdk: " + Build.VERSION.SDK_INT);
        Log.d("mapAcitvity", "release: " + Build.VERSION.RELEASE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Log.d("mapAcitvity", "using getFragmentManager");
            fm = getFragmentManager();
        } else {
            Log.d("mapAcitvity", "using getChildFragmentManager");
            fm = getChildFragmentManager();
        }

        return (SupportMapFragment) fm.findFragmentById(R.id.map);
    }

    private void iniciarPosition() {
        googleMap.setMyLocationEnabled(true);

        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                //LatLng currentLocation = new LatLng(googleMap.getMyLocation().getLatitude(),googleMap.getMyLocation().getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 13));
            }
        });
    }

}
