package com.birget.xdrjve;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ElanQoy extends AppCompatActivity {
    private ListView PutElan;
    private ArrayList<ReserveModel> model;
    private TextView Error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elan_qoy);
        Error = (TextView)findViewById(R.id.error);
        PutElan = (ListView)findViewById(R.id.putelan);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        model = new ArrayList<>();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             if(dataSnapshot.hasChild("PutOrders")){
                 DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("PutOrders");
                 db2.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                         model.clear();
                         for(DataSnapshot dataSnapshot2 :dataSnapshot1.getChildren()){
                             ReserveModel Model = dataSnapshot2.getValue(ReserveModel.class);
                             model.add(Model);
                         }
                         Reserve_adapter adap = new Reserve_adapter(ElanQoy.this,model);
                         PutElan.setAdapter(adap);
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });
             }else{
                 PutElan.setVisibility(View.GONE);
                 Error.setVisibility(View.VISIBLE);
             }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
