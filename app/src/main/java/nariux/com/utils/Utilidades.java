package nariux.com.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

public class Utilidades {

    public static String SERVER_URL="localhost:8000/";
    public static String SERVER_PROTOCOLO="http://";

    public static String SERVER_URL_SUPER="api/super";
    public static String HTTP_POST="POST";
    public static String HTTP_GET="GET";


    public SpinnerAdapter getAdapter(Context c, int areas_array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(c,
                areas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
