package com.android.monagealpha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class InputActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button btnAdd,btnBack,btnKategori,btnDate;
    String jenis;
    TextView viewKategori,viewDate,textTitle,viewIDR;
    ArrayList<String> jumlah;
    SwitchCompat switchbuttonin;
    int pemasukanHarian,pengeluaranHarian,saldoHarian;
    String date="",Kategori = "";
    EditText inputUang,inputCatatan;
    int pemasukan=0,pengeluaran=0,saldo=Prevalent.currentOnlineUser.getSaldo();
    int REQ_CODE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        jumlah = new ArrayList<>();
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        btnKategori = findViewById(R.id.inputKategori);
        viewDate = findViewById(R.id.viewDate);
        btnDate = findViewById(R.id.btnDate);
        textTitle = findViewById(R.id.textTitle);
        inputUang = findViewById(R.id.inputUang);
        inputCatatan = findViewById(R.id.inputCatatan);
        viewIDR = findViewById(R.id.viewIDR);
        viewKategori = findViewById(R.id.viewKategori);
        viewKategori.setText(Prevalent.currentOnlineUser.getKategori());
        openKategori();
        back();
        Kategori = Prevalent.currentOnlineUser.getKategori();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addData();

            }
        });
        viewKategori.setText(Kategori);
                switchbuttonin= findViewById(R.id.switchbutton);
        switchbuttonin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchbuttonin.isChecked()){
                    textTitle.setText("Pemasukan");
                    inputUang.setTextColor(Color.parseColor("#FDA300"));
                    viewIDR.setTextColor(Color.parseColor("#FDA300"));
                }else{
                    textTitle.setText("Pengeluaran");
                    inputUang.setTextColor(Color.parseColor("#D55252"));
                    viewIDR.setTextColor(Color.parseColor("#D55252"));
                }
            }
        });
        openDate();
    }

    private void openDate(){
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = "";
                showDatePicker();
            }
        });
    }
    private void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        date += dayOfMonth+"-"+(month+1)+"-"+year;
        viewDate.setText(date);

    }
    public  void addData(){
        if(date.equals("") && inputUang.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan Tanggal Dulu", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Masukkan Nominal Uang", Toast.LENGTH_SHORT).show();
        }else{
            final int jumlahUang = Integer.parseInt(inputUang.getText().toString());
            final DatabaseReference Rootref = FirebaseDatabase.getInstance().getReference();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
            String currentDateandTime = sdf.format(new Date());
            Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Users userData = dataSnapshot.child("Users").child(Prevalent.currentOnlineUser.getEmail()).getValue(Users.class);
                    Prevalent.currentOnlineUser = userData;
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child("Users").child(Prevalent.currentOnlineUser.getEmail())
                            .child("History").child(date).exists()){
                        HashMap<String,Object> user = new HashMap<>();
                        if(switchbuttonin.isChecked()){
                            pemasukanHarian = Integer.parseInt(dataSnapshot.child("Users").child(Prevalent.currentOnlineUser.getEmail())
                                    .child("History").child(date).child("pemasukan").getValue().toString()) + jumlahUang;
                            pengeluaranHarian = Integer.parseInt(dataSnapshot.child("Users").child(Prevalent.currentOnlineUser.getEmail())
                                    .child("History").child(date).child("pengeluaran").getValue().toString());
                        }
                        else{
                            pemasukanHarian = Integer.parseInt(dataSnapshot.child("Users").child(Prevalent.currentOnlineUser.getEmail())
                                    .child("History").child(date).child("pemasukan").getValue().toString());
                            pengeluaranHarian = Integer.parseInt(dataSnapshot.child("Users").child(Prevalent.currentOnlineUser.getEmail())
                                    .child("History").child(date).child("pengeluaran").getValue().toString()) + jumlahUang;
                        }
                        user.put("pemasukan",pemasukanHarian);
                        user.put("pengeluaran",pengeluaranHarian);
                        user.put("saldo",saldo);
                        Rootref.child("Users").child(Prevalent.currentOnlineUser.getEmail())
                                .child("History").child(date).updateChildren(user);
                        Toast.makeText(InputActivity.this, "Data Ditambahkan", Toast.LENGTH_SHORT).show();
                    }else{
                        if(switchbuttonin.isChecked()){
                            pemasukanHarian = jumlahUang;
                        }
                        else{
                            pengeluaranHarian = jumlahUang;
                        }
                        final HashMap<String,Object> users = new HashMap<>();
                        users.put("pemasukan",pemasukanHarian);
                        users.put("pengeluaran",pengeluaranHarian);
                        users.put("saldo",saldo);
                        Rootref.child("Users").child(Prevalent.currentOnlineUser.getEmail())
                                .child("History").child(date).updateChildren(users);
                        Toast.makeText(InputActivity.this, "Data Ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            if(switchbuttonin.isChecked()){
                jenis = "Pemasukan";
                saldo += jumlahUang;
                pemasukan += (Prevalent.currentOnlineUser.getPemasukan() + (jumlahUang));
            }else {
                jenis = "Pengeluaran";
                pengeluaran += (Prevalent.currentOnlineUser.getPengeluaran()+(jumlahUang));
                saldo -= (jumlahUang);
            };

            if (switchbuttonin.isChecked()){
                Rootref.child("Users").child(Prevalent.currentOnlineUser.getEmail()).child("pemasukan")
                        .setValue(pemasukan);
            }
            else {
                Rootref.child("Users").child(Prevalent.currentOnlineUser.getEmail()).child("pengeluaran")
                        .setValue(pengeluaran);
            }
            Rootref.child("Users").child(Prevalent.currentOnlineUser.getEmail()).child("saldo")
                    .setValue(saldo);
            Intent intent = new Intent(InputActivity.this,MainActivity.class);
            startActivity(intent);
            HashMap<String,Object> userData = new HashMap<>();
            userData.put("title",Kategori);
            userData.put("kategori",textTitle.getText().toString());
            userData.put("uang",jumlahUang);
            userData.put("tanggal",date);
            userData.put("memo",inputCatatan.getText().toString());
            Rootref.child("Users").child(Prevalent.currentOnlineUser.getEmail()).child("Detail").child(currentDateandTime).updateChildren(userData);
        }



    }


    public void back(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openKategori(){
        btnKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchbuttonin.isChecked()){
                    Intent intent = new Intent(InputActivity.this,KategoriPemasukanActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(InputActivity.this,KategoriPengluaranActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            String kategori = data.getStringExtra("kategori");
            Prevalent.currentOnlineUser.setKategori(kategori);


    }
}
