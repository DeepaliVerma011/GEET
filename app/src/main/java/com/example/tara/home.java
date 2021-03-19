package com.example.tara;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class home extends AppCompatActivity {
    TextView name,email,password,phn;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String UserID;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_home);
        name=findViewById(R.id.t1);
        email=findViewById(R.id.t2);
        password=findViewById(R.id.t3);
        phn= findViewById(R.id.t4);

        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        UserID= fauth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {



                phn.setText(documentSnapshot.getString("fphoneNo"));
                name.setText(documentSnapshot.getString("fname"));
                password.setText(documentSnapshot.getString("fpassword"));
                email.setText(documentSnapshot.getString("femail"));
            }
        });
    }
}