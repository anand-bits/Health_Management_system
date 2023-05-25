package com.example.new_trial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class patient_data extends AppCompatActivity {

    TextView profileName, profileEmail, profileUsername, profilePassword,bpm,temp;
    TextView titleName, titleUsername;
    Button notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profileUsername);
        titleName = findViewById(R.id.titleName);
        bpm=findViewById(R.id.TextViewBpmValue);
        temp=findViewById(R.id.textViewTempValue);
        titleUsername = findViewById(R.id.titleUsername);
        notification = findViewById(R.id.sendButtonNotification);

        showUserData();
            upload_firebase();
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(patient_data.this, LoginActivity.class);
                startActivity(intent);

                //passUserData();
            }
        });

    }

    public void showUserData(){
        String userUsername = "nakul";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        String pname = snapshot.child(userUsername).child("name").getValue(String.class);
                                                        String pmail = snapshot.child(userUsername).child("email").getValue(String.class);
                                                        String pusername = snapshot.child(userUsername).child("username").getValue(String.class);
                                                        profileName.setText(pname);
                                                        profileEmail.setText(pmail);
                                                        profileUsername.setText(pname);

                                                        titleName.setText(pname);
                                                        titleUsername.setText(pname);

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });}
    public void  upload_firebase()
{


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Anand");
    reference.addValueEventListener(new ValueEventListener() {


                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String spo2 = snapshot.child("Spo2").getValue(String.class);
                                            String temp2 = snapshot.child("temperature").getValue(String.class);
                                            Long timestamp= snapshot.child("timestamp").getValue(Long.class);
                                            bpm.setText(spo2);
                                            temp.setText(temp2);
                                            Health health= new Health(spo2,temp2, timestamp);

                                            db.collection("Nakul")
                                                    .add(health);





                                        }


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
    );


            }
}

























/* public void passUserData(){
        String userUsername = profileUsername.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    String bpmFromDB = snapshot.child(userUsername).child("bpm").getValue(String.class);
                    String tempFromDB = snapshot.child(userUsername).child("temp").getValue(String.class);

                    /*Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

                    intent.putExtra("name", nameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("password", passwordFromDB);
                    intent.putExtra("bpm", bpmFromDB);
                    intent.putExtra("temp", tempFromDB);

                    startActivity(intent);
                }
            }

           // @Override
           // public void onCancelled(@NonNull DatabaseError error) {

            //}*/