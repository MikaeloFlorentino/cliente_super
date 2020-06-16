package nariux.com.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import nariux.com.conection.ClientHttp;
import nariux.com.model.Super;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private ClientHttp clientHttp;

    public GalleryViewModel() {

        clientHttp = new ClientHttp();
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public void actualizaComprado(Super s){


        clientHttp.actualizaComprado(s);
    }

    public void eliminar(Super s){
        clientHttp.setElimina(s);
    }

    public LiveData<String> getText() {
        return mText;
    }
}