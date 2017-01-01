package com.clipseven.nziyodzemethodist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClearData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_data);

        TextView prompt = (TextView) findViewById(R.id.clearDataPrompt);
        Button yes = (Button) findViewById(R.id.clearDataYesBut);
        Button no = (Button) findViewById(R.id.clearDataNoBut);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.3));

        prompt.setText("Clear All saved Favourites and Recent history?" );
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ClearData.this);
                MainActivity.userData(ClearData.this,"favlist","deleteAll","");
                MainActivity.userData(ClearData.this,"reclist","deleteAll","");
                MainActivity.userData(ClearData.this,"showsplash","deleteAll","");
                MainActivity.userData(ClearData.this,"image","deleteAll","");
                MainActivity.userData(ClearData.this,"showsplash","deleteAll","");
                MainActivity.userData(ClearData.this,"color","deleteAll","");
                MainActivity.userData(ClearData.this,"textsize","deleteAll","");
                MainActivity.userData(ClearData.this,"recordflag","deleteAll","");

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("example_text", "Set name");
                editor.apply();

                QuickToast("Everything will be fully cleared when you restart the hymn book.");

                finish();
            }
        });


    }
    public void QuickToast(String s){
        Toast.makeText(this, s,
                Toast.LENGTH_SHORT).show();
    }

}
