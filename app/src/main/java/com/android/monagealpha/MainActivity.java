package com.android.monagealpha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    TextView viewPemasukan,viewSaldo;
    Button btnHistory,btnLaporan,btnPlus;
    DataHelper myDb;
    int jumlahPemasukan,jumlahPengeluaran,jumlahSaldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPemasukan = findViewById(R.id.viewPemasukan);
        viewSaldo = findViewById(R.id.viewSaldo);
        btnHistory = findViewById(R.id.btnHistory);
        btnLaporan = findViewById(R.id.btnLaporan);
        btnPlus = findViewById(R.id.btnPlus);
        myDb = new DataHelper(this);
        viewAll();
        jumlahPemasukan = 0;
        jumlahSaldo = 0;
        jumlahPengeluaran = 0;
        Cursor res = myDb.getAllData();
        while(res.moveToNext()){
            jumlahPemasukan += res.getInt(2);
        }
        jumlahSaldo = jumlahPemasukan - jumlahPengeluaran;
        viewPemasukan.setText(String.valueOf(jumlahPemasukan));
        viewSaldo.setText(String.valueOf(jumlahSaldo));
        openInput();

    }

    public void viewAll(){
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    showMessage("Error","No data found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("Id : " + res.getString(0)+"\n");
                    buffer.append("Nama : " + res.getString(1)+"\n");
                    buffer.append("Pemasukan : " + res.getString(2)+"\n\n");
                }
                showMessage("Data",buffer.toString());

            }
        });
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void openInput(){
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InputActivity.class);
                startActivity(intent);
            }
        });
    }
}
