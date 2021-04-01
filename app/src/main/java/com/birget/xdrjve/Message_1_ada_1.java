package com.birget.xdrjve;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message_1_ada_1 extends ArrayAdapter<com.birget.xdrjve.Message_1_adapter> {
    private Activity context;
    private List<com.birget.xdrjve.Message_1_adapter> artistList;
    public Message_1_ada_1(Activity context, List<com.birget.xdrjve.Message_1_adapter> artistList){
        super(context,R.layout.list_layout_3,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.list_layout_3, null, true);
        final Message_1_adapter model = artistList.get(position);
        final CircleImageView CircleImage = (CircleImageView)listViewitem.findViewById(R.id.circleimage);
        final TextView Name = (TextView)listViewitem.findViewById(R.id.name);
        final RelativeLayout Rel1 = (RelativeLayout)listViewitem.findViewById(R.id.relative1);
        final TextView Notification = (TextView)listViewitem.findViewById(R.id.notification);
        final String c = model.getId();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(c);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String d = dataSnapshot.child("name").getValue().toString();
                Name.setText(d);
                String e = dataSnapshot.child("profil").getValue().toString();
                Picasso.get().load(e).into(CircleImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("Message");
        Rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(c).removeValue();
                FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                String MyUid = user1.getUid();
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Intent i =new Intent(context.getApplicationContext(), Message_2.class);
                        i.putExtra("uid",model.getId());
                        context.startActivity(i);
                        context.finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        DatabaseReference reference12 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("Message");
        reference12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(c)){
                    int a =(int)dataSnapshot.child(c).getChildrenCount();
                    String b = String.valueOf(a);
                    String d = b+" "+"ədəd mesaj var";
                    Notification.setText(d);
                }else {
                    Notification.setText("Hal-hazırda mesaj yoxdur!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listViewitem;
    }
}


