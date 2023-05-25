package com.example.new_trial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class doctorSIgnUppage extends AppCompatActivity {
    EditText DocsignupName, DocsignupEmail, DocsignupUsername, DocsignupPassword;
    TextView loginText;
    Button signupButton;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_uppage);
        DocsignupName = findViewById(R.id.DoctorSignup_name);
        DocsignupEmail = findViewById(R.id.DoctorSignup_email);
        DocsignupUsername = findViewById(R.id.DoctorSignup_username);
        DocsignupPassword = findViewById(R.id.DoctorSignup_password);
        signupButton = findViewById(R.id.DoctorSignup_button);
        loginText = findViewById(R.id.textViewDoctorRef);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = FirebaseDatabase.getInstance();
                ref = db.getReference("doctor");

                String name = DocsignupName.getText().toString();
                String email = DocsignupEmail.getText().toString();
                String username = DocsignupUsername.getText().toString();
                String password = DocsignupPassword.getText().toString();
                HelperClass helperClass = new HelperClass(name, email, username, password);
                ref.child(username).setValue(helperClass);

                Toast.makeText(doctorSIgnUppage.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(doctorSIgnUppage.this, LoginActivity.class);
                startActivity(intent);


            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(doctorSIgnUppage.this, doctlogin.class);
                startActivity(intent);
            }
        });
    }
}