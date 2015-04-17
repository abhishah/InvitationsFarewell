package com.example.invitationfarewell;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity{
	protected boolean _active = true;
	protected int _splashTime = 1500; // time to display the splash screen in ms

	private Thread splashThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	   
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        
	    Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensed-Light.ttf");
	    	
	    splashThread = new Thread(){
	        @Override
	        public void run(){
	            try {
	                int waited = 0;
	                while (_active && (waited < _splashTime)) {
	                    sleep(100);
	                    if (_active){
	                        waited += 100;
	                    }
	                }
	            } catch (Exception e){

	            } finally {
	                finish();
	            }
	        };
	             };
	    splashThread.start();
	
	}

	@Override
	public void onBackPressed(){
		super.onBackPressed();
    	_active = false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		return false;
	}

	@Override
	protected void onStop(){
		super.onStop();
	  
	}

 }
