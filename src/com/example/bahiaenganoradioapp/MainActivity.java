package com.example.bahiaenganoradioapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends Activity
{
  private ImageButton btn;
  
  private View.OnClickListener pausePlay = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
    	// use this to start and trigger a service
    	Intent i= new Intent(getApplicationContext(), MyRadioService.class);
    	//put extra can be done here
    	getApplicationContext().startService(i); 
    }
      
  };
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_main);
    this.btn = ((ImageButton)findViewById(R.id.act_play_button));
    
    this.btn.setOnClickListener(this.pausePlay);
  }

  
}