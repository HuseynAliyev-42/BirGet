package com.birget.xdrjve;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<ShowShare> {
    private Activity context;
    private List<ShowShare> artistList;
    public Adapter(Activity context, List<ShowShare> artistList){
        super(context,R.layout.list_layout_2,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.list_layout_2, null, true);

        TextView Name = (TextView)listViewitem.findViewById(R.id.name);
        TextView Phone = (TextView)listViewitem.findViewById(R.id.phone);

        final ShowShare customerRequest2 = artistList.get(position);

        Name.setText(customerRequest2.getName());
        Phone.setVisibility(View.INVISIBLE);
        return listViewitem;
    }
}
