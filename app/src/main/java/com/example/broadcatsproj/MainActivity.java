package com.example.broadcatsproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_Broadcast;
    Button btn_Start;
    Receiver localListner;
    public static final String BROADCAST_ACTION = "Broadcast_action 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_Broadcast = findViewById(R.id.tv_broadcast);
        btn_Start = findViewById(R.id.btn_Start);
    }

    protected void onStart() {
        super.onStart();
        localListner = new Receiver();
        IntentFilter intentFilter = new IntentFilter(MainActivity.BROADCAST_ACTION);
        this.registerReceiver(localListner,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListner);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_Start) {
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = tv_Broadcast.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived" + message;
            tv_Broadcast.setText(currentText);
        }
    }
}