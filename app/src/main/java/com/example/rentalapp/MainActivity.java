package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String uri;
    NavController navController;
    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        NavController navController = navHostFragment.getNavController();
    }

//    @Override
//    public void onBackPressed() {
//        count++;
//        if (count == 1){
//            Toast.makeText(this, "doublepress to exit", Toast.LENGTH_SHORT).show();
//            getSupportFragmentManager().popBackStack();
//
//        }
//        if (count == 2){
//            finish();
//        }


//    }

}
