package com.example.bahiaenganoradioapp;

import java.io.IOException;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyRadioService extends Service {
	 MediaPlayer mediaPlayer;
	
	
	 @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		 Log.v("service", String.valueOf(startId));
		 if(startId == 1){
			 mediaPlayer = new MediaPlayer();
			 mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//see later
			 
			 new Player().execute(new String[] { "http://67.23.249.51:9980/" });
		 }else{
			 if(startId % 2 == 0){
				 mediaPlayer.pause();
			 }else{
				 mediaPlayer.start();
			 }
		 }
	    return Service.START_NOT_STICKY;
	  }
	 
	 /**
	  * preparing mediaplayer will take sometime 
	  * to buffer the content so prepare it inside the background 
	  * thread and starting it on UI thread.
	  * @author piyush
	  *
	  */

	 class Player extends AsyncTask<String, Void, Boolean> {
	     
	     @Override
	     protected Boolean doInBackground(String... params) {
	         // TODO Auto-generated method stub
	         Boolean prepared;
	         try {
	        	 
	             mediaPlayer.setDataSource(params[0]);

	             mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

	                 @Override
	                 public void onCompletion(MediaPlayer mp) {
	                     // TODO Auto-generated method stub
	                     
	                     mediaPlayer.stop();
	                     mediaPlayer.reset();
	                 }
	             });
	             mediaPlayer.prepare();
	             prepared = true;
	         } catch (IllegalArgumentException e) {
	             // TODO Auto-generated catch block
	             Log.d("IllegarArgument", e.getMessage());
	             prepared = false;
	             e.printStackTrace();
	         } catch (SecurityException e) {
	             // TODO Auto-generated catch block
	             prepared = false;
	             e.printStackTrace();
	         } catch (IllegalStateException e) {
	             // TODO Auto-generated catch block
	             prepared = false;
	             e.printStackTrace();
	         } catch (IOException e) {
	             // TODO Auto-generated catch block
	             prepared = false;
	             e.printStackTrace();
	         }
	         return prepared;
	     }

	     @Override
	     protected void onPostExecute(Boolean result) {
	         // TODO Auto-generated method stub
	         super.onPostExecute(result);
	         
	         Log.d("Prepared", "//" + result);
	         mediaPlayer.start();
	     }

	     public Player() {
	     }

	     @Override
	     protected void onPreExecute() {
	         // TODO Auto-generated method stub
	         super.onPreExecute();
	         Toast.makeText(getApplicationContext(), "Buffering", Toast.LENGTH_SHORT).show();

	     }
	 }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}