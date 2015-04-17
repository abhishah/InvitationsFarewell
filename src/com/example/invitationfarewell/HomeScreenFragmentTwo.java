package com.example.invitationfarewell;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.invitationfarewell.SwipeDetector.Action;


public class HomeScreenFragmentTwo extends Fragment implements OnClickListener{
	SwipeDetector swipedetector;
    EditText et_password;
    Button button;
    String password;

    private SQLiteDatabase db;
    private Cursor c;
    boolean login;
    String query;


	@Override
	public View onCreateView(LayoutInflater inflater,
		 ViewGroup container, Bundle savedInstanceState) {
		login=false;
		 View v = inflater.inflate(R.layout.home_screen_fragment_two, container, false);
        //TextView something = (TextView)v.findViewById(R.id.tv_hs2_text);
        et_password=(EditText)v.findViewById(R.id.et_hs2_password);
        button=(Button)v.findViewById(R.id.bt_hs2_submit);
        //db=SQLiteDatabase.openDatabase("InvitationFarewell",c,SQLiteDatabase.OPEN_READONLY);
        db=getActivity().openOrCreateDatabase("InvitationFarewell", android.content.Context.MODE_APPEND, null);


        swipedetector=new SwipeDetector();
        v.setOnTouchListener(swipedetector);
        v.setOnClickListener(this);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                password=et_password.getText().toString();

                /*c.newCursor(db,,"Farewell","select * from farewell where password='"+password+"'");
                */
                try {


                    query="select * from farewell where password='"+password+"'";

                    c = db.rawQuery(query, null);

                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Log.d("HS2", c.getCount() + ""+ query+password+" "+login);
                    if (c.getCount() == 0) {
                        Toast.makeText(getActivity(), "Kuchh Bhi Likhoge Ab?", Toast.LENGTH_LONG).show();
                       /* try{
                            Toast.makeText(getActivity(),c.getString(c.getColumnIndex("password")),Toast.LENGTH_LONG).show();

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }*/

                    } else {c.moveToFirst();
                       
                        if(password.trim().equalsIgnoreCase("rishikesh"))
                        {
                        	  login=true;
                        	  Toast.makeText(getActivity(), " login succesfull", Toast.LENGTH_LONG).show();
                        	  ((HomeActivity)getActivity()).flipCardRight();
                        }
                    }
                }


            }
        });
      return v;	
	}
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(login){
		 if (swipedetector.getAction() == Action.LR) {
      	   
      	Toast.makeText(getActivity(), "Swiped LR", Toast.LENGTH_LONG).show();
      	((HomeActivity)getActivity()).flipCardLeft();
      	}
		 else  if (swipedetector.getAction() == Action.RL) {
	      	   
		      	Toast.makeText(getActivity(), "Swiped LR", Toast.LENGTH_LONG).show();
		      	((HomeActivity)getActivity()).flipCardRight();
		      	}
	}}
}
