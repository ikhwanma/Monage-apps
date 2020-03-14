package com.android.monagealpha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity {
    Button btnAdd,btnBack,btnKategori;
    Kategori kategori;
    DataHelper myDb;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnC;
    String input = "IDR ",jumlahUang;
    TextView viewInput,viewKategori;
    ArrayList<String> jumlah;
    SwitchCompat switchbuttonin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnC = findViewById(R.id.btnC);
        jumlah = new ArrayList<>();
        viewInput = findViewById(R.id.viewInput);
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        btnKategori = findViewById(R.id.inputKategori);
        viewKategori = findViewById(R.id.viewKategori);
        kategori = new Kategori();
        myDb = new DataHelper(this);
        viewKategori.setText(kategori.getKategori());
        openKategori();
        addData();
        back();
        add0();add1();add2();add3();add4();add5();add6();add7();add8();add9();addC();

        //switch buttton
        switchbuttonin= findViewById(R.id.switchbutton);
        switchbuttonin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchbuttonin.isChecked()){
                    Intent intent = new Intent(InputActivity.this,InputActivity2.class);
                    startActivity(intent);
                }
            }
        });


    }
    public  void addData(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahUang = "";
                for(int i = 0; i<jumlah.size();i++)
                    jumlahUang += jumlah.get(i);
                boolean isInserted = myDb.insertData("Makanan",Integer.parseInt(jumlahUang));
                if(isInserted=true){
                    Toast.makeText(InputActivity.this, "Uang Ditambah:)", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InputActivity.this, "Data belum dimasukkan", Toast.LENGTH_SHORT).show();
                }
                input = "IDR ";
                viewInput.setText(input);
            }
        });
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
    public void add1(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="1";
                jumlah.add("1");
                viewInput.setText(input);
            }
        });
    }
    public void add2(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="2";
                jumlah.add("2");
                viewInput.setText(input);
            }
        });
    }
    public void add3(){
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="3";
                jumlah.add("3");
                viewInput.setText(input);
            }
        });
    }
    public void add4(){
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="4";
                jumlah.add("4");
                viewInput.setText(input);
            }
        });
    }
    public void add5(){
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="5";
                jumlah.add("5");
                viewInput.setText(input);
            }
        });
    }public void add6(){
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="6";
                jumlah.add("6");
                viewInput.setText(input);
            }
        });
    }public void add7(){
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="7";
                jumlah.add("7");
                viewInput.setText(input);
            }
        });
    }public void add8(){
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="8";
                jumlah.add("8");
                viewInput.setText(input);
            }
        });
    }public void add9(){
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="9";
                jumlah.add("9");
                viewInput.setText(input);
            }
        });
    }public void add0(){
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input+="0";
                jumlah.add("0");
                viewInput.setText(input);
            }
        });
    }public void addC(){
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = "IDR ";
                jumlah.clear();
                viewInput.setText(input);
            }
        });
    }
    public void openKategori(){
        btnKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this,KategoriActivity.class);
                startActivity(intent);
            }
        });
    }



}
