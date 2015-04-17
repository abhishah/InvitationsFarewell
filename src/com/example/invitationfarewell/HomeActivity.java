package com.example.invitationfarewell;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;



public class HomeActivity extends ActionBarActivity {

	FragmentManager fManager;
	FragmentTransaction fTranslation;
	HomeScreenOneFragment fragment_one;
	HomeScreenFragmentTwo fragment_two;
	MemeActivity meme;
	Invite invite;
	int pos;
	
	 private SQLiteDatabase db;
	    private Cursor c;
	    private String password="rishikesh";


	/*
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
*/
	
	SwipeDetector swipedetector;
	
	 boolean mShowingBack = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.home_activity);
		fragment_one = new HomeScreenOneFragment();
		pos=0;
		fragment_two = new HomeScreenFragmentTwo();
		meme=new MemeActivity();
		invite=new Invite();
		fManager = getFragmentManager();
		fTranslation = fManager.beginTransaction();
		fTranslation.replace(R.id.home_fragment_container,fragment_one);
		//fTranslation.addToBackStack(null);
		fTranslation.commit();
		
		 db=openOrCreateDatabase("InvitationFarewell",MODE_APPEND,null);
	        db.execSQL("create table if not exists farewell (password text)");
	        c=db.rawQuery("select * from farewell",null);
		
	        if(c.getCount()==0)
	        {
	            ContentValues cv=new ContentValues();
	            cv.put("password",password);
	            db.insert("farewell", null, cv);
	            c=db.rawQuery("select * from farewell",null);
	            
	        }
	        else
	        {
	            Log.d("MainActivity","Password in the database "+c.getCount());
	        }

	        db.close();
	/*	
		// Gesture detection
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
		*/
	}
	
public void flipCardLeft(){
    	if (mShowingBack){
           if(pos>0){ getFragmentManager().popBackStack();
            mShowingBack = true;
            pos--;}
            Log.i("in","in");
            return;
        }
}

public void flipCardRight(){
	
	
            pos++;
            mShowingBack = true;
            fTranslation = fManager.beginTransaction();
      	    fTranslation.setCustomAnimations(
                    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                    R.animator.card_flip_left_in, R.animator.card_flip_left_out);
            Log.i("out","out");
            if(pos==1){
            fTranslation.replace(R.id.home_fragment_container, fragment_two);
            fTranslation.addToBackStack(null);
            fTranslation.commit();}
            if(pos==2)
            {  fTranslation.replace(R.id.home_fragment_container, meme);
            fTranslation.addToBackStack(null);
            fTranslation.commit();
            }
            if(pos==3){
                fTranslation.replace(R.id.home_fragment_container, invite);
                fTranslation.addToBackStack(null);
                fTranslation.commit();}
            else {}
            
   
}
/*class MyGestureDetector extends SimpleOnGestureListener {
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // right to left swipe
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(HomeActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                flipCard();
            	Toast.makeText(HomeActivity.this, "Right Swipe", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // nothing
        }
        return false;
    }

   @Override
    public boolean onDown(MotionEvent e) {
          return true;
    }
  }
//MyGesture Ends
*/

	
}
