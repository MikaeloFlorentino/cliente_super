package nariux.com.ui.slideshow;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import nariux.com.R;
import nariux.com.conection.ClientHttp;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    //private Snackbar sySnackbar;
    private ClientHttp clientHttp;
    private Button boton;
    private AlertDialog.Builder builder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        inicia(this.getContext(), root);
        return root;
    }


    @SuppressLint("WrongViewCast")
    private void inicia(Context c, View r){
        clientHttp = new ClientHttp();
        boton = (Button) r.findViewById(R.id.button_vacia);
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enviaForm(v);
                //Aqui asignar evento
                //boton_tomar_foto............

            }
        });
        //CompoundButton.OnCheckedChangeListener onCheckedChangeListener = ;


    }
    public void enviaForm(View v){
     /*   final View vv =v;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Titulo")
                .setMessage("El Mensaje para el usuario")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //listener.onPossitiveButtonClick();
                                Snackbar.make(vv, "Arre, ya se guardo", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //listener.onNegativeButtonClick();
                                Snackbar.make(vv, "NO", Snackbar.LENGTH_SHORT).show();
                            }
                        });
         builder.create();*/
//
      createSimpleDialog(v);


    }

    public void createSimpleDialog( View v) {
         final View vv =v;

         builder = new AlertDialog.Builder(getActivity());

         builder.setTitle("Seguro");
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setMessage("Deseas dar mate al backlog")
                .setPositiveButton("Fierro",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //listener.onPossitiveButtonClick();
                                clientHttp.vaciar();
                                Snackbar.make(vv, "Ya fue", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Nel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //listener.onNegativeButtonClick();
                                Snackbar.make(vv, "Ok NO!", Snackbar.LENGTH_SHORT).show();
                            }
                        });

        builder.create().show();
    }


}