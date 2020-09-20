package com.example.rentalapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bikeupanddelrecycler#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bikeupanddelrecycler extends Fragment {
    RecyclerView bikeupanddelrecycler; private FirestoreRecyclerAdapter adapters;
    private FirebaseFirestore firebaseFirestore;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public bikeupanddelrecycler() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bikeupanddelrecycler.
     */
    // TODO: Rename and change types and number of parameters
    public static bikeupanddelrecycler newInstance(String param1, String param2) {
        bikeupanddelrecycler fragment = new bikeupanddelrecycler();
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
        View view = inflater.inflate(R.layout.fragment_bikeupanddelrecycler, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        bikeupanddelrecycler = view.findViewById(R.id.bikeupdel);
        Query query = firebaseFirestore.collection("bike");
        FirestoreRecyclerOptions<bikemodalclass> options = new FirestoreRecyclerOptions.Builder<bikemodalclass>().setQuery(query,bikemodalclass.class).build();
        adapters = new FirestoreRecyclerAdapter<bikemodalclass, biupdelviewholder>(options) {
            @NonNull
            @Override
            public biupdelviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bikerecylerviw,parent,false);
                return new biupdelviewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull biupdelviewholder biupdelviewholder, int i, @NonNull final bikemodalclass bikemodalclass) {
                biupdelviewholder.bikenames.setText(bikemodalclass.getBikename());
                biupdelviewholder.rent.setText(bikemodalclass.getRent());
                Glide.with(getActivity()).asBitmap().load(bikemodalclass.getBimurl()).into(biupdelviewholder.bikeimage);
                biupdelviewholder.bikeselecter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(v.getContext(),bikeupanddel.class);
                        String name = bikemodalclass.getBikename();
                        String rent = bikemodalclass.getRent();
                        String imurl  = bikemodalclass.getBimurl();
                        String bikemodal  = bikemodalclass.getBikemodal();
                        String kmpb  = bikemodalclass.getBikekmdriven();
                        String mil  = bikemodalclass.getBmilage();
                        String ful  = bikemodalclass.getBfuel();
                        Bundle bub = new Bundle();
                        bub.putString("name",name);
                        bub.putString("rent",rent);
                        bub.putString("imurl",imurl);
                        bub.putString("modal",bikemodal);
                        bub.putString("ckm",kmpb);
                        bub.putString("cmil",mil);
                        bub.putString("cful",ful);
                        intent.putExtra("bundle",bub);

                        v.getContext().startActivity(intent);

                    }
                });
            }
        };
        bikeupanddelrecycler.setHasFixedSize(true);
        bikeupanddelrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        bikeupanddelrecycler.setAdapter(adapters);
        return  view;
    }

    private class biupdelviewholder  extends RecyclerView.ViewHolder {
        private CircleImageView bikeimage;
        private TextView bikenames;
        private  TextView rent;
        private CardView bikeselecter;
        public biupdelviewholder(@NonNull View itemView) {
            super(itemView);
            bikeimage = itemView.findViewById(R.id.bikeimage);
            bikenames = itemView.findViewById(R.id.bikename);
            rent = itemView.findViewById(R.id.biprize);
            bikeselecter = itemView.findViewById(R.id.bikecard);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        adapters.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapters.startListening();
    }
}