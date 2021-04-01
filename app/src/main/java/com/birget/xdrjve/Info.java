package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class Info extends AppCompatActivity {
    private ImageView imageView;
    private  String uid;
    private Button ChooseImage,UploadImage;
    private Uri filePath;
    private TextView ChangePassword;
    FirebaseStorage storage;
    private ImageView Profilimage;
    StorageReference storageReference;
    private Button Upload;
    private TextView ChangeEmail;
    private String url;
    private TextView Email;
    private TextView Phone;
    private TextView ChangePhone;
    private TextView ShowScore;
    private final int PICK_IMAGE_REQUEST = 71;
    private TextView Contact;
    private TextView Name;
    private TextView LogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(4).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Info.this, Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Info.this, Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Info.this, Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Info.this, Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Info.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        LogOut = (TextView)findViewById(R.id.logout);
        Name = (TextView)findViewById(R.id.name);
        ChangePassword = (TextView)findViewById(R.id.changepassword);
        ShowScore = (TextView)findViewById(R.id.showsccore);
        Contact = (TextView)findViewById(R.id.contactd);
        ChangePhone = (TextView)findViewById(R.id.changephone);
        ChangeEmail = (TextView)findViewById(R.id.changemial);
        Phone = (TextView)findViewById(R.id.phone);
        Email = (TextView)findViewById(R.id.email);
        imageView = (ImageView)findViewById(R.id.image);
        Profilimage = (ImageView) findViewById(R.id.profilimage);
        Upload = (Button)findViewById(R.id.upload);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        uid = user.getUid();
        SetImage();
        SetEmail();
        SetPhone();
        SetName();
        Profilimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage();
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });
        ChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailChange();
            }
        });
        ChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneChange();
            }
        });
        ShowScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreShow();
            }
        });
        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactChange();
            }
        });
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog csprogress=new ProgressDialog( Info.this);
                csprogress.setMessage("Yüklənir...");
                csprogress.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        csprogress.dismiss();
//whatever you want just you have to launch overhere.
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        auth.signOut();
                        Intent i =new Intent(Info.this, Activity_2.class);
                        startActivity(i);
                        finish();
                    }
                }, 13000);
            }
        });
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Info.this, ChangePass.class);
                startActivity(i);
            }
        });
    }
    private void SetName(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String myUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                Name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ChooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Profilimage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void UploadImage(){
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Yüklənir...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // getting image uri and converting into string
                                    Uri downloadUrl = uri;
                                    String image = downloadUrl.toString();
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                    reference.child("profil").setValue(image);
                                    Toast.makeText(Info.this, "Yuklendi", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Info.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Yuklenir "+(int)progress+"%");
                        }
                    });
        }
    }
    private void SetImage(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url = dataSnapshot.child("profil").getValue().toString();
                Picasso.get().load(url).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void SetEmail(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mail = dataSnapshot.child("email").getValue().toString();
                Email.setText(mail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void  SetPhone(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String phone = dataSnapshot.child("phone").getValue().toString();
                Phone.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void EmailChange(){
        Intent i =new Intent(Info.this, com.birget.xdrjve.ChangeEmail.class);
        startActivity(i);
    }
    private void PhoneChange(){
        Intent i =new Intent(Info.this, PhoneChange.class);
        startActivity(i);
    }
    private void ContactChange(){
        Intent i =new Intent(Info.this, Home.class);
        startActivity(i);
    }
    private void ScoreShow(){
        Intent i = new Intent(Info.this, ScoreShow.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
