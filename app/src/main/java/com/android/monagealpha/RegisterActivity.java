package com.android.monagealpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private EditText inputNama,inputEmail,inputPassword;
    private ProgressDialog loadingbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        inputNama = findViewById(R.id.inputNama);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        loadingbar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    public void createAccount(){
        String nama = inputNama.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputEmail.setError("Invalid Email");
            inputEmail.setFocusable(true);
        }if (password.length()<6){
            inputPassword.setError("Password Minimal 6 Karakter");
            inputPassword.setFocusable(true);
        }if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length()>=6) {
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Please wait!");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            registerUser(nama, email, password);
        }


        }
    private void registerUser(final String nama, final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            loadingbar.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            ValidateUser(nama, email, password);
                            Intent intent = new Intent(RegisterActivity.this,login.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this, "Account created", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadingbar.dismiss();
                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ValidateUser(final String nama, String emailAsli, final String password) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        final String email = emailAsli.replace("@","%1").replace(".","%2");

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("email", email);
                    userdataMap.put("password",password);
                    userdataMap.put("nama",nama);
                    userdataMap.put("img","");
                    userdataMap.put("pemasukan",0);
                    userdataMap.put("pengeluaran",0);
                    userdataMap.put("kategori","");
                    userdataMap.put("saldo",0);

                    Rootref.child("Users").child(email).updateChildren(userdataMap);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
