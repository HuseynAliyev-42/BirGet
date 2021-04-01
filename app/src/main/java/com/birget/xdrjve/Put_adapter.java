package com.birget.xdrjve;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.birget.xdrjve.Activity_3;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Put_adapter extends ArrayAdapter<PutAndReserveModel> {
    private Activity context;
    private List<PutAndReserveModel> artistList;

    public Put_adapter(Activity context, List<PutAndReserveModel> artistList) {
        super(context, R.layout.reserve_layout_2, artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.reserve_layout_2, null, true);
        final PutAndReserveModel model = artistList.get(position);
        final TextView Title1 = (TextView)listViewitem.findViewById(R.id.title1);
        final Button Ignore = (Button)listViewitem.findViewById(R.id.ignore);
        final TextView FromPlace = (TextView)listViewitem.findViewById(R.id.fromplace);
        final TextView ToPlace = (TextView)listViewitem.findViewById(R.id.toplace);
        final TextView Condition = (TextView)listViewitem.findViewById(R.id.condition);
        final CardView Card1 = (CardView)listViewitem.findViewById(R.id.card1);
        String uid = model.getId();
        String time = model.getTime();
        String month1 = model.getMonth();
        String day = model.getDay();
        String fromplace = model.getFromplace();
        String toplace = model.getToplace();
        if(month1.matches("1")){
            final String month = "Yanvar";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("2")){
            final String month = "Fevral";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("3")){
            final String month = "Mart";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("4")){
            final String month = "Aprel";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("5")){
            final String month = "May";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("6")){
            final String month = "Iyun";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("7")){
            final String month = "Iyul";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("8")){
            final String month = "Avqust";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("9")){
            final String month = "Sentyabr";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("10")){
            final String month = "Oktyabr";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("11")){
            final String month = "Noyabr";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("12")){
            final String month = "Dekabr";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String myUid = user.getUid();
                if(dataSnapshot.child("customer").hasChild(myUid)){
                    Condition.setText("Sürücüdən cavab gözlənilir");
                    return;
                }
                if (dataSnapshot.child("truecustomer").hasChild(myUid)){
                    Condition.setText("Təsdiqlənib");
                    return;
                }
                if(dataSnapshot.child("falsecustomer").hasChild(myUid)){
                    Condition.setText("İstək rədd edilib");
                    return;
                }
                Condition.setText("Elanın vaxtı keçib");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(context.getApplicationContext(), Search_7.class);
                i.putExtra("uid",model.getId());
                context.startActivity(i);
            }
        });
        Ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        final String myUid = user.getUid();
                        if(dataSnapshot.child(model.getId()).child("customer").hasChild(myUid)){
                            DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid);
                            db3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild("drive")){
                                        DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(model.getId()).child("customer");
                                        db2.child(myUid).removeValue();
                                        Intent i = new Intent(context.getApplicationContext(), Qoy_9.class);
                                        context.startActivity(i);
                                        context.finish();
                                    }else{
                                        DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(model.getId()).child("customer");
                                        db2.child(myUid).removeValue();
                                        Intent i = new Intent(context.getApplicationContext(), Activity_3.class);
                                        context.startActivity(i);
                                        context.finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        if(dataSnapshot.child(model.getId()).child("truecustomer").hasChild(myUid)){
                            DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid);
                            db3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild("drive")){
                                        DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(model.getId()).child("truecustomer");
                                        db2.child(myUid).removeValue();
                                        Intent i = new Intent(context.getApplicationContext(),Qoy_9.class);
                                        context.startActivity(i);
                                        context.finish();
                                    }else{
                                        DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(model.getId()).child("truecustomer");
                                        db2.child(myUid).removeValue();
                                        Intent i = new Intent(context.getApplicationContext(),Activity_3.class);
                                        context.startActivity(i);
                                        context.finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return listViewitem;
    }
}