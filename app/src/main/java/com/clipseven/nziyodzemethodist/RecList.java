package com.clipseven.nziyodzemethodist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecList extends AppCompatActivity {

    ListView ls;
    String list = "";
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MainActivity.userData(this,"recordflag","deleteAll","");


        TextView norecText = (TextView) findViewById(R.id.norecText);
        ls = (ListView) findViewById(R.id.RecListView);
        final Intent toHymn = new Intent(this,hymnDisplay.class);
        final Intent toHome = new Intent(this,MainDrawer.class);
        final Intent toFav = new Intent(this,MakeFav.class);
        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.deleteHistory);
        int counter = 0;

        list = MainActivity.userData(this,"reclist","","");
        for( int i=0; i<list.length(); i++ ) {
            if( list.charAt(i) == ',' ) {
                counter++;
            }
        }
        final String[]recHymns =list.split(",");
        String[] names = new String[counter];
        for(int i=0;i<counter;i++){
            names[i] =recHymns[i]+". "+ getStringResourceByName("hymn"+recHymns[i]+"firstline");
        }

        if(counter==0){
            norecText.animate().scaleY(2f).scaleX(2f).setDuration(100000);
        }
        else {
            adapter =
                    new ArrayAdapter<String>(
                            this,
                            R.layout.hymn_list,R.id.hymnFirstLinebut,
                            names
                    );
            ls.setAdapter(adapter);
            ls.setVisibility(View.VISIBLE);
            norecText.setVisibility(View.INVISIBLE);
        }


        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toHymn.putExtra("hymnNum",recHymns[i]);
                startActivity(toHymn);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.userData(RecList.this,"reclist","deleteAll","");
                QuickToast("History has been cleared.");
                startActivity(toHome);
            }
        });

        ls.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                toFav.putExtra("hymnNum",recHymns[i]);
                startActivity(toFav);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                return true;
            }
        });

    }

    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }
    public void QuickToast(String s){
        Toast.makeText(this, s,
                Toast.LENGTH_SHORT).show();
    }


}
