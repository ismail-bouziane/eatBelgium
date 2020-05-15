package com.helb.eatBelgium.Controlers.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.helb.eatBelgium.R;
import com.helb.eatBelgium.Views.CategoriesAdapter;
import com.helb.eatBelgium.model.Category;

import java.util.List;
import java.util.Objects;


public class PlatsFragment extends Fragment {

    private List<Category> listCategory;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    private static String  itemID;

    TextView txtProduct;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    public static PlatsFragment newInstance() {
        return (new PlatsFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_plats, container, false);
        RelativeLayout ll = (RelativeLayout) inflater.inflate(R.layout.fragment_plats, container, false);
        RecyclerView recyclerView = (RecyclerView) ll.findViewById(R.id.list_categories);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return  ll;

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initialize your view here for use view.findViewById("your view id")
        recyclerView = view.findViewById(R.id.list_categories);

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fetch();
    }


    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("categories");
        FirebaseRecyclerOptions<Category> options =
                new FirebaseRecyclerOptions.Builder<Category>()
                        .setQuery(query, new SnapshotParser<Category>() {
                            @NonNull
                            @Override

                            public Category parseSnapshot(@NonNull DataSnapshot snapshot) {
                                System.out.println(snapshot.getChildren());
                                itemID = snapshot.getKey().toString();
                                return new Category(snapshot.getKey(),
                                        snapshot.child("nomCat").getValue().toString());
                            }
                        })
                        .build();
        adapter = new FirebaseRecyclerAdapter<Category, ViewHolder>(options) {

            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_plats, parent, false);

                final ViewHolder viewHolder = new ViewHolder(view);

                viewHolder.txtTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      //  Toast.makeText(view.getContext(), "TEST CLICK", Toast.LENGTH_SHORT).show(); // ici
                        //    System.out.println(category);


                        txtProduct = view.findViewById(R.id.list_Products);
                        Bundle bundle = new Bundle();
                        bundle.putString("recID",itemID);

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        ProductsFragments productsFragments = new ProductsFragments();
                        productsFragments.setArguments(bundle);

                        fragmentTransaction.replace(R.id.fragment_plat_layout,productsFragments);
                        fragmentTransaction.commit();
                       // itemID = "";
                    }
                });
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i, @NonNull Category category) {
                viewHolder.setTxtTitle(category.getNomCategory());
                Log.d("DEBUG---------------------------------",category.getNomCategory());
                viewHolder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show(); // ici
                    //    System.out.println(category);
                        Log.d("DEBUG---------------------------------",category.getNomCategory());

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
        public RecyclerView root;
        public TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = (RecyclerView)itemView.findViewById(R.id.list_categories);
            txtTitle= (TextView)itemView.findViewById(R.id.list_title);
        }
        //    public TextView txtTitle;
        //  public TextView txtDesc;
         public void setTxtTitle(String string) {
              txtTitle.setText(string);
            }
    }


}


