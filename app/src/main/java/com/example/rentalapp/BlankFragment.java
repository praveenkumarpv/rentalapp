package com.example.rentalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    EditText emilid,passwords;
    Button log;
    TextView forget,regiser;
    private FirebaseAuth mAuth;
    private String adminlog = "adminlog@gmail.com";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
        mAuth = FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_blank,container,false);
        emilid = v.findViewById(R.id.emid);
        passwords = v.findViewById(R.id.password);
        log = v.findViewById(R.id.siup);
        forget = v.findViewById(R.id.textView9);
        regiser = v.findViewById(R.id.sir);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String emaili = emilid.getText().toString();
                String passi = passwords.getText().toString();
                if (emaili.isEmpty()){
                    emilid.setError("Please enter the email");
                    emilid.requestFocus();
                }
                else if (passi.isEmpty()){
                    passwords.setError("Please enter the password");
                    passwords.requestFocus();
                }
                else if (emaili.isEmpty() && passi.isEmpty()){
                    Toast.makeText(getActivity(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if (emaili.equals(adminlog)){
                    mAuth.signInWithEmailAndPassword(emaili,passi).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getActivity(), "Successfull", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(v).navigate(R.id.action_blankFragment_to_addminselection);
                            }
                            else {
                                Toast.makeText(getActivity(), "Unsuccessfull", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
                else  if (!(emaili.isEmpty() && passi.isEmpty())){
                   mAuth.signInWithEmailAndPassword(emaili,passi).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(getActivity(), "Successfull Login", Toast.LENGTH_SHORT).show();
                               Navigation.findNavController(v).navigate(R.id.action_blankFragment_to_activitymainscreen);
                           }
                           else {
                               Toast.makeText(getActivity(), "Login Fialed", Toast.LENGTH_SHORT).show();

                           }

                       }
                   });
                }

            }
        });
        regiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_blankFragment_to_registration);
            }
        });
        return v;

    }
}