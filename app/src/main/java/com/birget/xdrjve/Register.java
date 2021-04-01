package com.birget.xdrjve;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView TermText;
    private CheckBox TermCheckBox;
    private AdView mAdView1,mAdView2;
    private Spinner Gender,Birthyear;
    private ImageView ShowPassword;
    private Button Submit;
    private CountryCodePicker CCP;
    private String PhoneNumberText;
    private EditText Name,Surname,Email,Password,PhoneNumber;
    private String GenderText,BirthYearText,NameText,SurnameText,EmailText,PasswordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        List<String> gendertype =new ArrayList<String>();
        gendertype.add("Cinsiniz");
        gendertype.add("Kişi");
        gendertype.add("Qadın");
        List<String> years = new ArrayList<String>();
        years.add("Təvəllüd ilinizi seçin");
        years.add("2001");
        years.add("2000");
        years.add("1999");
        years.add("1998");
        years.add("1997");
        years.add("1996");
        years.add("1995");
        years.add("1994");
        years.add("1993");
        years.add("1992");
        years.add("1991");
        years.add("1990");
        years.add("1989");
        years.add("1988");
        years.add("1987");
        years.add("1986");
        years.add("1985");
        years.add("1984");
        years.add("1983");
        years.add("1982");
        years.add("1981");
        years.add("1980");
        years.add("1979");
        years.add("1978");
        years.add("1977");
        years.add("1976");
        years.add("1975");
        years.add("1974");
        years.add("1973");
        years.add("1972");
        years.add("1971");
        years.add("1970");
        years.add("1969");
        years.add("1968");
        years.add("1967");
        years.add("1966");
        years.add("1965");
        years.add("1964");
        years.add("1963");
        years.add("1962");
        years.add("1961");
        years.add("1960");
        years.add("1959");
        years.add("1958");
        years.add("1957");
        years.add("1956");
        years.add("1955");
        years.add("1954");
        years.add("1953");
        years.add("1952");
        years.add("1951");
        years.add("1950");
        years.add("1949");
        years.add("1948");
        years.add("1947");
        years.add("1946");
        years.add("1945");
        years.add("1944");
        years.add("1943");
        years.add("1942");
        years.add("1941");
        years.add("1940");
        years.add("1939");
        years.add("1938");
        years.add("1937");
        years.add("1936");
        years.add("1935");
        years.add("1934");
        years.add("1933");
        years.add("1932");
        years.add("1931");
        years.add("1930");
        years.add("1929");
        years.add("1928");
        years.add("1927");
        years.add("1926");
        years.add("1925");
        years.add("1924");
        years.add("1923");
        years.add("1922");
        years.add("1921");
        years.add("1920");
        years.add("1919");
        ShowPassword = (ImageView)findViewById(R.id.showpassword);
        Birthyear = (Spinner)findViewById(R.id.birthyear);
        Birthyear.setOnItemSelectedListener(this);
        Gender = (Spinner)findViewById(R.id.gender);
        Gender.setOnItemSelectedListener(this);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gendertype);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Birthyear.setAdapter(dataAdapter);
        Gender.setAdapter(genderAdapter);
        MobileAds.initialize(this, "ca-app-pub-4810068176185955~8190980825");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4810068176185955/4151663464");
        //
        mAdView1 = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest);
        Submit = (Button)findViewById(R.id.submit);
        Name = (EditText) findViewById(R.id.name);
        Surname = (EditText) findViewById(R.id.surname);
        Email = (EditText)findViewById(R.id.email);
        PhoneNumber = (EditText)findViewById(R.id.phoneNumber);
        CCP = (CountryCodePicker)findViewById(R.id.ccp);
        CCP.registerCarrierNumberEditText(PhoneNumber);
        Password = (EditText)findViewById(R.id.password);
        TermText = (TextView)findViewById(R.id.termText);
        TermCheckBox = (CheckBox)findViewById(R.id.termCheck);
        TermText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Register.this);
                dialog.setContentView(R.layout.custom);
                dialog.show();
            }
        });
        ShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                Password.setSelection(Password.length());
                ShowPassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        Password.setSelection(Password.length());
                        ShowPassword.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                                Password.setSelection(Password.length());
                                ShowPassword.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                        Password.setSelection(Password.length());
                                        ShowPassword.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                                                Password.setSelection(Password.length());
                                                ShowPassword.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                                        Password.setSelection(Password.length());
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//whatever you want just you have to launch overhere.
                        PhoneNumberText = CCP.getFullNumberWithPlus();
                        NameText = Name.getText().toString();
                        SurnameText = Surname.getText().toString();
                        EmailText = Email.getText().toString();
                        PasswordText = Password.getText().toString();
                        BirthYearText = Birthyear.getSelectedItem().toString();
                        GenderText = Gender.getSelectedItem().toString();
                        if(PhoneNumberText.matches("")){
                            Toast.makeText(Register.this,"Zəhmət olmasa, telelefon nömrəsini qeyd edin!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(TermCheckBox.isChecked()){
                            if(NameText.matches("")){
                                Toast.makeText(Register.this,"Zəhmət olmasa, adınızı daxil edin!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (SurnameText.matches("")){
                                Toast.makeText(Register.this,"Zəhmət olmasa,soyadınız daxil edin!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(EmailText.matches("")){
                                Toast.makeText(Register.this,"Zəhmət olmasa,mailinizi daxil edin!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(PasswordText.matches("")){
                                Toast.makeText(Register.this,"Zəhmət olmasa,şifrə daxil edin!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(BirthYearText.matches("Təvəllüd ilinizi seçin")){
                                Toast.makeText(Register.this,"Zəhmət olmasa,təvəllüd ilinizi seçin!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(GenderText.matches("Cinsiniz")){
                                Toast.makeText(Register.this,"Zəhmət olmasa, cinsinizi seçin!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            FirebaseAuth auth = FirebaseAuth.getInstance();
                            auth.createUserWithEmailAndPassword(EmailText,PasswordText)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                FirebaseAuth auth2 = FirebaseAuth.getInstance();
                                                FirebaseUser user = auth2.getCurrentUser();
                                                String uid = user.getUid();
                                                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                                db1.child("profil").setValue("https://firebasestorage.googleapis.com/v0/b/xdrive-22228.appspot.com/o/prof.png?alt=media&token=143a9f2f-85a2-4d7a-8d8d-078f2f381c37");
                                                db1.child("gender").setValue(GenderText);
                                                db1.child("birthyear").setValue(BirthYearText);
                                                db1.child("password").setValue(PasswordText);
                                                db1.child("email").setValue(EmailText);
                                                db1.child("name").setValue(NameText);
                                                db1.child("surname").setValue(SurnameText);
                                                db1.child("uid").setValue(uid);
                                                db1.child("phone").setValue(PhoneNumberText);
                                                db1.child("Ratings").child("total").setValue("0.0");
                                                db1.child("travel").setValue("yes");
                                                Intent i =new Intent(Register.this, Activity_3.class);
                                                startActivity(i);
                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(Register.this,"Zəhmət olmasa, şərtləri oxuyun və qəbul edin!",Toast.LENGTH_SHORT).show();
                        }
            }
        });
    }
    private void PaswordShow(){
        ShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                Password.setSelection(Password.length());

            }
        });
    }
    private void PasswordHide(){
        ShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                Password.setSelection(Password.length());
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
