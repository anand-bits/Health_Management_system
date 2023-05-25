package com.example.new_trial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class doctlogin extends AppCompatActivity {
    EditText doctloginUsername, doctloginPassword;
    Button loginButton;
    TextView Doctsignuplink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctlogin);
        doctloginUsername = findViewById(R.id.Doctlogin_username);
        doctloginPassword = findViewById(R.id.Doctlogin_password);
        Doctsignuplink = findViewById(R.id.DoctsignupRedirectText);
        loginButton = findViewById(R.id.Doctlogin_button);
        Doctsignuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(doctlogin.this, doctorSIgnUppage.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUsername() | !validatePassword()){

                } else {
                    checkUser();
                }
            }
        });
    }

    public Boolean validateUsername() {
        String val = doctloginUsername.getText().toString();
        if (val.isEmpty()) {
            doctloginUsername.setError("Username cannot be empty");
            return false;
        } else {
            doctloginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = doctloginPassword.getText().toString();
        if (val.isEmpty()) {
            doctloginPassword.setError("Password cannot be empty");
            return false;
        } else {
            doctloginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userUsername = doctloginUsername.getText().toString().trim();
        String userPassword = doctloginPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("doctor");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    doctloginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)) {
                        doctloginUsername.setError(null);

                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        Intent intent = new Intent(doctlogin.this, patient_data.class);
                        startActivity(intent);
                    } else {
                        doctloginPassword.setError("Invalid Credentials");
                        doctloginPassword.requestFocus();
                    }
                } else {
                    doctloginUsername.setError("User does not exist");
                    doctloginUsername.requestFocus();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}