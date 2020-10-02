package com.example.rentalapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bikeupload#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bikeupload extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView bikeimageview;
    EditText bikename, bikerent,bikemodel,kms,mil,ful;
    Button uplodbutton;
    private Uri filepath;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseFirestore db;
    Uri downloadUrl;


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

        View view = inflater.inflate(R.layout.fragment_bikeupload, container, false);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        db = FirebaseFirestore.getInstance();
        bikeimageview = view.findViewById(R.id.bikeimageup);
        bikename = view.findViewById(R.id.bikenaeup);
        bikemodel = view.findViewById(R.id.bikemodel);
        bikerent = view.findViewById(R.id.bikerentup);
        uplodbutton = view.findViewById(R.id.uploadbike);
        kms = view.findViewById(R.id.kmdriven);
        mil = view.findViewById(R.id.milage);
//        ful = view.findViewById(R.id.fuel);
        bikeimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uloadimage();
            }
        });
        uplodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uploadfile();
            }
        });
        return view;
    }




    private void uloadimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode ==
                RESULT_OK
                && data != null && data.getData() != null) {
            filepath = data.getData();
            Glide.with(this)
                    .load(filepath)
                    .into(bikeimageview);

            final StorageReference riversRef = storageReference.child(System.currentTimeMillis()+ "." + getFileExtension(filepath));

            riversRef.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            }, 500);

                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                     downloadUrl = uri;
                                    bikemodalclass upload = new bikemodalclass(bikename.getText().toString().trim(),
                                            downloadUrl.toString(),
                                            bikerent.getText().toString(),
                                            bikemodel.getText().toString().trim(),kms.getText().toString(),mil.getText().toString(),null);

                                    db.collection("bike").document(bikename.getText().toString().trim())
                                            .set(upload)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getActivity(), "Uploaded successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error writing document", e);
                                                }
                                            });

                            }
                        });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads


                        }
                    });
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = requireActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void Uploadfile() {
//        Toast.makeText(getActivity(), "Processing.....", Toast.LENGTH_SHORT).show();
    }
    }

