package com.example.kiit.mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Player extends AppCompatActivity {
    Button play;
    TextView title;
    Data d;
    ImageView img;
    SeekBar sk;
    MediaPlayer mp;
    int p;
    TextView endtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);



        d=new Data();
        sk=(SeekBar)findViewById(R.id.seekBar);
        play=(Button)findViewById(R.id.button);
        title=(TextView)findViewById(R.id.textView4);
        endtime=(TextView)findViewById(R.id.textView5);
        img=(ImageView)findViewById(R.id.imageView);
        Intent i=getIntent();
        p=i.getExtras().getInt("pos");

        Glide.with(this).load(R.drawable.animation).asGif().into(img);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressvalue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressvalue=progress;
                if(progress==100&&p<d.name.length-1){
                    p=p+1;
                    setTitile();
                    if(mp!=null) {
                        mp.stop();
                        mp = MediaPlayer.create(Player.this, d.songs[p]);
                        mp.start();
                        play.setBackgroundResource(R.drawable.pause);
                    }

                }
                else if(progress==100){
                    mp=null;
                    play.setBackgroundResource(R.drawable.play);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(mp!=null)
                mp.seekTo(mp.getDuration()/100*progressvalue);
            }
        });
        setTitile();
        thread();
        Thread2();




    }
    public void setTitile(){
        title.setText(d.name[p]+"["+d.movie[p]+"]");

    }
    public void play(View view) {
        if(mp==null||!mp.isPlaying()) {
            if (mp == null) {
                mp = MediaPlayer.create(this, d.songs[p]);
                mp.start();
            }
            else if(!mp.isPlaying()){
                mp.start();
            }
            play.setBackgroundResource(R.drawable.pause);
        }
        else {
            mp.pause();
            play.setBackgroundResource(R.drawable.play);

        }
        }


    public void stop(View view){
        if(mp!=null){
            mp.stop();
            mp=null;
            endtime.setText("0:00");
            play.setBackgroundResource(R.drawable.play);

        }
    }

    public void forward(View view){
        if(mp!=null) {
            mp.seekTo(mp.getCurrentPosition() + 5000);
        }
        else{
            mp=MediaPlayer.create(this,d.songs[p]);
            mp.seekTo(mp.getCurrentPosition()+5000);
        }
    }
    public void rewind(View view){
        if(mp!=null){
            mp.seekTo(mp.getCurrentPosition()-5000);
        }

    }
    public void thread() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (mp == null) {
                        sk.setProgress(0);
                    }
                    else if(!sk.isPressed()) {
                        try {
                            sk.setProgress(mp.getCurrentPosition() * 100 / mp.getDuration());
                        }catch (Exception e){}


                    }
                }
            }
        };
        t.start();
    }
    public void next(View view){
        if(p<d.name.length-1) {
            p = p + 1;
            setTitile();
            if(mp!=null){
                mp.stop();
                mp = MediaPlayer.create(this, d.songs[p]);
                mp.start();
                play.setBackgroundResource(R.drawable.pause);
            }
        }
    }
    public void previous(View view){
        if(p>0) {
            p = p - 1;
            setTitile();
            if(mp!=null){
                mp.stop();
                mp = MediaPlayer.create(this, d.songs[p]);
                mp.start();
                play.setBackgroundResource(R.drawable.pause);
            }
        }
    }
    public void list(View view){
        if(mp!=null&&mp.isPlaying()){
        mp.stop();
        }
        Intent i=new Intent(Player.this,SongList.class);
        startActivity(i);
        finish();
    }
    public void Thread2(){
        Thread thread=new Thread() {
            @Override
            public void run() {
                while (true) {
                    if(mp!=null) {
                        try {
                            int x = mp.getCurrentPosition() / 1000 / 60;
                            int y = (mp.getCurrentPosition()) / 1000 % 60;
                            if (y < 10)
                                endtime.setText("" + x + ":0" + y);
                            else
                                endtime.setText("" + x + ":" + y);
                        } catch (Exception e) {
                        }

                    }

                }
            }
        };
        thread.start();

    }

}
