package com.android.monagealpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AkunActivity extends AppCompatActivity {
    Button btnEdit,btnBack;
    EditText inputNama,inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        btnBack = findViewById(R.id.btnBack);
        inputNama = findViewById(R.id.inputNama);
        inputEmail = findViewById(R.id.inputEmail);
        inputNama.setText(Prevalent.currentOnlineUser.getNama());
        inputEmail.setText(Prevalent.currentOnlineUser.getEmail().replace("%1","@").replace("@2","."));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AkunActivity.this,PengaturanActivity.class);
                startActivity(intent);
            }
        });


    }
}
