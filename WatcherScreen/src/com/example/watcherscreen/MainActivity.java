package com.example.watcherscreen;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements OnCompletionListener, OnPreparedListener, OnErrorListener {

	private VideoView videoView;
	private final String streamURL="rtmp://52.35.77.197:1935/live/myStream";
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (!LibsChecker.checkVitamioLibs(this)){
			System.out.println("Vitameo library doesn't setup properly!");
			return;
		}
		
		setContentView(R.layout.activity_main);
		
		videoView=(VideoView)findViewById(R.id.videoView);
		progressBar=(ProgressBar)findViewById(R.id.progressBar);
		
		setupVideo();	
	}
	
	private void setupVideo(){
		videoView.setMediaController(new MediaController(this));
		videoView.setOnCompletionListener(this);
		videoView.setOnPreparedListener(this);
		videoView.setOnErrorListener(this);
		Uri streamURI = Uri.parse(streamURL);
		videoView.setVideoURI(streamURI);
		videoView.requestFocus();
	}

	@Override
	public boolean onError(MediaPlayer mediaPlayer, int arg1, int arg2) {
		// TODO Auto-generated method stub
		progressBar.setVisibility(View.GONE);
		videoView.setVisibility(View.GONE);
		System.out.println("Can't able to play.");
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		progressBar.setVisibility(View.GONE);
		videoView.start();
		videoView.resume();
	}

	@Override
	public void onCompletion(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub		
	}
}
