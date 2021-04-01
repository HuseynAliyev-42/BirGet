package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

import java.util.ArrayList;
import java.util.List;

public class Request extends AppCompatActivity {
    private BottomNavigationView nav1;
    private ListView listViewArtist;
    private DatabaseReference db1;
    private List<CustomerRequest> artistList;
    private LinearLayout RequestButton,CustomerButton;
    private Button Add;
    private Button Remove;
    private String uid;
    private LinearLayout ScaleRequest,ScaleCustomer;
    private Button Stop;
    private ListView CustomerList;
    private ArrayList<CustomerRequest> list1;
    private RelativeLayout Request,Customer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(4).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Request.this, Qoy_9.class);
                        startActivity(i);
                        break;
                    case R.id.myinfo:
                        Intent b = new Intent(Request.this, Info_2.class);
                        startActivity(b);
                        break;
                    case R.id.message:
                        Intent c = new Intent(Request.this, Message_1_2.class);
                        startActivity(c);
                        break;
                    case R.id.customer:
                        Intent d = new Intent(Request.this, Search_2.class);
                        startActivity(d);
                        break;
                    case R.id.rel1:
                        Intent e = new Intent(Request.this,Request.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
       Requset();
       RequestButton = (LinearLayout)findViewById(R.id.line1_1);
       CustomerButton = (LinearLayout)findViewById(R.id.line_1_2);
       RequestButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Requset();
           }
       });
       CustomerButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Customer();
           }
       });
    }
    private void Customer(){
        Request = (RelativeLayout)findViewById(R.id.request);
        Customer1 = (RelativeLayout)findViewById(R.id.customer1);
        ScaleRequest = ((LinearLayout)findViewById(R.id.scalerequest));
        ScaleCustomer = (LinearLayout)findViewById(R.id.scalecustomer);
        ScaleRequest.setVisibility(View.INVISIBLE);
        ScaleCustomer.setVisibility(View.VISIBLE);
        Request.setVisibility(View.GONE);
        Customer1.setVisibility(View.VISIBLE);
        CustomerList = (ListView)findViewById(R.id.customerlist);
        list1 = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        final DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("truecustomer");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1.clear();
                for(DataSnapshot data2 : dataSnapshot.getChildren()){
                    CustomerRequest customerRequest = data2.getValue(CustomerRequest.class);

                    list1.add(customerRequest);
                }
                CustomRequestAdapter_2 adapter = new CustomRequestAdapter_2(Request.this,list1);
                CustomerList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Requset(){
        Request = (RelativeLayout)findViewById(R.id.request);
        Customer1 = (RelativeLayout)findViewById(R.id.customer1);
        ScaleRequest = ((LinearLayout)findViewById(R.id.scalerequest));
        ScaleCustomer = (LinearLayout)findViewById(R.id.scalecustomer);
        ScaleRequest.setVisibility(View.VISIBLE);
        ScaleCustomer.setVisibility(View.GONE);
        Request.setVisibility(View.VISIBLE);
        Customer1.setVisibility(View.INVISIBLE);
        String a = getIntent().getStringExtra("message");
        if(a == null){
            Toast.makeText(Request.this,"Zəhmət olmasa, elani təsdiq etmək üçün həmin o şəxsin adının üzərinə basılı tutun!",Toast.LENGTH_SHORT).show();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();
            String uid = user.getUid();
            Add = (Button)findViewById(R.id.add);
            Remove = (Button)findViewById(R.id.remove);
            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Add();
                }
            });
            Remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Remove();
                }
            });
            db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("customer");
            artistList = new ArrayList<>();
            listViewArtist = (ListView)findViewById(R.id.list1);
            db1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    artistList.clear();
                    for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                        CustomerRequest customerRequest = artistSnapshot.getValue(CustomerRequest.class);

                        artistList.add(customerRequest);
                    }
                    CustomerRequestAdapter adapter = new CustomerRequestAdapter(Request.this,artistList);
                    listViewArtist.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return;
        }else{
            Toast.makeText(Request.this,"Zəhmət olmasa, elani təsdiq etmək üçün həmin o şəxsin adının üzərinə basılı tutun!",Toast.LENGTH_SHORT).show();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();
            String uid = user.getUid();
            Add = (Button)findViewById(R.id.add);
            Remove = (Button)findViewById(R.id.remove);
            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Add();
                }
            });
            Remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Remove();
                }
            });
            db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("customer");
            artistList = new ArrayList<>();
            listViewArtist = (ListView)findViewById(R.id.list1);
            db1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    artistList.clear();
                    for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                        CustomerRequest customerRequest = artistSnapshot.getValue(CustomerRequest.class);
                        artistList.add(customerRequest);
                    }
                    CustomerRequestAdapter adapter = new CustomerRequestAdapter(Request.this,artistList);
                    listViewArtist.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            nav1.getMenu().getItem(1).setIcon(R.drawable.warn);
            nav1.getMenu().getItem(1).setChecked(true);
        }
    }
    private void Add() {
//whatever you want just you have to launch overhere.
                Intent i = new Intent(Request.this, com.birget.xdrjve.Add.class);
                startActivity(i);
    }
    private void Remove(){
//whatever you want just you have to launch overhere.
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                uid = user.getUid();
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("truecustomer");
                db2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            modelid modelid = dataSnapshot1.getValue(com.birget.xdrjve.modelid.class);
                            String receiveuid = modelid.getUid();
                            DatabaseReference d1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(receiveuid);
                            FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                            String myuid = user1.getUid();
                            d1.child("activity").setValue(myuid);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                db1.child("drive").removeValue();
                db1.child("customer").removeValue();
                db1.child("tra").removeValue();
                db1.child("travel").setValue("no");
                db1.child("truecustomer").removeValue();
                db1.child("falsecustomer").removeValue();
                Intent i =new Intent(Request.this, Activity_3.class);
                startActivity(i);
                finish();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
