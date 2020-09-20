package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String uri;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent=getIntent();
//        Bundle bundle=intent.getBundleExtra("bundle");
//        uri =bundle.getString("uri");
//        Toast.makeText(this,uri, Toast.LENGTH_SHORT).show();
//        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager()
//                .findFragmentById(R.id.fragment);
//        navController = navHostFragment.getNavController();
//        NavigationUI.setupWithNavController(getActionBar(),navController);navController
    }
}