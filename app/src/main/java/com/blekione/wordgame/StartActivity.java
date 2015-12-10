package com.blekione.wordgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    public void onClickAddPlayer(View view) {
        Intent intent = new Intent(this, AddNewPlayer.class);
        startActivity(intent);
        TextView welcomeMsg = (TextView) findViewById(R.id.welcome_message);
        welcomeMsg.setText("Welcome! Bleki");
    }

}
