package com.android.monagealpha;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class history extends Fragment{
    DatabaseReference Rootref;
    View historyView;
    RecyclerView myHistory;
    ArrayList<DataHistory> list;
    TextView ada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        historyView = inflater.inflate(R.layout.fragment_history, container, false);
        myHistory =historyView.findViewById(R.id.recyclerview_history);
        myHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        Rootref = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getEmail())
                .child("History");
        ada = historyView.findViewById(R.id.viewAda);
        return  historyView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<DataHistory>().setQuery(Rootref,DataHistory.class).build();

        FirebaseRecyclerAdapter<DataHistory,DataViewHolder> adapter = new FirebaseRecyclerAdapter<DataHistory, DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final DataViewHolder holder, int position, @NonNull DataHistory model) {
                final String userDate = getRef(position).getKey();
                Rootref.child(userDate).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(userDate.isEmpty()){
                            ada.setText("Tidak ada History");
                        }else{
                            String pemasukan = String.valueOf(dataSnapshot.child("pemasukan").getValue()) ;
                            String pengeluaran = String.valueOf(dataSnapshot.child("pengeluaran").getValue()) ;
                            String saldo = String.valueOf(dataSnapshot.child("saldo").getValue());
                            String tanggal = userDate;

                            holder.pengeluaran.setText(pengeluaran);
                            holder.pemasukan.setText(pemasukan);
                            holder.saldo.setText(saldo);
                            holder.tanggal.setText(tanggal);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
                DataViewHolder viewHolder = new DataViewHolder(view);
                return viewHolder;
            }
        };
        myHistory.setAdapter(adapter);
        adapter.startListening();
    }

    public static class DataViewHolder extends  RecyclerView.ViewHolder{
        TextView pemasukan,pengeluaran,saldo,tanggal;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            pemasukan = itemView.findViewById(R.id.viewPemasukanhistory);
            pengeluaran = itemView.findViewById(R.id.viewPengeluranHistory);
            saldo = itemView.findViewById(R.id.viewSaldoHistory);
            tanggal = itemView.findViewById(R.id.viewTanggalHistory);

        }
    }

}
