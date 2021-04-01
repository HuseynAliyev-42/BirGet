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

import com.birget.xdrjve.Error_1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

public class Search_6_adapter extends ArrayAdapter<com.birget.xdrjve.Search_6_model> {
    private Activity context;
    private String month;
    private List<Search_6_model> artistList;
    public Search_6_adapter(Activity context, List<Search_6_model> artistList){
        super(context,R.layout.search_6_item,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.search_6_item, null, true);
        CardView Card1 = (CardView)listViewitem.findViewById(R.id.card1);
        TextView Price = (TextView)listViewitem.findViewById(R.id.price);
        TextView Date = (TextView)listViewitem.findViewById(R.id.date);
        TextView Name = (TextView)listViewitem.findViewById(R.id.name);
        TextView FromPlace = (TextView)listViewitem.findViewById(R.id.fromplace);
        TextView ToPlace = (TextView)listViewitem.findViewById(R.id.toplace);
        TextView Time=(TextView)listViewitem.findViewById(R.id.time);
        ImageView Profil = (ImageView)listViewitem.findViewById(R.id.profil);
        TextView PredictTime = (TextView)listViewitem.findViewById(R.id.predicttime);
        final Search_6_model model = artistList.get(position);
        final String Uid= model.getUid();
        if(model.getMonth().matches("0")){
            month="Yanvar";
        }
        if(model.getMonth().matches("1")){
            month="Fevral";
        }
        if(model.getMonth().matches("2")){
            month="Mart";
        }
        if(model.getMonth().matches("3")){
            month="Aprel";
        }
        if(model.getMonth().matches("4")){
            month="May";
        }
        if(model.getMonth().matches("5")){
            month="İyun";
        }
        if(model.getMonth().matches("6")){
            month="İyul";
        }
        if(model.getMonth().matches("7")){
            month="Avqust";
        }
        if(model.getMonth().matches("8")){
            month="Sentyabr";
        }
        if(model.getMonth().matches("9")){
            month="Oktyabr";
        }
        if(model.getMonth().matches("10")){
            month="Noyabr";
        }
        if(model.getMonth().matches("11")){
            month="Dekabr";
        }
        String date = model.getDay() + " "+ month + ","+" "+model.getTime();
        Date.setText(date);
        Price.setText(model.getPrice());
        Name.setText(model.getName());
        FromPlace.setText(model.placedan);
        ToPlace.setText(model.getPlaceto());
        Time.setText(model.getTime());
        PredictTime.setText(model.getPredicttime());
        Picasso.get().load(model.getProfil()).into(Profil);
        Card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
                final Calendar calendar = Calendar.getInstance();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      if (dataSnapshot.hasChild("drive")){
                          if(dataSnapshot.child("drive").hasChild("day")){
                              if(dataSnapshot.child("drive").hasChild("year")){
                                  int day = Integer.valueOf(dataSnapshot.child("drive").child("day").getValue().toString());
                                  int month = Integer.valueOf(dataSnapshot.child("drive").child("month").getValue().toString());
                                  int year = Integer.valueOf(dataSnapshot.child("drive").child("year").getValue().toString());
                                  int nowday = calendar.get(Calendar.DAY_OF_MONTH);
                                  int nowmonth = 1+calendar.get(Calendar.MONTH);
                                  int nowyear = calendar.get(Calendar.YEAR);
                                  if(year>nowyear){
                                      Intent i = new Intent(context.getApplicationContext(), Search_7.class);
                                      i.putExtra("uid",model.getUid());
                                      context.startActivity(i);
                                      return;
                                  }
                                  if(year == nowyear){
                                      if (month>nowmonth){
                                          Intent i = new Intent(context.getApplicationContext(),Search_7.class);
                                          i.putExtra("uid",model.getUid());
                                          context.startActivity(i);
                                          return;
                                      }
                                  }
                                  if(year == nowyear){
                                      if(month == nowmonth){
                                          if(day>=nowday){
                                              Intent i = new Intent(context.getApplicationContext(),Search_7.class);
                                              i.putExtra("uid",model.getUid());
                                              context.startActivity(i);
                                              return;
                                          }
                                      }
                                  }
                                  DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
                                  ref.child("drive").removeValue();
                                  ref.child("People").removeValue();
                                  ref.child("customer").removeValue();
                                  ref.child("travel").removeValue();
                                  ref.child("truecustomer").removeValue();
                                  ref.child("active").setValue("no");
                                  Intent i =new Intent(context.getApplicationContext(), Error_1.class);
                                  context.startActivity(i);
                              }
                          }else{
                              Intent i =new Intent(context.getApplicationContext(),Error_1.class);
                              context.startActivity(i);
                          }
                      }else {
                          Intent i =new Intent(context.getApplicationContext(),Error_1.class);
                          context.startActivity(i);
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
