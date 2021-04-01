package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search_9 extends AppCompatActivity {
    private Button Submit;
    private String ReceiverUid;
    private String name;
    private String phone;
    private String myUid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_9);
        Submit = (Button)findViewById(R.id.submit);
        ReceiverUid = getIntent().getStringExtra("uid");
        if(ReceiverUid.matches("")){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String myUid = user.getUid();
            DatabaseReference d1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid);
            d1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild("drive")){
                        Intent i = new Intent(Search_9.this, Qoy_9.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(Search_9.this, Activity_3.class);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        myUid = user.getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                phone = dataSnapshot.child("phone").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid).child("ReserveOrders");
        db1.child(String.valueOf(getTaskId())).child("id").setValue(ReceiverUid);
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(ReceiverUid).child("customer");
        db3.child(String.valueOf(getTaskId())).child("name").setValue(name);
        db3.child(String.valueOf(getTaskId())).child("phone").setValue(phone);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Search_9.this,Activity_3.class);
                startActivity(i);
                finish();
            }
        });
    }
}
