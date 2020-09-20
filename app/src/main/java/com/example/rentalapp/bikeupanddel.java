package com.example.rentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class bikeupanddel extends AppCompatActivity {
    private ImageView bikeimageloder;
    private EditText rents, modals,kms,mil,ful;
    private Button up,del;
    private TextView carnames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikeupanddel);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        bikeimageloder = findViewById(R.id.bikeimageup);
        carnames = findViewById(R.id.carnaeup);
        rents = findViewById(R.id.carrentup);
        modals = findViewById(R.id.carmodal);
        up = findViewById(R.id.updatecars);
        del = findViewById(R.id.deletcars);
        kms = findViewById(R.id.kmdriven);
        mil = findViewById(R.id.milage);
        ful = findViewById(R.id.fuel);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");

        final String img=bundle.getString("imurl");
        final String carname =bundle.getString("name");
        final String rent=bundle.getString("rent");
        final String  modal = bundle.getString("modal");

        final  String ckms = bundle.getString("ckm");
        final  String cmil = bundle.getString("cmil");
        final  String cful = bundle.getString("cful");
        Glide.with(this)
                .load(img)
                .into(bikeimageloder);
        carnames.setText(carname);
        rents.setText(rent);
        modals.setText(modal);
        kms.setText(ckms);
        mil.setText(cmil);
        ful.setText(cful);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("bike").document(carname)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(bikeupanddel.this, "sussesfull", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {


                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(bikeupanddel.this, "unsussesfull", Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bikemodalclass upload = new bikemodalclass(carname,img,rents.getText().toString(),modals.getText().toString(),kms.getText().toString(),mil.getText().toString()
                        ,ful.getText().toString());

                db.collection("bike").document(carname)
                        .set(upload, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(bikeupanddel.this, "susses", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}