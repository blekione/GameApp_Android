package com.blekione.wordgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.blekione.wordgame.com.blekione.wordgame.domain.Player;

public class AddNewPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_player);
        final EditText playerNameText = (EditText) findViewById(R.id.player_name);
        playerNameText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handle = false;
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    StartActivity.setPlayer(playerNameText.getText().toString());
                    StartActivity.setLastPlayer(StartActivity.getPlayer(Player.getIdCounter() - 1));
                    openMainActivity();
                }
                    return false;
            }
        });
    }

    public void onClickAddNewPlayer(View view) {
        EditText playerNameText = (EditText) findViewById(R.id.player_name);
        StartActivity.setPlayer(playerNameText.getText().toString());
        StartActivity.setLastPlayer(StartActivity.getPlayer(Player.getIdCounter() - 1));
        openMainActivity();
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
