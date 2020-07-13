package com.maochun.progressring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer mUpdateProgressTimer;
    private boolean mUpdateProgressStart = false;

    private CircularProgressView mProgressBar = null;
    private int mProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressView_test);
        mProgressBar.setProgress(0);

        mUpdateProgressTimer = new Timer();
        mUpdateProgressTimer.schedule(new MainActivity.UpdatePogressTimerTask(), 0, 50);
    }

    public void onTestButtonClick(View v){
        if (mUpdateProgressStart){
            mUpdateProgressTimer.cancel();
        }else{
            mUpdateProgressTimer = new Timer();
            mUpdateProgressTimer.schedule(new MainActivity.UpdatePogressTimerTask(), 0, 50);
        }

        mUpdateProgressStart = !mUpdateProgressStart;
    }

    public class UpdatePogressTimerTask extends TimerTask
    {
        public void run()
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setProgress(mProgress);
                    mProgress += 1;
                }
            });
        }
    };
}
