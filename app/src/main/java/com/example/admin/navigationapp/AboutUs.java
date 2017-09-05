package com.example.admin.navigationapp;

import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        VideoView videoView1;
        videoView1 = (VideoView) findViewById(R.id.videoView1);


        String videoSource = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        Uri videoUri = Uri.parse(videoSource);
        videoView1.setVideoURI(videoUri);
        MediaController videoController = new MediaController(this);
        videoController.setAnchorView(videoView1);
        videoView1.setMediaController(videoController);
        videoView1.start();

    }

}
