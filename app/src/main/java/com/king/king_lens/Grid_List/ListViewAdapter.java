package com.king.king_lens.Grid_List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.king.king_lens.R;

/**
 * Created by NgocTri on 10/22/2016.
 */

public class ListViewAdapter extends ArrayAdapter<Product> {
    public ListViewAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }
        Product product = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        TextView txtPrice = (TextView) v.findViewById(R.id.price_list);

        img.setImageResource(product.getImageId());
        txtTitle.setText(product.getTitle());
        txtDescription.setText(product.getDescription());
        txtPrice.setText(""+product.getPrice());

        return v;
    }
}
