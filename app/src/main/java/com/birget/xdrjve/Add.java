package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class Add extends AppCompatActivity {
    private EditText Email;
    private CountryCodePicker CCP;
    private EditText PhoneNumber;
    private Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(0).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Add.this,Qoy_9.class);
                        startActivity(i);
                        break;
                    case R.id.myinfo:
                        Intent b = new Intent(Add.this, Info_2.class);
                        startActivity(b);
                        break;
                    case R.id.message:
                        Intent c = new Intent(Add.this, Message_1_2.class);
                        startActivity(c);
                        break;
                    case R.id.customer:
                        Intent d = new Intent(Add.this, Search_2.class);
                        startActivity(d);
                        break;
                    case R.id.rel1:
                        Intent e = new Intent(Add.this, Request.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        Email = (EditText)findViewById(R.id.email);
        Submit = (Button)findViewById(R.id.bitir);
        PhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        CCP = (CountryCodePicker)findViewById(R.id.ccp);
        CCP.registerCarrierNumberEditText(PhoneNumber);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String phone = CCP.getFullNumberWithPlus();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                String uid = user.getUid();
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                db1.child("truecustomer").child(String.valueOf(getTaskId())).child("name").setValue(email);
                db1.child("truecustomer").child(String.valueOf(getTaskId())).child("phone").setValue(phone);
                db1.child("truecustomer").child(String.valueOf(getTaskId())).child("uid").setValue(String.valueOf(getTaskId()));
                Intent i =new Intent(Add.this, Customer.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
