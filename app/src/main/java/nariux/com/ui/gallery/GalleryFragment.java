package nariux.com.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
// import android.widget.ArrayAdapter;
// import android.widget.CompoundButton;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
// import android.widget.Switch;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
// import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
// import androidx.lifecycle.Observer;
// import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

// import nariux.com.ui.gallery.AdapterSuperList;
// import nariux.com.MainActivity;
import nariux.com.AdapterSuperList;
import nariux.com.R;
import nariux.com.conection.ClientHttp;
import nariux.com.model.Super;
import nariux.com.utils.Utilidades;


public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private ClientHttp clientHttp;
    private TextView textView;
    private ListView listview;
    private ArrayList<Super> listSuper;
    private Button refrescar;
    private Spinner spinnerArea;
    private Utilidades utilidades;
    private Switch switch12 ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);*/
        utilidades = new Utilidades();
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        textView = root.findViewById(R.id.textView5);
        listview = root.findViewById(R.id.Liston);
        refrescar = root.findViewById(R.id.button_refrescar);
        spinnerArea = root.findViewById(R.id.spinnerArea);
        spinnerArea.setAdapter(utilidades.getAdapter(getContextClass(), R.array.areas_array));
        Switch switch12 = root.findViewById(R.id.switch12);
        clientHttp = new ClientHttp();


        refrescar.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View v) {
                traeSuper();
            }
        });


/*        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                clientHttp.setElimina(getList().get(position));
                traeSuper();
                Toast.makeText(getContextClass(), "Eliminaste: "+ getList().get(position).getArticulo(), Toast.LENGTH_LONG).show();
            }
        });)*/

        inicia();
        return root;
    }
    private void inicia() {
        traeSuper();
    }

    private void traeSuper(){
        listSuper= clientHttp.getSuper();

        textView.setText("total:" + listSuper.size());
        ArrayList<String> names = new ArrayList<String>();

        for(Super s : listSuper){
           names.add(s.getArticulo());
        }

        //listview.setAdapter(getMyAdapter(names));
        listview.setAdapter(getAdapterSuperList());
    }

    private MyAdapter getMyAdapter(ArrayList<String> list){
        return  new MyAdapter(getContextClass(), R.layout.simple_list_item_1, list);
    }

    private AdapterSuperList getAdapterSuperList(){
        //AdapterSuperList adapter = new AdapterSuperList(getContextClass(), listSuper);
        AdapterSuperList adapter = new AdapterSuperList(this.getActivity(), listSuper);
        return adapter;
    }
    private Context getContextClass(){ return this.getContext(); }
    private List<Super> getList(){ return listSuper;}
}