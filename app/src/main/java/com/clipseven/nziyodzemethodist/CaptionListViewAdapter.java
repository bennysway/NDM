package com.clipseven.nziyodzemethodist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRouter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;



public class CaptionListViewAdapter extends BaseAdapter {
    private static ArrayList<CaptionStorage> searchArrayList;

    private LayoutInflater mInflater;
    MediaPlayer mediaPlayer ;
    String AudioSavePathInDevice = null;


    boolean play = false;

    public CaptionListViewAdapter(Context context, ArrayList<CaptionStorage> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final CaptionListViewAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.caption_list_layout, null);
            holder = new CaptionListViewAdapter.ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.captionDate);
            holder.txtCityState = (TextView) convertView
                    .findViewById(R.id.captionType);

            convertView.setTag(holder);
        } else {
            holder = (CaptionListViewAdapter.ViewHolder) convertView.getTag();
        }


        holder.txtName.setText(searchArrayList.get(position).getTitle());
        holder.txtCityState.setText(searchArrayList.get(position)
                .getType());


        return convertView;
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtCityState;

    }
    public boolean checkPermission(View v) {
        int result = ContextCompat.checkSelfPermission((v.getContext()),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(v.getContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }
}