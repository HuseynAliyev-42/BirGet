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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeEmail extends AppCompatActivity {
    private Button Submit;
    private EditText Email;
    private String  email,password;
    private int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(4).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(ChangeEmail.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(ChangeEmail.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(ChangeEmail.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(ChangeEmail.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(ChangeEmail.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        Email = (EditText)findViewById(R.id.email);
        Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//whatever you want just you have to launch overhere.
                final ProgressDialog csprogress=new ProgressDialog(ChangeEmail.this);
                csprogress.setMessage("Yüklənir...");
                csprogress.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

//whatever you want just you have to launch overhere.
                    }
                }, 5000);
                        final String emailText = Email.getText().toString();
                        if(emailText.matches("")){
                            Toast.makeText(ChangeEmail.this,"Zəhmət olmasa,boşluqları doldurun!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        FirebaseUser user = auth.getCurrentUser();
                        final String myUid = user.getUid();
                        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid);
                        db3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                email = dataSnapshot.child("email").getValue().toString();
                                password = dataSnapshot.child("password").getValue().toString();
                                final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();

                                AuthCredential credential = EmailAuthProvider
                                        .getCredential(email, password);
                                user1.reauthenticate(credential)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    user1.updateEmail(emailText).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(ChangeEmail.this, "Uğurla hesab dəyişdirildi!", Toast.LENGTH_SHORT).show();
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid).child("email");
                                                                db.setValue(emailText);
                                                                Intent i = new Intent(ChangeEmail.this, Activity_3.class);
                                                                startActivity(i);
                                                                finish();
                                                                return;
                                                            } else {

                                                            }
                                                        }
                                                    });
                                                } else {

                                                }
                                            }
                                        });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
