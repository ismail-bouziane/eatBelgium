package com.helb.eatBelgium.Controlers.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helb.eatBelgium.Common.Common;
import com.helb.eatBelgium.R;
import com.helb.eatBelgium.model.Panier;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PanierFragment extends Fragment  {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    DatabaseReference databaseReference;
    Button btnCommander;
    private static long maxId=0;



    public static PanierFragment newInstance() {
        return (new PanierFragment());
    }

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_panier, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        listView=(ListView)view.findViewById(R.id.list_panier);
        adapter= new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1, android.R.id.text1,Common.listCommandes); //simple_list_item_1 R.


        btnCommander=(Button)view.findViewById(R.id.btnCommander);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_panier = database.getReference().child("Commandes");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),"Vous avez cliqué a l'item : "+ adapter.getItem(position), Toast.LENGTH_SHORT).show();
                final int wich_item = position;
                new AlertDialog.Builder(view.getContext())
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Etes vous sure ?")
                        .setMessage("Voulez vous vraiment retirer ce produit de votre panier")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Common.listCommandes.remove(wich_item);
                                //  positionchecker.clear();
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Non",null)
                        .show();


            }
        });

        
        listView.setAdapter(adapter);

        btnCommander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                Date date = new Date();
                        Panier panier = new Panier(Common.listCommandes,Common.currentUser.getName(),format.format(date));

                        table_panier.push().setValue(panier);
                        Common.listCommandes.clear();
                    }
        });

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
