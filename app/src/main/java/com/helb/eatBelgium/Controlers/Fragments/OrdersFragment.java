package com.helb.eatBelgium.Controlers.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import com.helb.eatBelgium.model.Orders;

import java.util.List;

public class OrdersFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    public static OrdersFragment newInstance() {
        return (new OrdersFragment());
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout ll = (RelativeLayout) inflater.inflate(R.layout.fragment_orders, container, false);
        RecyclerView recyclerView = (RecyclerView) ll.findViewById(R.id.list_orders);
        return ll;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initialize your view here for use view.findViewById("your view id")
        recyclerView = view.findViewById(R.id.list_orders);

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fetchCommandes();
    }

    private void fetchCommandes() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Commandes").orderByChild("currentUser").equalTo(Common.currentUser.getName());
        FirebaseRecyclerOptions<Orders> options =
                new FirebaseRecyclerOptions.Builder<Orders>()
                        .setQuery(query, new SnapshotParser<Orders>() {
                            @NonNull
                            @Override
                            public Orders parseSnapshot(@NonNull DataSnapshot snapshot) {

                                return new Orders(snapshot.child("currentUser").getValue().toString(),
                                        snapshot.child("date").getValue().toString(),
                                        (List<String>) snapshot.child("listPanier").getValue()
                                       );
                            }


                        })
        .build();
        adapter = new FirebaseRecyclerAdapter<Orders, ViewHolder>(options) {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_orders, parent, false);

                final ViewHolder viewHolder = new ViewHolder(view);
                viewHolder.txtOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Orders orders) {
                    viewHolder.setTxtOrder(orders.getDate());

                    viewHolder.txtOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(view.getContext(), "Voici la commande du " + orders.getDate(), Toast.LENGTH_SHORT).show();
                        }
                    });

            }
        };
        recyclerView.setAdapter(adapter);
    }




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        public RecyclerView rootOrders;
        public TextView txtOrder;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootOrders = (RecyclerView) itemView.findViewById(R.id.list_orders);
            txtOrder = (TextView) itemView.findViewById(R.id.list_title_commandes);
        }

        public void setTxtOrder(String date) {
            txtOrder.setText("Commande du " + date);
        }
    }

}
