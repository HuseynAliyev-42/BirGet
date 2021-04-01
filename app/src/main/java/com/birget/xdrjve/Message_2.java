package com.birget.xdrjve;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message_2 extends AppCompatActivity {
    private String ReceiverUid;
    private ImageView Back;
    private ImageView SendButton;
    private EditText SendEdit;
    private FirebaseUser SenderUser;
    private MessageAdapter messageAdapter;
    private List<Chat> mChat;
    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private TextView Name;
    private ImageView Call;
    private CircleImageView CircleImage;
    private String phonenumber;
    private String profil;
    private String c;
    private String msg;
    private int dad;
    private DatabaseReference db1,db2;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAbo21vCA:APA91bG0bpvsZAiLhEJPLq2MJPNGoKJhlCCkaIi9b_iKWxbI7f6p2Nz61vnEoYE4FrM2heomUoLa15T94y6p2YIVo7ts49fbr0uaverMa4_a8RH2NdoYxEZPbn4oS4R14QY_2iqolK7U";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;
    private boolean notify = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_2);
        Call = (ImageView)findViewById(R.id.call);
        Back = (ImageView)findViewById(R.id.back);
        Name = (TextView)findViewById(R.id.name);
        CircleImage = (CircleImageView) findViewById(R.id.imagecircle);
        ReceiverUid = getIntent().getStringExtra("uid");
        c = ReceiverUid;
        recyclerView = (RecyclerView)findViewById(R.id.rec1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        SendButton = (ImageView)findViewById(R.id.sendbutton);
        SendEdit = (EditText)findViewById(R.id.sendedittext);
        SenderUser = FirebaseAuth.getInstance().getCurrentUser();
        db1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://xdrive-22228.firebaseio.com/").child("Users2").child(ReceiverUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phonenumber = dataSnapshot.child("phone").getValue().toString();
                profil = dataSnapshot.child("profil").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Name.setText(name);
                Picasso.get().load(profil).into(CircleImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(ReceiverUid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readMessages(SenderUser.getUid(),ReceiverUid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(phonenumber);
            }
        });
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = SendEdit.getText().toString();
                if(!msg.equals("")){
                    SendMessage(SenderUser.getUid(),ReceiverUid,msg);
                    DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Users2");
                    reference2.child(SenderUser.getUid()).child("People").child(ReceiverUid).child("id").setValue(ReceiverUid);
                    reference2.child(ReceiverUid).child("People").child(SenderUser.getUid()).child("id").setValue(SenderUser.getUid());
                    SendEdit.setText("");
                    return;
                }
                Toast.makeText(Message_2.this,"Boş mesaj yollaya bilmərsiniz!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void SendMessage(String sender, final String receiver, String message){
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
        reference2.child("Chats").push().setValue(hashMap);
        SetNumberMesssage(message);

        final String msg = message;

    }
    private void SetNumberMesssage(String message){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String myuid = user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(ReceiverUid).child("Message").child(myuid);

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("message",message);

        reference.push().setValue(hashMap);
    }
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    private void readMessages(final String myid, final String userid){
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Chat chat =snapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(myid)&&chat.getSender().equals(userid)||chat.getReceiver().equals(userid)&&chat.getSender().equals(myid)){
                        mChat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(Message_2.this,mChat);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
