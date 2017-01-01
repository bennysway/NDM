package com.clipseven.nziyodzemethodist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class removeFav extends AppCompatActivity {
    String list;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_fav);
        final Intent intent = new Intent(this,FavList.class);

        final String s = getIntent().getStringExtra("hymnNum");
        TextView prompt = (TextView) findViewById(R.id.removeFavPrompt);
        Button yes = (Button) findViewById(R.id.removeFavYesBut);
        Button no = (Button) findViewById(R.id.removeFavNoBut);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.2));
        list = MainActivity.userData(this,"favlist","","");

        prompt.setText("Remove hymn " + s + " from favourites?" );


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.userData(removeFav.this,"favlist","delete",s);
                startActivity(intent);
                finish();
            }
        });


    }

}
