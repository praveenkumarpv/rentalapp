package com.example.rentalapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.prefs.Preferences;

import static com.example.rentalapp.BlankFragment.Preference;


public class mainscreen extends Fragment {
    View v;
    Button login;
    TextView registerlo;
    public  static  final  String preference = "shared";


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public mainscreen() {
        // Required empty public constructor
    }


    public static mainscreen newInstance(String param1, String param2) {
        mainscreen fragment = new mainscreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mainscreen,container,false);
        login = v.findViewById(R.id.log);
        registerlo = v.findViewById(R.id.reg);
        try {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            if (uid != null){
                Navigation.findNavController(v).navigate(R.id.action_mainscreen_to_activitymainscreen);
                Toast.makeText(getActivity(), uid, Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_mainscreen_to_blankFragment);
                }
            });
            registerlo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_mainscreen_to_registration);
                }
            });
        }
        SharedPreferences settins = getActivity().getSharedPreferences(Preference,0);
        boolean haslogin = settins.getBoolean("haslogin",false);
        if (haslogin){
            Toast.makeText(getActivity(), "suss", Toast.LENGTH_SHORT).show();
//            Navigation.findNavController(v).navigate(R.id.action_mainscreen_to_activitymainscreen);
            Navigation.findNavController(v).navigate(R.id.action_mainscreen_to_registration);
        }

       return v;
    }
}