package com.android.monagealpha;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KategoriPengluaranActivity extends AppCompatActivity {

    private Button btnMakanan,btnTransportasi,btnPendidikan,btnBuku,btnElektronik,btnBack;
    private Button btnKesehatan,btnTelepon,btnBelanja,btnHiburan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_pengluaran);
        btnMakanan = findViewById(R.id.btnMakanan);
        btnTransportasi = findViewById(R.id.btnTransportasi);
        btnPendidikan = findViewById(R.id.btnPendidikan);
        btnBuku = findViewById(R.id.btnBuku);
        btnElektronik = findViewById(R.id.btnElektronik);
        btnKesehatan = findViewById(R.id.btnKesehatan);
        btnTelepon = findViewById(R.id.btnTelepon);
        btnBelanja = findViewById(R.id.btnBelanja);
        btnHiburan = findViewById(R.id.btnHiburan);
        final DatabaseReference Rootref = FirebaseDatabase.getInstance().getReference();

        final Intent intent = getIntent();



        btnBack= findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnTransportasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Transportasi");
                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnPendidikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Pendidikan");
                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Buku");
                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnElektronik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("ElekTronik");
                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Kesehatan");
                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnTelepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Telepon");
                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnBelanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Belanja");
                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnHiburan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prevalent.currentOnlineUser.setKategori("Hiburan");
                Intent intent = new Intent(KategoriPengluaranActivity.this,InputActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
