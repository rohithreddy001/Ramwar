package com.example.ramwar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView ivCard1;
    private ImageView ivCard2;
    private ImageView ivCard3;
    private TextView tvPlayerScore;
    private TextView tvPlayerScore1;
    private TextView tvCpuScore;
    private int playerScore = 0;
    private int playerScore1 = 0;
    private int cpuScore = 0;
    private ImageView[] cardViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCard1 = findViewById(R.id.card1);
        ivCard2 = findViewById(R.id.card2);
        ivCard3 = findViewById(R.id.card3);
        tvPlayerScore = findViewById(R.id.tvPlayerScore);
        tvPlayerScore1 = findViewById(R.id.tvPlayerScore1);
        tvCpuScore = findViewById(R.id.tvCpuScore);
    }
    public void reset(View view)
    {
        cpuScore =0;
        playerScore1=0;
        playerScore=0;
        tvPlayerScore.setText(playerScore + "");
        tvCpuScore.setText(cpuScore + "");
        tvPlayerScore1.setText(playerScore1 + "");


      
    }

    public void deal(View view) {
        PlayingCard playersCard = PlayingCard.drawRandomCard();
        PlayingCard playersCard1 = PlayingCard.drawRandomCard();

        PlayingCard cpuCard = PlayingCard.drawRandomCard();
        updateCardImages(playersCard, playersCard1,cpuCard);

        if (playersCard.beats(cpuCard) && playersCard.beats(playersCard1) ) {
            playerScore++;
            tvPlayerScore.setText(playerScore + "");
        }  else if (playersCard1.beats(cpuCard) && playersCard1.beats(playersCard) ) {
            playerScore1++;
            tvPlayerScore1.setText(playerScore1 + "");
        }
        else if (playersCard.isTie(cpuCard) && playersCard.isTie(playersCard1)) {
            Toast message = new Toast(getApplicationContext());
            message.makeText(MainActivity.this, "You tied!", Toast.LENGTH_SHORT).show();
        }
        else {
            cpuScore++;
            tvCpuScore.setText(cpuScore + "");
        }
    if(cpuScore==10 )
    {
        Toast message = new Toast(getApplicationContext());
        message.makeText(MainActivity.this, "cpu won!", Toast.LENGTH_SHORT).show();
        cpuScore=0;
        playerScore=0;
        playerScore1=0;
        tvPlayerScore.setText(playerScore + "");
        tvCpuScore.setText(cpuScore + "");
        tvPlayerScore1.setText(playerScore1 + "");
    }
    else if(playerScore==10)
    {
        Toast message = new Toast(getApplicationContext());
        message.makeText(MainActivity.this, "player won!", Toast.LENGTH_SHORT).show();
        cpuScore=0;
        playerScore=0;
        playerScore1=0;
        tvPlayerScore.setText(playerScore + "");
        tvCpuScore.setText(cpuScore + "");
        tvPlayerScore1.setText(playerScore1 + "");
    }
    else if(playerScore1==10)
    {
        Toast message = new Toast(getApplicationContext());
        message.makeText(MainActivity.this, "player1 won!", Toast.LENGTH_SHORT).show();
        cpuScore=0;
        playerScore=0;
        playerScore1=0;
        tvPlayerScore.setText(playerScore + "");
        tvCpuScore.setText(cpuScore + "");
        tvPlayerScore1.setText(playerScore1 + "");
    }

    }

    private void updateCardImages(PlayingCard playersCard,
                                  PlayingCard cpuCard,PlayingCard playersCard1) {
        int drawableId1 = getIdentifierForImageResource(
                playersCard.getNameForRank("card%d")
        );

        int drawableId2 = getIdentifierForImageResource(
                cpuCard.getNameForRank("card%d")
        );
        int drawableId3 = getIdentifierForImageResource(
                playersCard1.getNameForRank("card%d")
        );


        ivCard1.setImageDrawable(getDrawable(drawableId1));
        ivCard2.setImageDrawable(getDrawable(drawableId2));
        ivCard3.setImageDrawable(getDrawable(drawableId3));
    }

    private int getIdentifierForImageResource(String resourceName) {
        return getResources().getIdentifier(
                resourceName,
                "drawable",
                "com.example.ramwar"
        );
    }

}