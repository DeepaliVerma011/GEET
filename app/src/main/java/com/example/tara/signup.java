package com.example.tara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class signup extends AppCompatActivity {

    EditText txtemail,txtusername,txtpassword,txtconfirmpassword,txtphonenumber;
    Button txtbutton;
    Button txtbutton2;
    FirebaseFirestore fstore = FirebaseFirestore.getInstance();
String userID;

   private FirebaseAuth Firebaseauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

         fstore = FirebaseFirestore.getInstance();
        Firebaseauth= FirebaseAuth.getInstance();
        txtemail= findViewById(R.id.jemail);
        txtusername= findViewById(R.id.jusername);
        txtpassword= findViewById(R.id.jpassword);
        txtconfirmpassword= findViewById(R.id.jconfirmpassword);
        txtphonenumber= findViewById(R.id.jphonenumber);
        txtbutton= findViewById(R.id.jbutton);
        txtbutton2= findViewById(R.id.jbutton2);


        if(Firebaseauth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            finish();
        }



txtbutton2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(signup.this,login.class);
        startActivity(myIntent);
    }
});



        txtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = txtemail.getText().toString().trim();
                  final String username = txtusername.getText().toString().trim();
                  final String password = txtpassword.getText().toString();
                  final String phoneNo = txtphonenumber.getText().toString();
                  final String confirmpassword = txtconfirmpassword.getText().toString();



                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(signup.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(password)) {
                    Toast.makeText(signup.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(username)) {
                    Toast.makeText(signup.this, "please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(signup.this, "please enter confirmpassword", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(password.length()<6) {
                    Toast.makeText(signup.this, "password should have atleast 6 characters", Toast.LENGTH_SHORT).show();

                }


                if(password.equals(confirmpassword)){
                    Firebaseauth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                                        userID = Firebaseauth.getCurrentUser().getUid();
                                        DocumentReference documentReference= fstore.collection("users").document(userID);
                                        Map<String,Object> user=new HashMap<>();
                                        user.put("fname",username);
                                        user.put("femail", email);
                                               user.put("fphoneNo",phoneNo);
                                               user.put("fpassword", password);
                                               user.put("fconfirm",confirmpassword);
                                               documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                   @Override
                                                   public void onSuccess(Void aVoid) {
                                                       Log.d("TAG","onsuccess: user profile is created for"+userID);
                                                   }
                                               });
                                        Toast.makeText(signup.this, "successfully signed up", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(signup.this, "signup failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                }
            }
        });


        }



}




