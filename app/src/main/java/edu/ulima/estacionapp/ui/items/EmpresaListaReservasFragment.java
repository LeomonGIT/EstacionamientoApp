package edu.ulima.estacionapp.ui.items;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ulima.estacionapp.R;

public class EmpresaListaReservasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.empresa_lista_reservas_fragment, container, false);


        return myFragmentView;
    }
}
