package com.saifkhan.countdowntmer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView TVCountDown;
    private Button BStartAndPause, BReset;
    private CountDownTimer CountDownTimer;
    private Boolean TimeRunning;
    private long TimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TVCountDown = findViewById(R.id.textview_main_countdownview);
        BStartAndPause = findViewById(R.id.button_main_start);
        BReset = findViewById(R.id.button_main_reset);
        BStartAndPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TimeRunning){
                    pauseTimer();
                }
                else{
                    startTimer();
                }
            }
        });
        BReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDown();
    }

    private void startTimer() {
        CountDownTimer = new CountDownTimer(TimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                TimeRunning = false;
            }
        }.start();
        TimeRunning = true;
        BStartAndPause.setText("PAUSE");
        BReset.setVisibility(View.INVISIBLE);
        BReset.setVisibility(View.VISIBLE);

    }

    private void updateCountDown() {
        int minutes = (int) (TimeLeftInMillis/1000)/60;
        int second = (int) (TimeLeftInMillis/1000)%60;
        String timeleftformat = String.format(Locale.getDefault(),"%02d:%02D",minutes,second);
        TVCountDown.setText(timeleftformat);
    }

    private void pauseTimer() {
        CountDownTimer.cancel();
        TimeRunning = false;
        BStartAndPause.setText("START");
        BReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {
        TimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDown();
        BReset.setVisibility(View.INVISIBLE);
        BStartAndPause.setVisibility(View.VISIBLE);
    }


}