package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView timerTextView = (TextView)findViewById(R.id.timerTextView);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timerTextView.setVisibility(View.VISIBLE);

                int minutes = progress/60;
                int seconds = progress % 60;
                if(seconds<10){
                    String secondsNew = "0" + seconds;
                    timerTextView.setText(minutes + ":" + secondsNew);
                }else {
                    timerTextView.setText(minutes + ":" + seconds);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void onClick(View view){

        ImageView cracked1 = (ImageView)findViewById(R.id.crackedEgg);
        ImageView unCracked1 = (ImageView)findViewById(R.id.uncrackedEgg);
        cracked1.animate().alpha(0f).setDuration(0);
        unCracked1.animate().alpha(1f).setDuration(0);

        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);

        final TextView  timerTextView = (TextView)findViewById(R.id.timerTextView);

        final Button button = (Button)findViewById(R.id.startButton);

        button.setVisibility(View.INVISIBLE);

        final int countDown = seekBar.getProgress();

        seekBar.setEnabled(false);

        new CountDownTimer(countDown*1000, 1000){
            int timer = 0;
            public void onTick(long milliSecondsUntilDone){

                timer+=1;
                int minutes = (countDown-timer)/60;
                int seconds = (countDown-timer)%60;
                if(seconds<10){
                    String secondsNew = "0" + seconds;
                    timerTextView.setText(minutes + ":" + secondsNew);
                }else {
                    timerTextView.setText(minutes + ":" + seconds);
                };

            }

            public void onFinish(){

                seekBar.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                ImageView cracked = (ImageView)findViewById(R.id.crackedEgg);
                ImageView unCracked = (ImageView)findViewById(R.id.uncrackedEgg);
                cracked.animate().alpha(1f).setDuration(0);
                unCracked.animate().alpha(0f).setDuration(0);

                timerTextView.setVisibility(View.INVISIBLE);

                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.bomb);
                mp.start();

            }
        }.start();
    }
}
