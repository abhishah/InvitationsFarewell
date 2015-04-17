package com.example.invitationfarewell;

import com.example.invitationfarewell.SwipeDetector.Action;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreenOneFragment extends Fragment implements OnClickListener{
	SwipeDetector swipedetector;
	@Override
	public View onCreateView(LayoutInflater inflater,
		 ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.home_screen_fragment_one, container, false);
        TextView something = (TextView)v.findViewById(R.id.tv_hs1_text);
        swipedetector=new SwipeDetector();
        v.setOnTouchListener(swipedetector);
        v.setOnClickListener(this);
       
      return v;	
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		 if (swipedetector.getAction() == Action.RL) {
      	   //Do some action
      	
      	((HomeActivity)getActivity()).flipCardRight();
      	}
	}
}
