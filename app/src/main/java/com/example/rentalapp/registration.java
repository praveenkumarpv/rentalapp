package com.example.rentalapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.example.rentalapp.BlankFragment.Preference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link registration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class registration extends Fragment {
    EditText names,emailid,password,dob,licenceno,mobnum;
    ImageView back;
    Button reg;
    TextView login;
    private FirebaseAuth mAuth;
    String uid;
    FirebaseFirestore db ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public registration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment registration.
     */
    // TODO: Rename and change types and number of parameters
    public static registration newInstance(String param1, String param2) {
        registration fragment = new registration();
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
       View v = inflater.inflate(R.layout.fragment_registration,container,false);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        names = v.findViewById(R.id.e1);
       emailid = v.findViewById(R.id.e2);
       mobnum = v.findViewById(R.id.e3);
       password = v.findViewById(R.id.e4);
       dob = v.findViewById(R.id.date);
       licenceno = v.findViewById(R.id.licno);
       reg = v.findViewById(R.id.ri);
       login = v.findViewById(R.id.lo);
       reg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(final View v) {
               final String name,email,mobile,passwords,dobs,lice;
               name = names.getText().toString();
               email = emailid.getText().toString();
               mobile = mobnum.getText().toString();
               passwords = password.getText().toString();
               dobs = dob.getText().toString();
               lice = licenceno.getText().toString();
               if (name.isEmpty()){
                   names.setError("Field is empty");
                   names.requestFocus();
               }
               else if (email.isEmpty()){
                   emailid.setError("Field is empty");
                   emailid.requestFocus();
               }
               else if (mobile.isEmpty()){
                   mobnum.setError("Field is empty");
                   mobnum.requestFocus();
               }
               else if (passwords.isEmpty()){
                   password.setError("Field is empty");
                   password.requestFocus();
               }
               else if (dobs.isEmpty()){
                   dob.setError("Field is empty");
                   dob.requestFocus();
               } else if (lice.isEmpty()){
                   licenceno.setError("Field is empty");
                   licenceno.requestFocus();
               }
               else if (!(name.isEmpty() && email.isEmpty() && mobile.isEmpty() && passwords.isEmpty() && dobs.isEmpty() && lice.isEmpty() )){
                   mAuth.createUserWithEmailAndPassword(email,passwords).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()){
                              uid = mAuth.getInstance().getCurrentUser().getUid();
                              DocumentReference documentReference = db.collection("userres").document(uid);
//                              Toast.makeText(getActivity(), "Wellcome", Toast.LENGTH_SHORT).show();
                              Map<String, Object> user = new HashMap<>();

                              user.put("name",name);
                              user.put("email",email);
                              user.put("mob",mobile);
                              user.put("dob",dobs);
                              user.put("licenceno",lice);
                             documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void aVoid) {
                                     Navigation.findNavController(v).navigate(R.id.action_registration_to_blankFragment);
//                                     Navigation.findNavController(v).navigate(R.id.action_blankFragment_to_activitymainscreen);
                                     SharedPreferences settings = getActivity().getSharedPreferences(Preference,0);
                                     SharedPreferences.Editor editor= settings.edit();
                                     editor.putBoolean("haslogin",true);
                                     editor.commit();
                                 }
                             });


                          }
                          else {
                              Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                          }
                       }
                   });
               }

           }
       });
       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.action_registration_to_blankFragment);
           }
       });

       return v;
    }
}