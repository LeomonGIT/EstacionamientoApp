package edu.ulima.estacionapp.ui.items;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ulima.estacionapp.R;
import edu.ulima.estacionapp.Servicios.Empresa.ConectorInterface;
import edu.ulima.estacionapp.Servicios.Empresa.ConectorParse;

public class EmpresaListaReservasFragment extends Fragment implements ConectorInterface{

    ConectorParse conector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.empresa_lista_reservas_fragment, container, false);

        initializeData();

        return myFragmentView;
    }

    private void initializeData(){
        conector = new ConectorParse(this);
        conector.getAllReserve();
    }
    @Override
    public void onFinishParse() {
        //mostrar lista
        Log.e("callback","onFinishParse");
    }
}
