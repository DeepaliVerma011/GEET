package com.example.tara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText txtemail,txtpassword;
    Button login;
    Button skip;

     FirebaseAuth Firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Firebaseauth= FirebaseAuth.getInstance();
        txtemail= findViewById(R.id.jemail);
        txtpassword= findViewById(R.id.jpassword);
        login= findViewById(R.id.login);
        skip= findViewById(R.id.skip);



        skip.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                      startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                                    }
                                }
        );
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtemail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6) {
                    Toast.makeText(getApplicationContext(), "password should have atleast 6 characters", Toast.LENGTH_SHORT).show();

                }


Firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            Toast.makeText(login.this, "successfully logged in", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(login.this, "signup failed", Toast.LENGTH_SHORT).show();
        }
    }
});



            }
        });

    }

}

















