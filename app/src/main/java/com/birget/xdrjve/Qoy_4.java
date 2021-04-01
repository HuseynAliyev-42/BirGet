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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class Qoy_4 extends AppCompatActivity {
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
        setContentView(R.layout.activity_qoy_4);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(2).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Qoy_4.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Qoy_4.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Qoy_4.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Qoy_4.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Qoy_4.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
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
                    Intent i = new Intent(Qoy_4.this, Search_5_2.class);
                    i.putExtra("FromPlace",FromPlace);
                    i.putExtra("ToPlace",ToPlace);
                    i.putExtra("Time",Time);
                    i.putExtra("Month",String.valueOf(NowMonth));
                    i.putExtra("Year",String.valueOf(NowYear));
                    i.putExtra("Day",String.valueOf(NowDay));
                    i.putExtra("Type",getIntent().getStringExtra("Type"));
                    startActivity(i);
                    finish();
                    return;
                }
                if(NowYear >ChoosenYear){
                    Toast.makeText(Qoy_4.this, "İndiki tarixdən əvvəl tarix keçərli deyil!1", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(NowYear == ChoosenYear && NowMonth > ChoosenMonth){
                    Toast.makeText(Qoy_4.this, "İndiki tarixdən əvvəl tarix keçərli deyil!2", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(NowYear == ChoosenYear){
                    if(NowMonth == ChoosenMonth){
                        if(NowDay > ChoosenDay){
                            Toast.makeText(Qoy_4.this, "İndiki tarixdən əvvəl tarix keçərli deyil!3", Toast.LENGTH_SHORT).show();
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
        Intent i =new Intent(Qoy_4.this, Qoy_5.class);
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
