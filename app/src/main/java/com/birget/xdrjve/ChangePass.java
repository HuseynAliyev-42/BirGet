package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
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

public class ChangePass extends AppCompatActivity {
    private EditText OldPass,NewPass;
    private Button Submit;
    private String Email;
    private String Password;
    private int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(0).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(ChangePass.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(ChangePass.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(ChangePass.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(ChangePass.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(ChangePass.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        a=0;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        final String myUid = user.getUid();
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid);
        db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Email = dataSnapshot.child("email").getValue().toString();
                Password = dataSnapshot.child("password").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        OldPass = (EditText)findViewById(R.id.oldpass);
        NewPass = (EditText)findViewById(R.id.newpass);
        Submit = (Button)findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String NewPassText = NewPass.getText().toString();
                final String OldPassText = OldPass.getText().toString();
                if(NewPassText.matches("")){
                    Toast.makeText(ChangePass.this,"Zəhmət olmasa, boşluqları doldurun!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(OldPassText.matches("")){
                    Toast.makeText(ChangePass.this,"Zəhmət olmasa, boşluqları doldurun!",Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                final String uid = user.getUid();
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String customer = dataSnapshot.child("password").getValue().toString();
                        if(customer.matches(OldPassText)){
                            final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();

                            AuthCredential credential = EmailAuthProvider
                                    .getCredential(Email,Password);
                            user1.reauthenticate(credential)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                user1.updatePassword(NewPassText).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(ChangePass.this,"Uğurla hesab dəyişdirildi!",Toast.LENGTH_SHORT).show();
                                                            DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid).child("password");
                                                            db.setValue(NewPassText);
                                                            Intent i =new Intent(ChangePass.this, Qoy_9.class);
                                                            startActivity(i);
                                                            finish();
                                                        }else{
                                                            Toast.makeText(ChangePass.this,"Xəta baş verdi!",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }else {

                                            }
                                        }
                                    });
                        }else{

                        }
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
