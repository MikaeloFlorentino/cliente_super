package nariux.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nariux.com.model.Super;
import nariux.com.ui.gallery.GalleryViewModel;

public class AdapterSuperList  extends ArrayAdapter<Super> {

    private GalleryViewModel galleryViewModel;
    public AdapterSuperList(Context context, ArrayList<Super> supers) {
        super(context, 0, supers);
        galleryViewModel = new GalleryViewModel();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Super supers = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1, parent, false);
        }
        // Lookup view for data population
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        Switch switch12 = (Switch) convertView.findViewById(R.id.switch12);
        // Populate the data into the template view using the data object
        textView.setText(supers.getArticulo());
        switch12.setChecked(supers.isComprado());
        // Return the completed view to render on screen
        switch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                supers.setComprado(isChecked);
                galleryViewModel.actualizaComprado(supers);
            }
        });

        convertView.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("tocado: " + supers.getId());
            }
        });

        return convertView;
    }
}
