package edu.ulima.estacionapp.ui.items.Empresa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.ulima.estacionapp.Bean.Reserve;
import edu.ulima.estacionapp.R;

/**
 * Created by Leonardo on 22/07/2015.
 */
public class ListAdapterReserve extends ArrayAdapter {
    private Context context;
    private List<Reserve> datos;

    public ListAdapterReserve(Context context,List<Reserve> datos) {
        super(context, R.layout.item_reserva, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_reserva, null);
        TextView placa = (TextView) item.findViewById(R.id.txtPlaca);
        TextView nombre = (TextView) item.findViewById(R.id.txtNombreReserva);
        TextView fechaRegistro = (TextView) item.findViewById(R.id.txtFechaIngresoReserva);
        placa.setText(datos.get(position).getPlacaAuto());
        nombre.setText(datos.get(position).getNombreCliente());
        fechaRegistro.setText(""+datos.get(position).getInicioReserva());

        return item;
    }

}
