package nariux.com.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import nariux.com.R;
import nariux.com.conection.ClientHttp;
import nariux.com.model.Super;
import nariux.com.utils.Utilidades;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Spinner spinnerArea;
    private Spinner spinnerAtiende;
    private Spinner spinnerCasa;
    private EditText articulo;
    private ClientHttp clientHttp;
    private Snackbar sySnackbar;
    private Button boton;
    private Utilidades utilidades;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        utilidades = new Utilidades();
        inicia(this.getContext(), root);
        return root;
    }



    public void enviaForm(View v){
        Super s = new Super();
        s.setAtiende(spinnerAtiende.getSelectedItem().toString());
        s.setArea_super(spinnerArea.getSelectedItem().toString());
        s.setArea_casa(spinnerCasa.getSelectedItem().toString());
        s.setArticulo(articulo.getText().toString());
        Super ret = clientHttp.agregarLista(s);
        if(ret.getId()!=-1){

            Snackbar.make(v, "Arre, ya se guardo", Snackbar.LENGTH_SHORT).show();
            articulo.setText("");
        }else{

            Snackbar.make(v, "Fail beivy", Snackbar.LENGTH_SHORT).show();
        }

    }

    private void inicia(Context c, View r){
        clientHttp = new ClientHttp();
        articulo = (EditText) r.findViewById(R.id.editTextArticulo);
        boton = (Button) r.findViewById(R.id.button_alta);
        spinnerArea=(Spinner) r.findViewById(R.id.spinner_area);
        spinnerAtiende=(Spinner) r.findViewById(R.id.spinner_atiende);
        spinnerCasa=(Spinner) r.findViewById(R.id.spinner_casa);
        spinnerArea.setAdapter(utilidades.getAdapter(c, R.array.areas_array));
        spinnerAtiende.setAdapter(utilidades.getAdapter(c, R.array.atiende_array));
        spinnerCasa.setAdapter(utilidades.getAdapter(c, R.array.casa_array));

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enviaForm(v);
                //Aqui asignar evento
                //boton_tomar_foto............

            }
        });

    }
/*
    private SpinnerAdapter getAdapter(Context c, int areas_array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(c,
                areas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }*/
}