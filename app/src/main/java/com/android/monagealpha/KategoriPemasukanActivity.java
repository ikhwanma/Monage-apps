package com.android.monagealpha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KategoriPemasukanActivity extends AppCompatActivity  {

    private Button btnPengembalian,btnLainnya,btnInvestasi,btnPenjualan,btnGaji,btnBack;

    InputActivity inputActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_pemasukan);
        btnGaji = findViewById(R.id.btnGaji);
        btnPenjualan = findViewById(R.id.btnPenjualan);
        btnInvestasi = findViewById(R.id.btnInvestasi);
        btnLainnya = findViewById(R.id.btnLainnya);
        btnPengembalian = findViewById(R.id.btnPengembalian);
        btnBack= findViewById(R.id.btnBack);
        final DatabaseReference Rootref = FirebaseDatabase.getInstance().getReference();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KategoriPemasukanActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnGaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Gaji");
                Intent intent = new Intent(KategoriPemasukanActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Penjualan");
                Intent intent = new Intent(KategoriPemasukanActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnInvestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Investasi");
                Intent intent = new Intent(KategoriPemasukanActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Lainnya");
                Intent intent = new Intent(KategoriPemasukanActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnPengembalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Pengembalian");
                Intent intent = new Intent(KategoriPemasukanActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



}
