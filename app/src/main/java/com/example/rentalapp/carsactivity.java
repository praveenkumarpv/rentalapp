package com.example.rentalapp;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okio.Options;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link carsactivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carsactivity extends Fragment {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView carrecyclerview;
    private  FirestoreRecyclerAdapter adapter;
    private Context mcontext;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public carsactivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment carsactivity.
     */
    // TODO: Rename and change types and number of parameters
    public static carsactivity newInstance(String param1, String param2) {
        carsactivity fragment = new carsactivity();
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        View v= inflater.inflate(R.layout.fragment_carsactivity, container, false);
        carrecyclerview = v.findViewById(R.id.carrecycler);
        Query query = db.collection("cars");
        FirestoreRecyclerOptions<modalclass> op = new FirestoreRecyclerOptions.Builder<modalclass>().setQuery(query,modalclass.class).build();
       adapter = new FirestoreRecyclerAdapter<modalclass, itemsViewHolder>(op) {
            @NonNull
            @Override
            public itemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrecyclerview,parent,false);
                return new itemsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull itemsViewHolder itemsViewHolder, int i, @NonNull final modalclass modalclass) {
                itemsViewHolder.carname.setText(modalclass.getCarname());
                itemsViewHolder.price.setText(modalclass.getRen());
                Glide.with(getActivity()).asBitmap().load(modalclass.getImurl()).into(itemsViewHolder.im);

                itemsViewHolder.car.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(v.getContext(),Detailed.class);

                        String name = modalclass.getCarname();
                        String rent = modalclass.getRen();
                        String imurl = modalclass.getImurl();
                        Bundle bub = new Bundle();
                        bub.putString("name",name);
                        bub.putString("rent",rent);
                        bub.putString("imurl",imurl);
                        intent.putExtra("bundle",bub);
                        v.getContext().startActivity(intent);

                        //detailedview car = new detailedview();
                        //car.setArguments(bub);
                       //Navigation.findNavController(v).navigate(R.id.action_carsactivity_to_detailedview);

                    }
                });

            }
        };
       carrecyclerview.setHasFixedSize(true);
       carrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
       carrecyclerview.setAdapter(adapter);
        return v;
    }

    private class itemsViewHolder extends  RecyclerView.ViewHolder {
        private CircleImageView im;
        private TextView price,carname;
        private CardView car;
        public itemsViewHolder(@NonNull View itemView) {
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