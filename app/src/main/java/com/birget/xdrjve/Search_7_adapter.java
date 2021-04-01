package com.birget.xdrjve;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.birget.xdrjve.CustomerRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Search_7_adapter  extends ArrayAdapter<CustomerRequest> {
    private Activity context;
    private List<CustomerRequest> artistList;
    public Search_7_adapter(Activity context, List<CustomerRequest> artistList){
        super(context,R.layout.list_layout_5,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.list_layout_5, null, true);

        TextView Name = (TextView)listViewitem.findViewById(R.id.name);
        final CircleImageView CircleImage = (CircleImageView)listViewitem.findViewById(R.id.circleimage);
        final CustomerRequest customerRequest = artistList.get(position);
        final String uid = customerRequest.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.child("profil").getValue().toString();
                Picasso.get().load(a).into(CircleImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Name.setText(customerRequest.getName());
        return listViewitem;
    }
}

