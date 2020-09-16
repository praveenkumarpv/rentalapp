package com.example.rentalapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bikeupload#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bikeupload extends Fragment {
    ImageView bikeimageview;
    EditText bikename,bikerent;
    Button uplodbutton;
    private Uri filepath;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public bikeupload() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bikeupload.
     */
    // TODO: Rename and change types and number of parameters
    public static bikeupload newInstance(String param1, String param2) {
        bikeupload fragment = new bikeupload();
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

        View view= inflater.inflate(R.layout.fragment_bikeupload, container, false);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        bikeimageview = view.findViewById(R.id.bikeimageup);
        bikename = view.findViewById(R.id.bikenaeup);
        bikerent = view.findViewById(R.id.bikerentup);
        uplodbutton = view.findViewById(R.id.uploadbike);
        bikeimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uloadimage();
            }
        });
       uplodbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               uploadtofirebase();
           }
       });
        return view;
    }

    private void uploadtofirebase() {
        if (filepath != null){
            StorageReference reference = storageReference.child("image/"+ UUID.randomUUID().toString());
            reference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(), "Sussesfull", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }


    private void uloadimage() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"selectimage"),1);
        }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && requestCode == RESULT_OK && data!=null && data.getData()!=null){
            filepath =data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),filepath);
                bikeimageview.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    }
