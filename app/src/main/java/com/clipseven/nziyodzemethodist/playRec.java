package com.clipseven.nziyodzemethodist;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class playRec {
    private String AudioSavePathInDevice = "";
    private MediaPlayer mediaPlayer;

    playRec(String data){
        AudioSavePathInDevice = data;
    }

    void play(){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(AudioSavePathInDevice);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();

    }

    void stop(){

        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }



}
