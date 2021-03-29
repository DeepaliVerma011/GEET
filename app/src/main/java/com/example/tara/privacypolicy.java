package com.example.tara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class privacypolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacypolicy);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.action_privacy_policy_activity);
        setSupportActionBar(toolbar);
    }
}