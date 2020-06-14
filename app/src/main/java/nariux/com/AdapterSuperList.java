package nariux.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import nariux.com.model.Super;

public class AdapterSuperList  extends ArrayAdapter<Super> {

    public AdapterSuperList(Context context, ArrayList<Super> supers) {
        super(context, 0, supers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Super supers = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1, parent, false);
        }
        // Lookup view for data population
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        //Switch switch12 = (Switch) convertView.findViewById(R.id.switch12);
        // Populate the data into the template view using the data object
        textView.setText(supers.getArticulo());
        //switch12.setChecked(supers.isComprado());
        // Return the completed view to render on screen
        return convertView;
    }
}
