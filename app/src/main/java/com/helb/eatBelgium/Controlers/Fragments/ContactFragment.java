package com.helb.eatBelgium.Controlers.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helb.eatBelgium.Common.Common;
import com.helb.eatBelgium.R;
import com.helb.eatBelgium.model.Contact;

public class ContactFragment extends Fragment {
    EditText contactMessage;
    Button btnSendMessage;

    public static ContactFragment newInstance() {
        return (new ContactFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendMassage(view);

    }
    public void sendMassage(View view){
        contactMessage = view.findViewById(R.id.edtMessage);
        btnSendMessage = view.findViewById(R.id.btnSendMessage);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_contact = database.getReference("Contacts");

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table_contact.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Contact contact = new Contact(Common.currentUser.getName(),contactMessage.getText().toString());
                        table_contact.child(Common.currentUser.getName()).setValue(contact);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



}
