package com.example.simplenotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AlertDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_details);

    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = findViewById(R.id.textViewReg);
        Intent intent = getIntent();
        //PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        textView.setText(intent.getStringExtra("MESS"));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
