package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Customer extends AppCompatActivity {
    private BottomNavigationView nav1;
    private ListView listViewArtist;
    private DatabaseReference db1;
    private List<CustomerRequest> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        nav1 = (BottomNavigationView) findViewById(R.id.navigation);
        nav1.getMenu().getItem(3).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent i = new Intent(Customer.this, Qoy_9.class);
                        startActivity(i);
                        break;
                    case R.id.myinfo:
                        Intent b = new Intent(Customer.this, Info_2.class);
                        startActivity(b);
                        break;
                    case R.id.message:
                        Intent c = new Intent(Customer.this, Message_1_2.class);
                        startActivity(c);
                        break;
                    case R.id.customer:
                        Intent d = new Intent(Customer.this, Customer.class);
                        startActivity(d);
                        break;
                    case R.id.rel1:
                        Intent e = new Intent(Customer.this, Request.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
    }
}
