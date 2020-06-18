package nariux.com.ui.gallery;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import java.util.ArrayList;

import controller.SuperOnClickListener;
import nariux.com.R;
import nariux.com.model.Super;

public class AdapterSuperList  extends ArrayAdapter<Super> {

    private GalleryViewModel galleryViewModel;
    private final Activity context;
    ArrayList<Super> supersList;
    SuperOnClickListener superOnClickListener;

    public AdapterSuperList(Activity context, ArrayList<Super> supers) {
        super(context, 0, supers);
        this.context=context;
        this.supersList=supers;
    }

    public void setSuperOnClickListener(SuperOnClickListener superOnClickListener) {
        this.superOnClickListener = superOnClickListener;
    }

    public void setGalleryViewModel(GalleryViewModel galleryViewModel) {
        this.galleryViewModel = galleryViewModel;
    }

    public void swapItems(ArrayList<Super> supers) {
        this.supersList = supers;
        notifyDataSetChanged();
    }

    public AdapterSuperList(Context context, ArrayList<Super> supers) {
        super(context, 0, supers);
        this.context=null;
        this.supersList=supers;
    }


    static class ViewHolder {
        protected TextView articulo;
        protected TextView area;
        protected AppCompatCheckBox comprado;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Super supers = getItem(position);
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.simple_list_item_1, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.articulo = (TextView) view.findViewById(R.id.textView);
            viewHolder.area = (TextView) view.findViewById(R.id.textViewArea);
            viewHolder.comprado = (AppCompatCheckBox) view.findViewById(R.id.chb_superlist_comprado);
            view.setTag(viewHolder);
            viewHolder.comprado.setTag(supers);
        }else {
            view = convertView;
            ((ViewHolder) view.getTag()).comprado.setTag(supers);
        }

        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.articulo.setText(supers.getArticulo());
        holder.area.setText(supers.getArea_super());
        if (supersList.indexOf(supers) == position) {
            holder.comprado.setChecked(supers.isComprado());
        }

        holder.comprado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                superOnClickListener.onCheckedSuper(holder.comprado,position);
            }
        });

        if (supersList.get(position).isComprado()) {
            holder.comprado.setChecked(true);
        } else {
            holder.comprado.setChecked(false);
        }
        return view;
    }

/*
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Super supers = getItem(position);
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.simple_list_item_1, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.articulo = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.area = (TextView) convertView.findViewById(R.id.textViewArea);
            viewHolder.comprado = (Switch) convertView.findViewById(R.id.chb_superlist_comprado);


            // Return the completed view to render on screen
            viewHolder.comprado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    supers.setComprado(isChecked);
                    galleryViewModel.actualizaComprado(supers);
                }
            });

            view.setTag(viewHolder);
            viewHolder.comprado.setTag(supers);
        }else {
            view = convertView;
            ((ViewHolder) view.getTag()).comprado.setTag(supers);
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.articulo.setText(supers.getArticulo());
        viewHolder.area.setText(supers.getArea_super());
        viewHolder.comprado.setChecked(supers.isComprado());
        return view;
    }
*/

/*
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Super supers = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            //convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1, parent, false);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1, null);
        }


        TextView articulo = (TextView) convertView.findViewById(R.id.textView);
        TextView area = (TextView) convertView.findViewById(R.id.textViewArea);
        Switch comprado = (Switch) convertView.findViewById(R.id.chb_superlist_comprado);

        articulo.setText(supers.getArticulo());
        area.setText(supers.getArea_super());
        comprado.setChecked(supers.isComprado());

       comprado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                supers.setComprado(isChecked);
                galleryViewModel.actualizaComprado(supers);
            }
        });

        return convertView;
    }
*/
}
