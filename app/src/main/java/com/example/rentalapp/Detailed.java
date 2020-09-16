package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detailed extends AppCompatActivity {
TextView t1,t2,t3;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        t1=findViewById(R.id.cName);
        t2=findViewById(R.id.cRent);
        t3=findViewById(R.id.modal);
        imageView=findViewById(R.id.cImage);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");

        String img=bundle.getString("imurl");
        String carname =bundle.getString("name");
        String rent=bundle.getString("rent");
        String  modal = bundle.getString("modal");


        Glide.with(this)
                .load(img)
                .into(imageView);
        t1.setText(carname);
        t2.setText(rent);
        t3.setText(modal);

    }
}