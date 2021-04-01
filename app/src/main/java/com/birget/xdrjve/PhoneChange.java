package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class PhoneChange extends AppCompatActivity {
    private CountryCodePicker CCP;
    private EditText PhoneNumber;
    private Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_change);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        final BottomNavigationView nav2 = (BottomNavigationView)findViewById(R.id.navigation2);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db100 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db100.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                if(dataSnapshot.hasChild("drive")){
                    nav1.setVisibility(View.INVISIBLE);
                    nav2.setVisibility(View.VISIBLE);
                    nav2.getMenu().getItem(2).setChecked(true);
                    nav2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.home:
                                    Intent i =new Intent(PhoneChange.this,Qoy_9.class);
                                    startActivity(i);
                                    break;
                                case R.id.myinfo:
                                    Intent b = new Intent(PhoneChange.this, Info_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.message:
                                    Intent c = new Intent(PhoneChange.this, Message_1_2.class);
                                    startActivity(c);
                                    break;
                                case R.id.customer:
                                    Intent d = new Intent(PhoneChange.this, Search_2.class);
                                    startActivity(d);
                                    break;
                                case R.id.rel1:
                                    Intent e = new Intent(PhoneChange.this, Request.class);
                                    startActivity(e);
                                    break;
                            }
                            return true;
                        }
                    });
                }else{
                    nav1.setVisibility(View.VISIBLE);
                    nav2.setVisibility(View.INVISIBLE);
                    nav1.getMenu().getItem(4).setChecked(true);
                    nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.home:
                                    Intent i =new Intent(PhoneChange.this,Activity_3.class);
                                    startActivity(i);
                                    break;
                                case R.id.contact:
                                    Intent b = new Intent(PhoneChange.this,Search_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.info:
                                    Intent c = new Intent(PhoneChange.this,Qoy_1.class);
                                    startActivity(c);
                                    break;
                                case R.id.message:
                                    Intent d = new Intent(PhoneChange.this,Message_1.class);
                                    startActivity(d);
                                    break;
                                case R.id.profilim:
                                    Intent e = new Intent(PhoneChange.this,Info.class);
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
        PhoneNumber = (EditText)findViewById(R.id.phoneNumber);
        CCP = (CountryCodePicker)findViewById(R.id.ccp);
        CCP.registerCarrierNumberEditText(PhoneNumber);
        Submit = (Button)findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//whatever you want just you have to launch overhere.
                        String CCPText = CCP.getFullNumberWithPlus();
                        if(CCPText.matches("")){
                            Toast.makeText(PhoneChange.this,"Zəhmət olmasa, boşluqları doldurun!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        FirebaseUser user = auth.getCurrentUser();
                        String uid = user.getUid();
                        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                        db1.child("phone").setValue(CCPText);
                        Intent i =new Intent(PhoneChange.this, Search_9.class);
                        startActivity(i);
                        finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
