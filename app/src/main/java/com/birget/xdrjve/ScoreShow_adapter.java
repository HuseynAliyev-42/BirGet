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

import com.birget.xdrjve.ChangeScore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScoreShow_adapter  extends ArrayAdapter<ScoreShow_model> {
    private Activity context;
    private List<ScoreShow_model> artistList;
    public ScoreShow_adapter(Activity context, List<ScoreShow_model> artistList){
        super(context,R.layout.list1,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.list1, null, true);
        final ScoreShow_model  model = artistList.get(position);
        final CircleImageView CircleImage = (CircleImageView)listViewitem.findViewById(R.id.circleimage);
        final TextView Name = (TextView)listViewitem.findViewById(R.id.name);
        final TextView Score=(TextView)listViewitem.findViewById(R.id.score);
        final RelativeLayout Rel1 = (RelativeLayout)listViewitem.findViewById(R.id.relative1);
        String c = model.getId();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(c);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String scoretext=dataSnapshot.child("Ratings").child("total").getValue().toString();
                Score.setText(scoretext);
                String d = dataSnapshot.child("name").getValue().toString();
                Name.setText(d);
                String e = dataSnapshot.child("profil").getValue().toString();
                Picasso.get().load(e).into(CircleImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listViewitem;
    }
}
