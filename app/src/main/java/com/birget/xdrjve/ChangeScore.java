package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeScore extends AppCompatActivity {
    private String Uid;
    private String uid;
    private RatingBar ratingBar;
    private Button Submit;
    private float ratingscore;
    private float a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_score);
        Uid = getIntent().getStringExtra("uid");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        uid = user.getUid();
        ratingBar = (RatingBar) findViewById(R.id.rating);
        Submit = (Button) findViewById(R.id.bitir);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingscore = ratingBar.getRating();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("SendRatings");
                reference.child(Uid).child("id").setValue(Uid);
                reference.child(Uid).child("score").setValue(ratingscore);
                if (ratingscore == 0.0) {
                    a = (float) 0.0;
                }
                if (ratingscore == 0.5) {
                    a = (float) 0.5;
                }
                if (ratingscore == 1.0) {
                    a = (float) 1.0;
                }
                if (ratingscore == 1.5) {
                    a = (float) 1.5;
                }
                if (ratingscore == 2.0) {
                    a = (float) 2.0;
                }
                if (ratingscore == 2.5) {
                    a = (float) 2.5;
                }
                if (ratingscore == 3.0) {
                    a = (float) 3.0;
                }
                if (ratingscore == 3.5) {
                    a = (float) 3.5;
                }
                if (ratingscore == 4.0) {
                    a = (float) 4.0;
                }
                if (ratingscore == 4.5) {
                    a = (float) 4.5;
                }
                if (ratingscore == 5.0) {
                    a = (float) 5.0;
                }
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
                db1.child("Ratings").child("users").child(uid).child("ratings").setValue(a);
                final DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("Ratings").child("users");
                db2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        double total = 0.0;
                        double count = 0.0;
                        double average = 0.0;
                        for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                            double rating = Double.parseDouble(ds1.child("ratings").getValue().toString());
                            total = total + rating;
                            count = count + 1;
                            average = total / count;
                        }
                        final DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("Ratings");
                        db3.child("total").setValue(average);
                        Intent i = new Intent(ChangeScore.this, Succes.class);
                        i.putExtra("uid", Uid);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}

