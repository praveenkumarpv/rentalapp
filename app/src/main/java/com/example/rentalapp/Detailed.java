package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Detailed extends AppCompatActivity {
TextView t1,t2,t3,t4,t5,t6;
ImageView imageView;
Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        t1=findViewById(R.id.cName);
        t2=findViewById(R.id.cRent);
        t3=findViewById(R.id.modal);
        t4 = findViewById(R.id.kmdriven);
        t5 = findViewById(R.id.milage);
        t6 = findViewById(R.id.fuel);
        imageView=findViewById(R.id.cImage);
        book = findViewById(R.id.book);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");

        String img=bundle.getString("imurl");
        String carname =bundle.getString("name");
        String rent=bundle.getString("rent");
        String  modal = bundle.getString("modal");
        String kmdriven = bundle.getString("km");
        String milage = bundle.getString("milage");
        String fuel = bundle.getString("fuel");


        Glide.with(this)
                .load(img)
                .into(imageView);
        t1.setText(carname);
        t2.setText(rent);
        t3.setText(modal);
        t4.setText(kmdriven);
        t5.setText(milage);
        t6.setText(fuel);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Detailed.this, "Booking Succesfull", Toast.LENGTH_SHORT).show();
            }
        });

    }
}