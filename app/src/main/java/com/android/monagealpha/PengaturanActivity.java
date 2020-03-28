package com.android.monagealpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PengaturanActivity extends AppCompatActivity {
    Button btnAkun,btnBack;
    TextView viewAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        btnAkun = findViewById(R.id.btnAkun);
        btnBack = findViewById(R.id.btnBack);
        viewAkun = findViewById(R.id.viewAkun);

        viewAkun.setText(Prevalent.currentOnlineUser.getNama());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengaturanActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengaturanActivity.this,AkunActivity.class);
                startActivity(intent);
            }
        });
    }
}
