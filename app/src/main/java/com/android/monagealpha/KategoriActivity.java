package com.android.monagealpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KategoriActivity extends AppCompatActivity  {

    Button btnMakanan;
    Kategori kategori;
    InputActivity inputActivity;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
//        btnMakanan = findViewById(R.id.btnMakanan);
//        setMakanan();

        //Tablayout
        tabLayout = (TabLayout)findViewById(R.id.tab_kategori);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);

        //Menambah Fragment
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new KategoriPemasukan(),"Pemasukan");
        viewPageAdapter.addFragment(new KategoriPengeluaran(),"Pengeluaran");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }

    public void setMakanan(){
        btnMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KategoriActivity.this,InputActivity.class);
                startActivity(intent);
            }
        });
    }
}
