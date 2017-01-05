package com.clipseven.nziyodzemethodist;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class makeNote extends AppCompatActivity {
    String RandomNoteFileName = "abcdefghijklmnop";
    Random random ;
    String fullFile,key;
    Data dataKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_note);
        key = getIntent().getStringExtra("captionKey");
        dataKey = new Data(this,key);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Button share = (Button) findViewById(R.id.shareNote);
        Button save = (Button) findViewById(R.id.saveNote);
        Button cancel = (Button) findViewById(R.id.undoNote);
        final EditText textField = (EditText) findViewById(R.id.textField);
        TextView author = (TextView) findViewById(R.id.author);
        TextView lastEditTime = (TextView) findViewById(R.id.editDate);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = textField.getText().toString();
                if(!data.equals("")){
                    writeToFile(data);
                    finish();
                    QuickToast("Saved.");
                }
                else {
                    QuickToast("Cannot save. Some text is required.");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                QuickToast("No changes were made.");
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = textField.getText().toString();
                if(!data.equals("")){
                    copy(data);
                    finish();
                    QuickToast("Sharing note...");
                }
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String clr = preferences.getString("example_text","Set name");
        author.setText(clr);
        if(author.getText().toString().equals("Set name"))
            author.setVisibility(View.INVISIBLE);
        lastEditTime.setText(timeStamp());


    }
    public String timeStamp(){
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy HH:mm", Locale.US);
        Date resultdate = new Date(yourmilliseconds);
        return sdf.format(resultdate);
    }

    public void writeToFile(String body)
    {
        random = new Random();
        fullFile = CreateRandomAudioFileName(5)+key;
        Data fullKey = new Data(this,key+fullFile);
        dataKey.pushBack(timeStamp());
        dataKey.pushBack("note");
        dataKey.pushBack(fullFile);
        fullKey.update(body);
    }
    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomNoteFileName.
                    charAt(random.nextInt(RandomNoteFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }
    public void QuickToast(String s){
        Toast.makeText(this, s,
                Toast.LENGTH_SHORT).show();
    }
    public void copy(String s){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Hymn", s);
        clipboard.setPrimaryClip(clip);
        QuickToast("Hymn copied to clipboard");
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Nziyo DzeMethodist");
            i.putExtra(Intent.EXTRA_TEXT, s);
            startActivity(Intent.createChooser(i, "Share the Gospel:"));
        } catch(Exception e) {
            //e.toString();
        }
    }


}
