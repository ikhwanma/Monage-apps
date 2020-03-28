package com.android.monagealpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPassword extends AppCompatActivity {

    EditText userMail;
    Button btnForgot;
    ProgressDialog loadingbar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        userMail=findViewById(R.id.etResetMail);
        btnForgot=findViewById(R.id.btnResetPass);

        firebaseAuth = FirebaseAuth.getInstance();
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(userMail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LupaPassword.this,
                                            "Reset Password sudah dikirim Ke email anda ",Toast.LENGTH_LONG).show();

                                }else {
                                    Toast.makeText(LupaPassword.this,
                                            task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });

    }
}
