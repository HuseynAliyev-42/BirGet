package com.birget.xdrjve;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Search_3 extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private SearchView searchView;
    private double latitude = 0.00, longtitude = 0.00;
    private Button Submit;
    private int success = 0;
    private String location;
    private String FromPlace;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoy_2);
        FromPlace = getIntent().getStringExtra("FromPlace");
        Submit = (Button) findViewById(R.id.submit);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        searchView = (SearchView) findViewById(R.id.sv_location);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                location = searchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(Search_3.this, Locale.ENGLISH);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addressList == null || addressList.isEmpty()) {
                        Toast.makeText(Search_3.this, "Bu adda yer yoxdur!", Toast.LENGTH_LONG).show();
                        return false;
                    } else {
                        final Address address = addressList.get(0);
                        if (address.getLocality() == null || address.getLocality().matches("") || addressList.get(0) == null) {
                            Toast.makeText(Search_3.this, "Bu adda yer yoxdur!", Toast.LENGTH_LONG).show();
                            return false;
                        } else {
                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                            map.addMarker(new MarkerOptions().position(latLng).title(location));
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    final String city = address.getLocality();
                                    String adresstext = address.getAddressLine(0);
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String MyUid = user.getUid();
                                    DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
                                    db1.child("placeto").setValue(adresstext);
                                    db1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.hasChild("drive")){
                                                Intent i =new Intent(Search_3.this,Qoy_9.class);
                                                i.putExtra("FromPlace",FromPlace);
                                                i.putExtra("ToPlace",city);
                                                i.putExtra("Type","Axtar");
                                                startActivity(i);
                                                finish();
                                            }else{
                                                Intent i =new Intent(Search_3.this,Search_4.class);
                                                i.putExtra("FromPlace",FromPlace);
                                                i.putExtra("ToPlace",city);
                                                i.putExtra("Type","Axtar");
                                                startActivity(i);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    Intent i = new Intent(Search_3.this, Search_4.class);
                                    i.putExtra("FromPlace", FromPlace);
                                    i.putExtra("ToPlace",city);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        }
                    }
                }
                if (location == null) {
                    Toast.makeText(Search_3.this, "Zəhmət olmasa, bir yer adı axtarın!", Toast.LENGTH_LONG).show();
                    return false;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map= googleMap;

    }
}