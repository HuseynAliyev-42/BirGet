package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search_8 extends AppCompatActivity {
    private RatingBar ratingBar;
    private Button Bitir;
    private String Uid;
    private float numberint;
    private int scoreint;
    private float totalint;
    private float numberint2;
    private int scoreint2;
    private float totalint2;
    private int c;
    private float ratingscore;
    private String name,phone;
    private String uid;
    private float a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_8);
        ratingBar = (RatingBar)findViewById(R.id.rating);
        Bitir = (Button)findViewById(R.id.bitir);
        FirebaseUser us = FirebaseAuth.getInstance().getCurrentUser();
        uid = us.getUid();
        if(uid.matches("")){

        }else{
            DatabaseReference rf= FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
            rf.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                    if(dataSnapshot.hasChild("activity")){
                        Uid = dataSnapshot.child("activity").getValue().toString();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Toast.makeText(Search_8.this,uid,Toast.LENGTH_LONG).show();
            Bitir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ratingscore = ratingBar.getRating();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("SendRatings");
                    reference.child(Uid).child("id").setValue(Uid);
                    reference.child(Uid).child("score").setValue(ratingscore);
                    DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
                    db1.child("Ratings").child("users").child(uid).child("ratings").setValue(ratingscore);
                    final DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("Ratings").child("users");
                    db2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            double total = 0.0;
                            double count = 0.0;
                            double average = 0.0;
                            for (DataSnapshot ds1:dataSnapshot.getChildren()){
                                double rating = Double.parseDouble(ds1.child("ratings").getValue().toString());
                                total =  total+rating;
                                count = count+1;
                                average = total/count;
                            }
                            final DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("Ratings");
                            db3.child("total").setValue(average);
                            DatabaseReference db10 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                            db10.child("activity").removeValue();
                            Intent i = new Intent(Search_8.this, Activity_3.class);
                            i.putExtra("uid",Uid);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });
        }
    }
}
