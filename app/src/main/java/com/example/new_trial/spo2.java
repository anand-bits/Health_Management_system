package com.example.new_trial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class spo2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spo2);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Anand");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String spo2 = snapshot.child("Spo2").getValue(String.class);
                String temp = snapshot.child("temperature").getValue(String.class);
                Intent intent = new Intent(spo2.this, ProfileActivity.class);
                intent.putExtra("bpm", spo2);
                intent.putExtra("temp", temp);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}