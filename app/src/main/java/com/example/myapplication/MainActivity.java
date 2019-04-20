package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    player currentplayer = player.player1;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.gridlayout)
    GridLayout gridlayout;
    private boolean gameover = false;
    player[] playerchoose = new player[9];
    public static int gamecount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for (int i = 0; i < 9; i++) {
            playerchoose[i] = player.Noone;
        }


    }


    public void imgclick(View imageview) {
        ImageView image = (ImageView) imageview;
        image.setTranslationX(-2000);

        int tag = Integer.parseInt(image.getTag().toString());
        if (playerchoose[tag - 1] == player.Noone && gameover == false) {
            gamecount++;
            playerchoose[tag - 1] = currentplayer;
            int[][] winner = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
                    {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
            if (currentplayer == player.player1) {
                image.setImageResource(R.drawable.tiger);
                currentplayer = player.player2;
            } else if (currentplayer == player.player2) {
                image.setImageResource(R.drawable.lion);
                currentplayer = player.player1;

            }

            image.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
            for (int[] winnercloum : winner) {
                if (playerchoose[winnercloum[0]] == playerchoose[winnercloum[1]]
                        && playerchoose[winnercloum[1]] == playerchoose[winnercloum[2]] && playerchoose[winnercloum[0]] != player.Noone) {
                    button.setVisibility(View.VISIBLE);
                    gameover = true;
                    if (currentplayer == player.player1) {
                        Toast.makeText(this, "Player2 Winner", Toast.LENGTH_SHORT).show();
                    }
                    if (currentplayer == player.player2) {
                        Toast.makeText(this, "Player1 Winner", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
        if (gamecount==9)
        {
            button.setVisibility(View.VISIBLE);
            gamecount = 0;
        }

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Rest();

    }

    private void Rest() {
        for (int i = 0; i < gridlayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridlayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(.2f);
        }
        for (int i = 0; i < playerchoose.length; i++) {
            playerchoose[i] = player.Noone;
        }
        currentplayer = player.player1;
        gameover = false;
        button.setVisibility(View.INVISIBLE);
    }
}
