package com.blekione.wordgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.blekione.wordgame.com.blekione.wordgame.domain.Player;

public class AddNewPlayerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_player);
        final EditText playerNameText = (EditText) findViewById(R.id.player_name);
        // accept new player by pressing "done" button on Android virtual keyboard
        playerNameText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handle = false;
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    MainActivity.getActvityRef().addPlayer(playerNameText.getText().toString());
                    MainActivity.setLastPlayer(MainActivity.getPlayer(Player.getIdCounter() - 1));
                    openMainActivity();
                }
                    return false;
            }
        });
    }

    public void onClickAddNewPlayer(View view) {
        EditText playerNameText = (EditText) findViewById(R.id.player_name);
        MainActivity.getActvityRef().addPlayer(playerNameText.getText().toString());
        MainActivity.setLastPlayer(MainActivity.getPlayer(Player.getIdCounter() - 1));
        openMainActivity();
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
