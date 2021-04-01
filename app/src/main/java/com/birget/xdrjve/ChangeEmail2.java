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

public class ChangeEmail2 extends AppCompatActivity {
    private Button Submit;
    private EditText Email;
    private String  email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email2);
        BottomNavigationView nav1 = (BottomNavigationView) findViewById(R.id.navigation);
        nav1.getMenu().getItem(2).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent i = new Intent(ChangeEmail2.this, Qoy_9.class);
                        startActivity(i);
                        break;
                    case R.id.myinfo:
                        Intent b = new Intent(ChangeEmail2.this, Info_2.class);
                        startActivity(b);
                        break;
                    case R.id.message:
                        Intent c = new Intent(ChangeEmail2.this, Message_1_2.class);
                        startActivity(c);
                        break;
                    case R.id.customer:
                        Intent d = new Intent(ChangeEmail2.this, Search_2.class);
                        startActivity(d);
                        break;
                    case R.id.rel1:
                        Intent e = new Intent(ChangeEmail2.this, Request.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        Email = (EditText) findViewById(R.id.email);
        Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//whatever you want just you have to launch overhere.
                final ProgressDialog csprogress=new ProgressDialog(ChangeEmail2.this);
                csprogress.setMessage("Yüklənir...");
                csprogress.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
//whatever you want just you have to launch overhere.
                    }
                }, 5000);
                final String emailText = Email.getText().toString();
                if (emailText.matches("")) {
                    Toast.makeText(ChangeEmail2.this, "Zəhmət olmasa,boşluqları doldurun!", Toast.LENGTH_SHORT).show();
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
                                                        Toast.makeText(ChangeEmail2.this, "Uğurla hesab dəyişdirildi!", Toast.LENGTH_SHORT).show();
                                                        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid).child("email");
                                                        db.setValue(emailText);
                                                        Intent i = new Intent(ChangeEmail2.this, Info_2.class);
                                                        i.putExtra("info","email");
                                                        startActivity(i);
                                                        finish();
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
