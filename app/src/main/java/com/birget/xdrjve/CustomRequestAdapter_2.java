package com.birget.xdrjve;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.birget.xdrjve.CustomerRequest;

import java.util.List;

public class CustomRequestAdapter_2 extends ArrayAdapter<CustomerRequest> {
    private Activity context;
    private List<CustomerRequest> artistList;
    public CustomRequestAdapter_2(Activity context, List<CustomerRequest> artistList){
        super(context,R.layout.list_layout_2,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.list_layout_2, null, true);

        TextView Name = (TextView)listViewitem.findViewById(R.id.name);
        CardView Card = (CardView)listViewitem.findViewById(R.id.card1);
        TextView Phone = (TextView)listViewitem.findViewById(R.id.phone);

        final CustomerRequest customerRequest = artistList.get(position);

        Name.setText(customerRequest.getName());
        Phone.setText(customerRequest.getPhone());
        Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(context.getApplicationContext(),CustomerDetails.class);
                i.putExtra("uid",customerRequest.getUid());
                context.startActivity(i);
                context.finish();
            }
        });
        return listViewitem;
    }
}
