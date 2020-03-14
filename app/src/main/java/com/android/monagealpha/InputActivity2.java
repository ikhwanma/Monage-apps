package com.android.monagealpha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InputActivity2 extends AppCompatActivity {
    SwitchCompat switchbuttonin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2);
        switchbuttonin= findViewById(R.id.switchbutton);
        switchbuttonin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchbuttonin.isChecked()){
                    Intent intent = new Intent(InputActivity2.this,InputActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
