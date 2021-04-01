package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Qoy_8_5_2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner Number;
    private EditText Place1;
    private EditText Place2;
    private EditText Place3;
    private EditText Place4;
    private EditText Place5;
    private Button Submit;
    private String Talk;
    private String uid;
    private String Cigar;
    private String CarYear;
    private String Price;
    private String FromPlace;
    private String ToPlace;
    private String Time;
    private String Month;
    private String Day;
    private String Year;
    private String MaxYer;
    private String CarNumber;
    private String CarBrand;
    private String NumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoy_8_5_2);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(2).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Qoy_8_5_2.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Qoy_8_5_2.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Qoy_8_5_2.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Qoy_8_5_2.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Qoy_8_5_2.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        ArrayList<String> numberoptions = new ArrayList<String>();
        numberoptions.add("Səyahətdə dayanacaqların sayını seçin!");
        numberoptions.add("0");
        numberoptions.add("1");
        numberoptions.add("2");
        numberoptions.add("3");
        numberoptions.add("4");
        numberoptions.add("5");
        ArrayAdapter<String> numberadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,numberoptions);
        numberadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Number = (Spinner)findViewById(R.id.number);
        Number.setOnItemSelectedListener(this);
        Number.setAdapter(numberadapter);
        Talk = getIntent().getStringExtra("Talk");
        Cigar = getIntent().getStringExtra("Cigar");
        CarYear = getIntent().getStringExtra("CarYear");
        Price = getIntent().getStringExtra("Price");
        FromPlace = getIntent().getStringExtra("FromPlace");
        ToPlace = getIntent().getStringExtra("ToPlace");
        Time = getIntent().getStringExtra("Time");
        Day = getIntent().getStringExtra("Day");
        Month = getIntent().getStringExtra("Month");
        Year = getIntent().getStringExtra("Year");
        MaxYer = getIntent().getStringExtra("MaxYer");
        CarNumber = getIntent().getStringExtra("CarNumber");
        CarBrand = getIntent().getStringExtra("CarBrand");
        Submit = (Button)findViewById(R.id.submit);
        Place1 = (EditText)findViewById(R.id.place1);
        Place2 = (EditText)findViewById(R.id.place2);
        Place3 = (EditText)findViewById(R.id.place3);
        Place4 = (EditText)findViewById(R.id.place4);
        Place5 = (EditText)findViewById(R.id.place5);
        Number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                NumberText = Number.getSelectedItem().toString();
                if (NumberText.matches("Səyahətdə dayanacaqların sayını seçin!")) {
                    Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa, dayanacaq sayını seçin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (NumberText.matches("0")) {
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Qoy_8_5_2.this, Qoy_8_6_2.class);
                            i.putExtra("Talk", Talk);
                            i.putExtra("Cigar", Cigar);
                            i.putExtra("CarYear", CarYear);
                            i.putExtra("Price", Price);
                            i.putExtra("FromPlace", FromPlace);
                            i.putExtra("ToPlace", ToPlace);
                            i.putExtra("Time", Time);
                            i.putExtra("Month", Month);
                            i.putExtra("Day", Day);
                            i.putExtra("Year", Year);
                            i.putExtra("MaxYer", MaxYer);
                            i.putExtra("CarNumber", CarNumber);
                            i.putExtra("CarBrand", CarBrand);
                            i.putExtra("Number", NumberText);
                            startActivity(i);
                        }
                    });
                }
                if (NumberText.matches("1")) {
                    Place1.setVisibility(View.VISIBLE);
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String Place1Text = Place1.getText().toString();
                            if (Place1Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa, 1-ci dayanacağı seçin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent i = new Intent(Qoy_8_5_2.this, Qoy_8_6_2.class);
                            i.putExtra("Talk", Talk);
                            i.putExtra("Cigar", Cigar);
                            i.putExtra("CarYear", CarYear);
                            i.putExtra("Price", Price);
                            i.putExtra("FromPlace", FromPlace);
                            i.putExtra("ToPlace", ToPlace);
                            i.putExtra("Time", Time);
                            i.putExtra("Month", Month);
                            i.putExtra("Day", Day);
                            i.putExtra("Year", Year);
                            i.putExtra("MaxYer", MaxYer);
                            i.putExtra("CarNumber", CarNumber);
                            i.putExtra("CarBrand", CarBrand);
                            i.putExtra("Number", NumberText);
                            i.putExtra("Place1", Place1Text);
                            startActivity(i);
                        }
                    });
                }
                if (NumberText.matches("2")) {
                    Place1.setVisibility(View.VISIBLE);
                    Place2.setVisibility(View.VISIBLE);
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String Place1Text = Place1.getText().toString();
                            String Place2Text = Place2.getText().toString();
                            if (Place1Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,1-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place2Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,2-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent i = new Intent(Qoy_8_5_2.this, Qoy_8_6_2.class);
                            i.putExtra("Talk", Talk);
                            i.putExtra("Cigar", Cigar);
                            i.putExtra("CarYear", CarYear);
                            i.putExtra("Price", Price);
                            i.putExtra("FromPlace", FromPlace);
                            i.putExtra("ToPlace", ToPlace);
                            i.putExtra("Time", Time);
                            i.putExtra("Month", Month);
                            i.putExtra("Day", Day);
                            i.putExtra("Year", Year);
                            i.putExtra("MaxYer", MaxYer);
                            i.putExtra("CarNumber", CarNumber);
                            i.putExtra("CarBrand", CarBrand);
                            i.putExtra("Number", NumberText);
                            i.putExtra("Place1", Place1Text);
                            i.putExtra("Place2", Place2Text);
                            startActivity(i);
                        }
                    });
                }
                if (NumberText.matches("3")) {
                    Place1.setVisibility(View.VISIBLE);
                    Place2.setVisibility(View.VISIBLE);
                    Place3.setVisibility(View.VISIBLE);
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String Place1Text = Place1.getText().toString();
                            String Place2Text = Place2.getText().toString();
                            String Place3Text = Place3.getText().toString();
                            if (Place1Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,1-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place2Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,2-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place3Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,3-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent i = new Intent(Qoy_8_5_2.this, Qoy_8_6_2.class);
                            i.putExtra("Talk", Talk);
                            i.putExtra("Cigar", Cigar);
                            i.putExtra("CarYear", CarYear);
                            i.putExtra("Price", Price);
                            i.putExtra("FromPlace", FromPlace);
                            i.putExtra("ToPlace", ToPlace);
                            i.putExtra("Time", Time);
                            i.putExtra("Month", Month);
                            i.putExtra("Day", Day);
                            i.putExtra("Year", Year);
                            i.putExtra("MaxYer", MaxYer);
                            i.putExtra("CarNumber", CarNumber);
                            i.putExtra("CarBrand", CarBrand);
                            i.putExtra("Number", NumberText);
                            i.putExtra("Place1", Place1Text);
                            i.putExtra("Place2", Place2Text);
                            i.putExtra("Place3", Place3Text);
                            startActivity(i);
                        }
                    });
                }
                if (NumberText.matches("4")) {
                    Place1.setVisibility(View.VISIBLE);
                    Place2.setVisibility(View.VISIBLE);
                    Place3.setVisibility(View.VISIBLE);
                    Place4.setVisibility(View.VISIBLE);
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String Place1Text = Place1.getText().toString();
                            String Place2Text = Place2.getText().toString();
                            String Place3Text = Place3.getText().toString();
                            String Place4Text = Place4.getText().toString();
                            if (Place1Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,1-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place2Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,2-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place3Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,3-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place4Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,4-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent i = new Intent(Qoy_8_5_2.this, Qoy_8_6_2.class);
                            i.putExtra("Talk", Talk);
                            i.putExtra("Cigar", Cigar);
                            i.putExtra("CarYear", CarYear);
                            i.putExtra("Price", Price);
                            i.putExtra("FromPlace", FromPlace);
                            i.putExtra("ToPlace", ToPlace);
                            i.putExtra("Time", Time);
                            i.putExtra("Month", Month);
                            i.putExtra("Day", Day);
                            i.putExtra("Year", Year);
                            i.putExtra("MaxYer", MaxYer);
                            i.putExtra("CarNumber", CarNumber);
                            i.putExtra("CarBrand", CarBrand);
                            i.putExtra("Number", NumberText);
                            i.putExtra("Place1", Place1Text);
                            i.putExtra("Place2", Place2Text);
                            i.putExtra("Place3", Place3Text);
                            i.putExtra("Place4", Place4Text);
                            startActivity(i);
                        }
                    });
                }
                if (NumberText.matches("5")) {
                    Place1.setVisibility(View.VISIBLE);
                    Place2.setVisibility(View.VISIBLE);
                    Place3.setVisibility(View.VISIBLE);
                    Place4.setVisibility(View.VISIBLE);
                    Place5.setVisibility(View.VISIBLE);
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String Place1Text = Place1.getText().toString();
                            String Place2Text = Place2.getText().toString();
                            String Place3Text = Place3.getText().toString();
                            String Place4Text = Place4.getText().toString();
                            String Place5Text = Place5.getText().toString();
                            if (Place1Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,1-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place2Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,2-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place3Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,3-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place4Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,4-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Place5Text.matches("")) {
                                Toast.makeText(Qoy_8_5_2.this, "Zəhmət olmasa,5-ci dayanacağı qeyd edin!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent i = new Intent(Qoy_8_5_2.this, Qoy_8_6_2.class);
                            i.putExtra("Talk", Talk);
                            i.putExtra("Cigar", Cigar);
                            i.putExtra("CarYear", CarYear);
                            i.putExtra("Price", Price);
                            i.putExtra("FromPlace", FromPlace);
                            i.putExtra("ToPlace", ToPlace);
                            i.putExtra("Time", Time);
                            i.putExtra("Month", Month);
                            i.putExtra("Day", Day);
                            i.putExtra("Year", Year);
                            i.putExtra("MaxYer", MaxYer);
                            i.putExtra("CarNumber", CarNumber);
                            i.putExtra("CarBrand", CarBrand);
                            i.putExtra("Number", NumberText);
                            i.putExtra("Place1", Place1Text);
                            i.putExtra("Place2", Place2Text);
                            i.putExtra("Place3", Place3Text);
                            i.putExtra("Place4", Place4Text);
                            i.putExtra("Place5", Place5Text);
                            startActivity(i);
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
