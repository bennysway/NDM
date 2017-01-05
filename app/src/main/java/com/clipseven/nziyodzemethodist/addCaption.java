package com.clipseven.nziyodzemethodist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class addCaption extends AppCompatActivity {
    String hymnNum,hymnNumWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_caption);

        hymnNum = getIntent().getStringExtra("hymnNum");
        hymnNumWord = getIntent().getStringExtra("hymnNumWord");
        final Data recordCheck = new Data(this,"recordflag");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.4));

        Button record = (Button) findViewById(R.id.addAudio);
        Button note = (Button) findViewById(R.id.addNote);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRecord = new Intent(addCaption.this,hymnDisplay.class);
                toRecord.putExtra("hymnNum",hymnNum);
                toRecord.putExtra("hymnNumWord",hymnNumWord);
                recordCheck.update("true");
                startActivity(toRecord);
                finish();
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toNote = new Intent(addCaption.this,makeNote.class);
                toNote.putExtra("captionKey",hymnNumWord);
                toNote.putExtra("captionType","note");
                startActivity(toNote);
                finish();
            }
        });

    }
    public void QuickToast(String s){
        Toast.makeText(this, s,
                Toast.LENGTH_SHORT).show();
    }

}
