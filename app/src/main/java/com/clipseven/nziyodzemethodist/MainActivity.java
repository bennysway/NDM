package com.clipseven.nziyodzemethodist;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }





    public static String userData(Context context, String stringKey, String mode, String data){

        //keys include: favlist,reclist,showsplash,color,image,textsize,recordflag,withcaptions
        SharedPreferences.Editor editor = context.getSharedPreferences(stringKey, MODE_PRIVATE).edit();
        SharedPreferences prefs = context.getSharedPreferences(stringKey, MODE_PRIVATE);
        String restoredText = prefs.getString("list", null);
        String list = "";
        if (restoredText != null) {
            list = prefs.getString("list", "");//"No name defined" is the default value.
        }


        switch (mode){
            case "pushBack":
                list = list + data + ",";
                editor.putString("list", list);
                editor.apply();
                break;

            case "pushFront":
                list =  data + "," + list;
                editor.putString("list", list);
                editor.apply();
                break;

            case "delete":
                list = list.replace(data+",","");
                editor.putString("list", list);
                editor.apply();
                break;

            case "deleteRecord":
                list = list.replace(data,"");
                editor.putString("list", list);
                editor.apply();
                break;

            case "deleteCaption":
                list = list.replace(data,"");
                editor.putString("list", list);
                editor.apply();
                break;

            case "deleteAll":
                list = "";
                editor.putString("list", list);
                editor.apply();
                break;

            case "update":
                list = data;
                editor.putString("list", list);
                editor.apply();
                break;

            case "find":
                list = "."+list;
                data +=",";
                if(list.indexOf(data) > 0)
                    return "true";
                else
                    return "false";


        }
        return list;
    }


    public static SharedPreferences getSharedPreferences (Context ctxt) {
        return ctxt.getSharedPreferences("FILE", 0);

    }

    public String IntToStr(int i){
        return Integer.toString(i);
    }




}
