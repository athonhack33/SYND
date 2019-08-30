package com.example.synd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView i1,i2,i3,i4,i5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i1 = findViewById(R.id.markZone);
        i2 = findViewById(R.id.queueMgt);
        i3 = findViewById(R.id.autogen);
        i4 = findViewById(R.id.rewards);
        i5 = findViewById(R.id.SOS);

        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);
        i5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view == i1){
            //startActivity(new Intent(this, ));
        }

    }
}
