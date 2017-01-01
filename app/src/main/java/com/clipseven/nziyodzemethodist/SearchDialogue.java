package com.clipseven.nziyodzemethodist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

public class SearchDialogue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dialogue);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.3));

        final EditText input = (EditText) findViewById(R.id.searchBox);
        final Button startSearch = (Button) findViewById(R.id.searchDialogueButton);
        final Intent toSearch = new Intent(this,Search.class);

        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().toString().equals("")){
                    QuickToast("Nothing to search...");
                }
                else {
                    toSearch.putExtra("search",input.getText().toString());
                    startActivity(toSearch);
                }

            }
        });

    }
    public void QuickToast(String s){
        Toast.makeText(this, s,
                Toast.LENGTH_SHORT).show();
    }
}
