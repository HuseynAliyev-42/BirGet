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

public class RezervElan extends AppCompatActivity {
    private ListView PutElan;
    private ArrayList<PutAndReserveModel> model;
    private TextView Error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezerv_elan);
        Error = (TextView)findViewById(R.id.error);
        PutElan = (ListView)findViewById(R.id.putelan);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        model = new ArrayList<>();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("ReserveOrders")){
                    DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("ReserveOrders");
                    db2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                            model.clear();
                            for(DataSnapshot dataSnapshot2 :dataSnapshot1.getChildren()){
                                PutAndReserveModel Model = dataSnapshot2.getValue(PutAndReserveModel.class);
                                model.add(Model);
                            }
                            Put_adapter adap1= new Put_adapter(RezervElan.this,model);
                            PutElan.setAdapter(adap1);
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
