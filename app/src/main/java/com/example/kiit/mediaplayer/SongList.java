package com.example.kiit.mediaplayer;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {

    ArrayList<Songs> slist;
    SongsAdapter sadapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        lv=(ListView)findViewById(R.id.list);
        slist=new ArrayList<Songs>();
        sadapter=new SongsAdapter(this,R.layout.songlist,slist);
        lv.setAdapter(sadapter);
        songdata();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String bittu=slist.get(position).getName();
                Intent i=new Intent(SongList.this,Player.class);
                i.putExtra("pos",position);
                startActivity(i);
                finish();
            }
        });


    }
    public void songdata(){
      Songs songs;
        Data data = new Data();
        for(int i=0;i<data.name.length;i++) {
            songs = new Songs(""+(i+1),data.name[i], data.movie[i]);
            slist.add(songs);
        }

    }
}
