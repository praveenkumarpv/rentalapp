package com.example.rentalapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link carupanddelreview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carupanddelreview extends Fragment {
    private ImageView ba;
    private RecyclerView carupanddelre;
    private FirestoreRecyclerAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public carupanddelreview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment carupanddelreview.
     */
    // TODO: Rename and change types and number of parameters
    public static carupanddelreview newInstance(String param1, String param2) {
        carupanddelreview fragment = new carupanddelreview();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_carupanddelreview, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        carupanddelre = view.findViewById(R.id.carrecyclerinupdates);
        Query query = db.collection("cars");
        FirestoreRecyclerOptions<modalclass> op = new FirestoreRecyclerOptions.Builder<modalclass>().setQuery(query,modalclass.class).build();
        adapter = new FirestoreRecyclerAdapter<modalclass, caritemviewholder>(op) {
            @NonNull
            @Override
            public caritemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrecyclerview,parent,false);
                return new caritemviewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull caritemviewholder caritemviewholder, int i, @NonNull final modalclass modalclass) {
                caritemviewholder.carname.setText(modalclass.getCarname());
                caritemviewholder.price.setText(modalclass.getRen());
                Glide.with(getActivity()).asBitmap().load(modalclass.getImurl()).into(caritemviewholder.im);
                caritemviewholder.car.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(v.getContext(),updateanddelet.class);
                        String name = modalclass.getCarname();
                        String rent = modalclass.getRen();
                        String imurl = modalclass.getImurl();
                        String carmodal =modalclass.getCarmodal();
                        String downloadurl = modalclass.getImurl();
                        String kmps = modalclass.getCarkm();
                        String mila = modalclass.getCarmilage();
                        String fule = modalclass.getCarfuel();
                        Bundle bub = new Bundle();
                        bub.putString("name",name);
                        bub.putString("rent",rent);
                        bub.putString("imurl",imurl);
                        bub.putString("modal",carmodal);
                        bub.putString("url",downloadurl);
                        bub.putString("ckm",kmps);
                        bub.putString("cmil",mila);
                        bub.putString("cful",fule);
                        intent.putExtra("bundle",bub);

                        v.getContext().startActivity(intent);
                    }
                });
            }
        };
        carupanddelre.setHasFixedSize(true);
        carupanddelre.setLayoutManager(new LinearLayoutManager(getActivity()));
        carupanddelre.setAdapter(adapter);
        return  view;
    }

    private class caritemviewholder  extends  RecyclerView.ViewHolder {
        private CircleImageView im;
        private TextView price,carname;
        private CardView car;

        public caritemviewholder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.carimage);
            carname = itemView.findViewById(R.id.carname);
            price = itemView.findViewById(R.id.prize);
            car = itemView.findViewById(R.id.carcard);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
    }
}