package com.helb.eatBelgium.Controlers.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.helb.eatBelgium.Common.Common;
import com.helb.eatBelgium.R;
import com.helb.eatBelgium.model.Category;
import com.helb.eatBelgium.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragments extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    String productID;
    public TextView txtProduct;
    private static String recCatIDId;
    private static String nomPlat;
    private static int prixPlat;



    public ProductsFragments() {
        // Required empty public constructor
    }

    public static ProductsFragments newInstance() {
        ProductsFragments fragment = new ProductsFragments();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_products_fragments, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_products);

        Bundle bundle = getArguments();

        recCatIDId = bundle.getString("recID");

        return  rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initialize your view here for use view.findViewById("your view id")

        recyclerView = view.findViewById(R.id.recycler_products);

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getAllPlats();
    }

    private void getAllPlats(){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("products").orderByChild("idCat").equalTo(recCatIDId);
        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(query, new SnapshotParser<Product>() {
                            @NonNull
                            @Override

                            public Product parseSnapshot(@NonNull DataSnapshot snapshot) {
                                String catiD;
                                catiD = snapshot.child("idCat").getValue().toString();
                                nomPlat = snapshot.child("nomProduit").getValue().toString();
                                prixPlat = Integer.parseInt(snapshot.child("price").getValue().toString());
                                    return new Product(snapshot.getKey(),
                                            snapshot.child("nomProduit").getValue().toString(),
                                            snapshot.child("idCat").getValue().toString(),
                                            Integer.parseInt(snapshot.child("price").getValue().toString()),snapshot.child("image").getValue().toString() );


                            }

                        })
                        .build();
        adapter = new FirebaseRecyclerAdapter<Product, ViewHolder>(options) {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_products_fragments, parent, false);

                final ViewHolder viewHolder = new ViewHolder(view);
                viewHolder.txtnomPlat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return new ViewHolder(view);
            }


            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Product product) {
                viewHolder.setTxtnomPlat(product.getNameProduct(),product.getPrice());
                viewHolder.setImage(product.getImage());

                // Log.d("DEBUG---------------------------------",category.getNomCategory());
                viewHolder.txtnomPlat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(view.getContext(), "Ajoutée au panier", Toast.LENGTH_SHORT).show();
                      ///  Common.PrixTotal = Common.PrixTotal+product.getPrice();
                        Common.listCommandes.add(nomPlat);
                        Common.listPrix.add(prixPlat);
                    }
                });
                viewHolder.imagePlat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "Ajoutée au panier", Toast.LENGTH_SHORT).show();
                        ///  Common.PrixTotal = Common.PrixTotal+product.getPrice();
                        Common.listCommandes.add(nomPlat);
                        Common.listPrix.add(prixPlat);
                    }
                });
            }
        };
                recyclerView.setAdapter(adapter);
    }

            @Override
            public void onStart() {
                super.onStart();
                adapter.startListening();
            }

            @Override
            public void onStop() {
                super.onStop();
                adapter.stopListening();
            }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView rootPlats;
        public TextView txtnomPlat;
        public TextView pricePlat;
        public ImageView imagePlat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootPlats=(RecyclerView)itemView.findViewById(R.id.recycler_products);
            txtnomPlat=(TextView)itemView.findViewById(R.id.list_Products);
            imagePlat=(ImageView)itemView.findViewById((R.id.imageProduct));
        }
        public void setTxtnomPlat(String string, int price) {
            txtnomPlat.setText(string +" "+price+"€");
        }
        public void setImage(String image){
            Picasso.get().load(image).into(imagePlat);
        }
    }


}

