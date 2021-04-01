package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Error_1 extends AppCompatActivity {
    private ImageView Image1;
    private TextView Text1;
    private Button Button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_1);
        Button1 = (Button)findViewById(R.id.btn1);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Error_1.this, Activity_3.class);
                startActivity(i);
            }
        });
    }
}
