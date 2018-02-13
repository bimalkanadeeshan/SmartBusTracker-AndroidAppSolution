package com.example.bimalka.smartbustracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FavouriteTrackingsPage extends AppCompatActivity {

    private TextView mTextViewSaveDetails1;
    private TextView mTextViewSaveDetails11;
    private TextView mTextViewSaveDetails2;
    private TextView mTextViewSaveDetails22;
    private TextView mTextViewSaveDetails3;
    private TextView mTextViewSaveDetails33;
    private TextView mTextViewSaveDetails4;
    private TextView mTextViewSaveDetails44;
    private TextView mTextViewSaveDetails5;
    private TextView mTextViewSaveDetails55;
    private TextView mTextViewSaveDetails6;
    private TextView mTextViewSaveDetails66;
    private TextView mTextViewSaveDetails7;
    private TextView mTextViewSaveDetails77;
    private TextView mTextViewSaveDetails8;
    private TextView mTextViewSaveDetails88;

    DatabaseReference databaseReference1;
    DatabaseReference databaseReference11;
    DatabaseReference databaseReference2;
    DatabaseReference databaseReference22;
    DatabaseReference databaseReference3;
    DatabaseReference databaseReference33;
    DatabaseReference databaseReference4;
    DatabaseReference databaseReference44;
    DatabaseReference databaseReference5;
    DatabaseReference databaseReference55;
    DatabaseReference databaseReference6;
    DatabaseReference databaseReference66;
    DatabaseReference databaseReference7;
    DatabaseReference databaseReference77;
    DatabaseReference databaseReference8;
    DatabaseReference databaseReference88;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_trackings_page);

        mTextViewSaveDetails1 = (TextView) findViewById(R.id.textViewSavedRoutes1);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("SavedSearch").child("-L3wMGkRCXRVh-lHpJ-_").child("startingLocation");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mTextViewSaveDetails1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        mTextViewSaveDetails11 = (TextView) findViewById(R.id.textViewSavedRoutes11);

        databaseReference11 = FirebaseDatabase.getInstance().getReference().child("SavedSearch").child("-L3wMGkRCXRVh-lHpJ-_").child("destination");

        databaseReference11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mTextViewSaveDetails11.setText("->  "+value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        mTextViewSaveDetails2 = (TextView) findViewById(R.id.textViewSavedRoutes2);

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("SavedSearch").child("-L3wMPM3otFOMCMWe1C0").child("startingLocation");

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mTextViewSaveDetails2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        mTextViewSaveDetails22 = (TextView) findViewById(R.id.textViewSavedRoutes22);

        databaseReference22 = FirebaseDatabase.getInstance().getReference().child("SavedSearch").child("-L3wMPM3otFOMCMWe1C0").child("destination");

        databaseReference22.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mTextViewSaveDetails22.setText("->  "+value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        mTextViewSaveDetails3 = (TextView) findViewById(R.id.textViewSavedRoutes3);

        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("SavedSearch").child("-L3wOx4jFTMSNVdAtZ1S").child("startingLocation");

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mTextViewSaveDetails3.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        mTextViewSaveDetails33 = (TextView) findViewById(R.id.textViewSavedRoutes33);

        databaseReference33 = FirebaseDatabase.getInstance().getReference().child("SavedSearch").child("-L3wOx4jFTMSNVdAtZ1S").child("destination");

        databaseReference33.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mTextViewSaveDetails33.setText("->  "+value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


    }

}
