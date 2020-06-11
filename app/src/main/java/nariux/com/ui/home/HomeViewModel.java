package nariux.com.ui.home;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import nariux.com.R;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private String hola;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }


    public LiveData<String> getText() {
        return mText;
    }
}