package com.birget.xdrjve;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.birget.xdrjve.CustomerRequest;

import java.util.List;

public class CustomerRequestAdapter extends ArrayAdapter<CustomerRequest> {
    private Activity context;
    private List<CustomerRequest> artistList;
    public CustomerRequestAdapter(Activity context, List<CustomerRequest> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.list_layout, null, true);

        TextView Name = (TextView)listViewitem.findViewById(R.id.name);
        TextView Phone = (TextView)listViewitem.findViewById(R.id.phone);
        ImageView SendMessage = (ImageView)listViewitem.findViewById(R.id.sendmessage);
        CardView Card1 = (CardView)listViewitem.findViewById(R.id.card1);
        final CustomerRequest customerRequest = artistList.get(position);
        final String uid = customerRequest.getUid();
        Name.setText(customerRequest.getName());
        Phone.setText(customerRequest.getPhone());
        SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(context.getApplicationContext(), Message_2.class);
                i.putExtra("uid",customerRequest.getUid());
                context.startActivity(i);
            }
        });

        Name.setText(customerRequest.getName());
        Phone.setText(customerRequest.getPhone());
        Card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth auth = FirebaseAuth.getInstance();
                //FirebaseUser user = auth.getCurrentUser();
                //String Uid = user.getUid();
                //DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
                //db1.child("customer").child(customerRequest.getUid()).removeValue();
                //db1.child("truecustomer").child(customerRequest.getUid()).child("name").setValue(customerRequest.getName());
                //db1.child("truecustomer").child(customerRequest.getUid()).child("phone").setValue(customerRequest.getPhone());
                //db1.child("truecustomer").child(customerRequest.getUid()).child("uid").setValue(customerRequest.getUid());
                Intent i =new Intent(context.getApplicationContext(), RequestCustomerDetails.class);
                i.putExtra("Uid",customerRequest.getUid());
                i.putExtra("phone",customerRequest.getPhone());
                i.putExtra("name",customerRequest.getName());
                context.startActivity(i);
                context.finish();
            }
        });
        return listViewitem;
    }
}
