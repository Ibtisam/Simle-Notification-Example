package com.example.simplenotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AlertDetailsRegular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts_details_regular);
    }
    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = findViewById(R.id.textViewReg);
        Intent intent = getIntent();
        //PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        textView.setText(intent.getStringExtra("MESS"));
    }
}