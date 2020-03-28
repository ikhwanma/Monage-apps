package com.android.monagealpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    TextView viewPemasukan,viewSaldo,viewPengeluaran,userNama;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DatabaseReference mRootref;
    RecyclerView myLaporan;
    ArrayList<DataHistory> list;
    @Override
    public void onStart() {
        super.onStart();
        mRootref = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getEmail())
                .child("Detail");
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<DataLaporan>().setQuery(mRootref,DataLaporan.class).build();

        FirebaseRecyclerAdapter<DataLaporan, DataHolder> adapter = new FirebaseRecyclerAdapter<DataLaporan, DataHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final DataHolder holder, int position, @NonNull DataLaporan model) {
                final String userDate = getRef(position).getKey();
                mRootref.child(userDate).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String tanggal = String.valueOf(dataSnapshot.child("tanggal").getValue()) ;
                            String kategori = String.valueOf(dataSnapshot.child("kategori").getValue()) ;
                            String uang = String.valueOf(dataSnapshot.child("uang").getValue().toString());
                            String memo = String.valueOf(dataSnapshot.child("memo").getValue());
                            String title = String.valueOf(dataSnapshot.child("title").getValue());

                            holder.title.setText(title);
                            holder.viewMemo.setText(memo);
                            holder.viewKategori.setText(kategori);
                            holder.viewUang.setText(uang);
                            holder.viewTanggal.setText(tanggal);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_laporan,parent,false);
                DataHolder viewHolder = new DataHolder(view);
                return viewHolder;
            }
        };
        myLaporan.setAdapter(adapter);
        adapter.startListening();
    }
    public static class DataHolder extends RecyclerView.ViewHolder {
        public TextView viewUang,viewTanggal,viewKategori,viewMemo,title;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            viewUang = itemView.findViewById(R.id.viewUang);
            viewTanggal = itemView.findViewById(R.id.viewTanggal);
            viewMemo = itemView.findViewById(R.id.viewMemo);
            viewKategori = itemView.findViewById(R.id.viewKategori);
            title = itemView.findViewById(R.id.title);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPemasukan = findViewById(R.id.viewPemasukanhistory);
        viewSaldo = findViewById(R.id.viewSaldoHistory);
        viewPengeluaran = findViewById(R.id.viewPengeluaran);
        navigationView= (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        View headerView =  navigationView.getHeaderView(0);
        myLaporan = findViewById(R.id.myLaporan);
        myLaporan.setHasFixedSize(true);
        myLaporan.setLayoutManager(new LinearLayoutManager(this));
        userNama = headerView.findViewById(R.id.userNama);
        userNama.setText(Prevalent.currentOnlineUser.getNama());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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





        BottomNavigationView bottomnav = findViewById(R.id.bottom_nav);
        bottomnav.getMenu().findItem(R.id.input).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
                return true;
            }
            });
        bottomnav.getMenu().findItem(R.id.laporan).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
        });
        navigationView.setNavigationItemSelectedListener(navDraw);


        bottomnav.setOnNavigationItemSelectedListener(navListener);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public NavigationView.OnNavigationItemSelectedListener navDraw =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    if(id == R.id.nav_keluar){
                        Intent intent = new Intent(MainActivity.this,login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else if(id == R.id.nav_pengaturan){
                        Intent intent = new Intent(MainActivity.this,PengaturanActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    return true;
                }
            };

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectFragment = null;
                    switch (item.getItemId()) {


                        case R.id.history:
                            selectFragment = new history();
                            break;


                    }

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, selectFragment)
                            .commit();
                    return true;
                }

            };



}


