package com.clipseven.nziyodzemethodist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Splash extends AppCompatActivity {
    Runnable beginActivity;
    Runnable show1,show2,show3,showTitle,hideshows;
    Handler start,timer;
    Intent toStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Random r=new Random();
        VideoView videoview = (VideoView) findViewById(R.id.videoView);
        final TextView line1 = (TextView) findViewById(R.id.line1);
        final TextView line2 = (TextView) findViewById(R.id.line2);
        final TextView line3 = (TextView) findViewById(R.id.hymnline);
        Button skip = (Button) findViewById(R.id.skipSplash);
        toStart = new Intent(this,MainDrawer.class);
        timer = new Handler();
        int i=r.nextInt(317)+1;
        String h = "hymn" + IntToStr(i);
        String [] hymn;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean longSplash = preferences.getBoolean("long_splash",true);
        Data recordFlag = new Data(this,"recordflag");
        recordFlag.deleteAll();
        start = new Handler();


        int choice = r.nextInt(4)+1;
        Uri uri;
        switch (choice){
            case 1:
                uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash1);
                break;
            case 2:
                uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash2);
                break;
            case 3:
                uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash3);
                break;
            case 4:
                uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash4);
                break;
            default:
                uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash1);
                break;


        }
        videoview.setVideoURI(uri);
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        videoview.start();

        int resourceId = getResourceId(h,"array",getPackageName());
        hymn = getResources().getStringArray(resourceId);

        final String [] lines = hymn[0].split("\n");
        lines[2]= "Hymn " + IntToStr(i);
        line1.setText(lines[0]);
        line2.setText(lines[1]);
        line3.setText(lines[2]);
        line1.setAlpha(0);
        line2.setAlpha(0);
        line3.setAlpha(0);

        //////Runnables
        beginActivity = (new Runnable() {
            @Override
            public void run() {
                startActivity(toStart);
            }
        });
        show1 = new Runnable() {
            @Override
            public void run() {
                line1.animate().alpha(1);

            }
        };
        show2 = new Runnable() {
            @Override
            public void run() {
                line2.animate().alpha(1);

            }
        };
        show3 = new Runnable() {
            @Override
            public void run() {
                line3.animate().alpha(1);

            }
        };
        hideshows = new Runnable() {
            @Override
            public void run() {
                line1.animate().alpha(0);
                line2.animate().alpha(0);
                line3.animate().alpha(0);
            }
        };
        showTitle = new Runnable() {
            @Override
            public void run() {
                line1.setText("Nziyo DzeMethodist");
                start.postDelayed(beginActivity,4000);
                line1.animate().alpha(1).setDuration(100);
                line1.animate().scaleY(1.1f).scaleX(1.1f).setDuration(3000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        line1.animate().alpha(.5f).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                line1.animate().alpha(1f).setDuration(100);
                            }
                        });
                    }
                });
            }
        };


        if(longSplash){
            timer.postDelayed(show1,1000);
            timer.postDelayed(show2,3000);
            timer.postDelayed(show3,5000);
            timer.postDelayed(hideshows,7000);
            timer.postDelayed(showTitle,7300);
        }
        else
            showTitle.run();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.removeCallbacks(show1);
                timer.removeCallbacks(show2);
                timer.removeCallbacks(show3);
                timer.removeCallbacks(showTitle);
                start.removeCallbacks(beginActivity);
                startActivity(toStart);
            }
        });
        skip.setAlpha(0f);
        skip.animate().alpha(.5f).setDuration(2000);




    }

    public int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public String IntToStr(int i){
        return Integer.toString(i);
    }
    public void QuickToast(String s){
        Toast.makeText(this, s,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            timer.removeCallbacks(show1);
            timer.removeCallbacks(show2);
            timer.removeCallbacks(show3);
            timer.removeCallbacks(showTitle);
            start.removeCallbacks(beginActivity);
            QuickToast("HymnBook Closed");
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
