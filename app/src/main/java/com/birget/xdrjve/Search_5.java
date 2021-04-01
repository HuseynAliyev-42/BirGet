package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.birget.xdrjve.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Search_5 extends AppCompatActivity {
    private Button Submit;
    private CalendarView CalendarView;
    private int ChoosenDay;
    private int ChoosenMonth;
    private int ChoosenYear;
    private int NowDay;
    private int NowMonth;
    private int NowYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_5);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        final BottomNavigationView nav2 = (BottomNavigationView)findViewById(R.id.navigation2);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                if(dataSnapshot.hasChild("drive")){
                    nav1.setVisibility(View.INVISIBLE);
                    nav2.setVisibility(View.VISIBLE);
                    nav2.getMenu().getItem(3).setChecked(true);
                    nav2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.home:
                                    Intent i =new Intent(Search_5.this,Qoy_9.class);
                                    startActivity(i);
                                    break;
                                case R.id.myinfo:
                                    Intent b = new Intent(Search_5.this, Info_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.message:
                                    Intent c = new Intent(Search_5.this, Message_1_2.class);
                                    startActivity(c);
                                    break;
                                case R.id.customer:
                                    Intent d = new Intent(Search_5.this, Search_2.class);
                                    startActivity(d);
                                    break;
                                case R.id.rel1:
                                    Intent e = new Intent(Search_5.this, Request.class);
                                    startActivity(e);
                                    break;
                            }
                            return true;
                        }
                    });
                }else{
                    nav1.setVisibility(View.VISIBLE);
                    nav2.setVisibility(View.INVISIBLE);
                    nav1.getMenu().getItem(1).setChecked(true);
                    nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.home:
                                    Intent i =new Intent(Search_5.this,Activity_3.class);
                                    startActivity(i);
                                    break;
                                case R.id.contact:
                                    Intent b = new Intent(Search_5.this,Search_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.info:
                                    Intent c = new Intent(Search_5.this,Qoy_1.class);
                                    startActivity(c);
                                    break;
                                case R.id.message:
                                    Intent d = new Intent(Search_5.this,Message_1.class);
                                    startActivity(d);
                                    break;
                                case R.id.profilim:
                                    Intent e = new Intent(Search_5.this,Info.class);
                                    startActivity(e);
                                    break;
                            }
                            return true;
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }
        });
        Submit = (Button)findViewById(R.id.submit);
        final Calendar calendar = Calendar.getInstance();
        CalendarView =(CalendarView) findViewById(R.id.calendar);
        CalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {
                ChoosenDay  = dayOfMonth;
                ChoosenMonth = month+1;
                ChoosenYear = year;
            }
        });
        //2057
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar1 = Calendar.getInstance();
                NowDay = calendar1.get(Calendar.DAY_OF_MONTH);
                NowMonth = calendar1.get(Calendar.MONTH) +1;
                NowYear = calendar1.get(Calendar.YEAR);
                if (ChoosenDay == 0 && ChoosenMonth == 0 && ChoosenYear == 0){
                    String FromPlace= getIntent().getStringExtra("FromPlace");
                    String ToPlace = getIntent().getStringExtra("ToPlace");
                    String Time = getIntent().getStringExtra("Time");
                    Intent i = new Intent(Search_5.this, Search_5_2.class);
                    i.putExtra("FromPlace",FromPlace);
                    i.putExtra("ToPlace",ToPlace);
                    i.putExtra("Time",Time);
                    i.putExtra("Month",String.valueOf(NowMonth));
                    i.putExtra("Year",String.valueOf(NowYear));
                    i.putExtra("Day",String.valueOf(NowDay));
                    startActivity(i);
                    finish();
                    return;
                }
                    if(NowYear >ChoosenYear){
                        Toast.makeText(Search_5.this, "İndiki tarixdən əvvəl tarix keçərli deyil!1", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(NowYear == ChoosenYear && NowMonth > ChoosenMonth){
                        Toast.makeText(Search_5.this, "İndiki tarixdən əvvəl tarix keçərli deyil!2", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(NowYear == ChoosenYear){
                        if(NowMonth == ChoosenMonth){
                            if(NowDay > ChoosenDay){
                                Toast.makeText(Search_5.this, "İndiki tarixdən əvvəl tarix keçərli deyil!3", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    set();
           }
        });
    }
    private void set(){
        String FromPlace = getIntent().getStringExtra("FromPlace");
        String ToPlace = getIntent().getStringExtra("ToPlace");
        String Time = getIntent().getStringExtra("Time");
        Intent i =new Intent(Search_5.this,Search_5_2.class);
        i.putExtra("FromPlace",FromPlace);
        i.putExtra("ToPlace",ToPlace);
        i.putExtra("Time",Time);
        i.putExtra("Day",String.valueOf(ChoosenDay));
        i.putExtra("Month",String.valueOf(ChoosenMonth));
        i.putExtra("Year",String.valueOf(ChoosenYear));
        startActivity(i);
        finish();
    }
}
