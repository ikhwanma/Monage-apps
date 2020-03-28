package com.android.monagealpha;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class laporan extends Fragment {


    TextView viewPemasukan,viewSaldo,viewPengeluaran;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laporan, container, false);
        viewPemasukan = view.findViewById(R.id.viewPemasukanhistory);
        viewSaldo = view.findViewById(R.id.viewSaldoHistory);

        viewPengeluaran = view.findViewById(R.id.viewPengeluaran);
        final DatabaseReference Rootref = FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users userData = dataSnapshot.child("Users").child(Prevalent.currentOnlineUser.getEmail()).getValue(Users.class);
                Prevalent.currentOnlineUser = userData;
                viewPemasukan.setText(String.valueOf(Prevalent.currentOnlineUser.getPemasukan()));
                viewPengeluaran.setText(String.valueOf(Prevalent.currentOnlineUser.getPengeluaran()));
                viewSaldo.setText(String.valueOf(Prevalent.currentOnlineUser.getSaldo()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
