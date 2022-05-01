package nariux.com.ui.gallery;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import android.widget.CheckBox;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import controller.SuperOnClickListener;
import nariux.com.R;
import nariux.com.conection.ClientHttp;
import nariux.com.model.Super;
import nariux.com.utils.Utilidades;


public class GalleryFragment extends Fragment implements SuperOnClickListener {

    private GalleryViewModel galleryViewModel;
    AdapterSuperList oAdapterSuperList;
    private ClientHttp clientHttp;
    private TextView textView;
    private ListView listview;
    private ArrayList<Super> listSuper;
    private Button refrescar;
    private Spinner spinnerArea;
    private Utilidades utilidades;
    private ProgressDialog progressDialog;
    private CheckBox comprado;

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
        comprado =  (CheckBox) root.findViewById(R.id.checkBoxComprado);
        clientHttp = new ClientHttp();
        galleryViewModel= new GalleryViewModel();
        this.progressDialog = new ProgressDialog(requireContext());

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
        showLoading(requireContext().getResources().getString(R.string.loading));
        boolean c = comprado.isChecked();
        String area = spinnerArea.getSelectedItem().toString();
        System.out.println("ASDASD"+area);
        //showLoading(area);
        listSuper= clientHttp.getSuper(area, c, "Todos");

        textView.setText("total:" + listSuper.size());
        ArrayList<String> names = new ArrayList<String>();

        for(Super s : listSuper){
           names.add(s.getArticulo());
        }

        //listview.setAdapter(getMyAdapter(names));
        getAdapterSuperList();
        listview.setAdapter(oAdapterSuperList);
        dismissLoading();
    }

    private void getAdapterSuperList() {
        AdapterSuperList adapterSuperList = new AdapterSuperList(this.getActivity(), listSuper);
        adapterSuperList.setSuperOnClickListener(this);
        adapterSuperList.setGalleryViewModel(galleryViewModel);
        oAdapterSuperList = adapterSuperList;
    }

    private Context getContextClass(){ return this.getContext(); }
    private List<Super> getList(){ return listSuper;}

    @Override
    public void onCheckedSuper(AppCompatCheckBox chComprado, int indexSuper) {
        showLoading(requireContext().getResources().getString(R.string.loading));
        listSuper.get(indexSuper).setComprado(chComprado.isChecked());
        galleryViewModel.actualizaComprado(listSuper.get(indexSuper));
        oAdapterSuperList.swapItems(listSuper);
        dismissLoading();
    }

    private void showLoading(String message){
        this.progressDialog.setMessage(message);
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    private void dismissLoading(){
        if (this.progressDialog != null && this.isLoadingShowing()) {
            this.progressDialog.dismiss();
        }
    }

    public boolean isLoadingShowing() {
        return this.progressDialog != null && this.progressDialog.isShowing();
    }
}
