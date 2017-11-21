package com.example.kiit.mediaplayer;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SongsAdapter extends ArrayAdapter<Songs> {
    Context context;
    ArrayList<Songs> songs;
    public SongsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Songs> songs) {
        super(context, resource, songs);
        this.context=context;
        this.songs=songs;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        if(convertView==null){
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.songlist,parent,false);
        }
        else {
            v=convertView;
        }
        TextView textView=(TextView)v.findViewById(R.id.textView);
        String sname=songs.get(position).getName();
        textView.setText(sname);

        TextView textView1=(TextView)v.findViewById(R.id.textView2);
        String sno=songs.get(position).getNo();
        textView1.setText(sno);

        TextView textView2=(TextView)v.findViewById(R.id.textView3);
        String smovie=songs.get(position).getMovie();
        textView2.setText(smovie);
        return v;
    }
}
