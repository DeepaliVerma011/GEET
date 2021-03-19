package com.example.tara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;
public class feedback extends AppCompatActivity {
    EditText name, subject, message;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        name = findViewById(R.id.Namedata);
        subject=findViewById(R.id.subjectData);
        message = findViewById(R.id.messageData);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals(""))
                    name.setError("mandatory feild");
                else if(subject.getText().toString().equals(""))
                    subject.setError("mandatory feild");
                else if(message.getText().toString().equals(""))
                    message.setError("mandatory feild");
                else {


                    Intent i = new Intent(Intent.ACTION_SENDTO);

                    i.putExtra(Intent.EXTRA_EMAIL, "xyz@gmail.com");
                    i.putExtra(Intent.EXTRA_SUBJECT,subject.getText());

                    i.putExtra(Intent.EXTRA_TEXT, "name:" + name.getText() + "\n Message:" + message.getText());
                    i.setType("message/rfc822");
                    try {
                    startActivity(Intent.createChooser(i, "please select email"));
                    }
                    catch(ActivityNotFoundException ex){
                        Toast.makeText(feedback.this,"there is no email client", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}