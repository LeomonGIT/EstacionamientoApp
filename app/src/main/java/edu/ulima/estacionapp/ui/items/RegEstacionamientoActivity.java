package edu.ulima.estacionapp.ui.items;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import edu.ulima.estacionapp.R;

public class RegEstacionamientoActivity extends ActionBarActivity {

    private EditText txtCapacidad,txtTarifa;
    private GoogleMap googleMap;
    private double longitud,latitud;
    private MarkerOptions marker;
    public  Marker addMarker=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_estacionamiento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicializando widgets
        txtCapacidad = (EditText) findViewById(R.id.regCapacidad);
        txtTarifa = (EditText) findViewById(R.id.regTarifa);
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.regMap))
                .getMap();
        marker = new MarkerOptions();
        iniciarPosition();


        //logica getPositionOnTouchMap
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                if (addMarker != null)
                    addMarker.remove();
                marker.position(new LatLng(point.latitude, point.longitude))
                        .title("Local");
                addMarker = googleMap.addMarker(marker);
                Log.e("Ubicacion Marker", addMarker.getPosition().latitude + "---" + point.longitude);
            }
        });
    }


    private void iniciarPosition(){
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
    
    //metodo para registrar local
    public void clickRegEstacionamiento(View v){

        Toast.makeText(RegEstacionamientoActivity.this, "Registrando...Espere un momento", Toast.LENGTH_LONG).show();
        ParseGeoPoint point = new ParseGeoPoint(addMarker.getPosition().latitude,addMarker.getPosition().longitude);

        ParseObject estacionamiento = new ParseObject("Empresa");
        estacionamiento.put("capacidad", Integer.parseInt(txtCapacidad.getText().toString()));
        estacionamiento.put("tarifa", Integer.parseInt(txtTarifa.getText().toString()));
        estacionamiento.put("ubicacion",point);
        estacionamiento.put("disponible",Integer.parseInt(txtCapacidad.getText().toString()));
        estacionamiento.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(RegEstacionamientoActivity.this, "Registrado!", Toast.LENGTH_SHORT).show();
                    //onBackPressed();
                }
                else
                    Toast.makeText(RegEstacionamientoActivity.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Log.e("optionPress","presionado");
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
