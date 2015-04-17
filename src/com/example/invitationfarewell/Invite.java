package com.example.invitationfarewell;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnCreateContextMenuListener;

public class Invite extends Fragment{

	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.invite_end, container, false);
			
			 return v;	
	}

}
