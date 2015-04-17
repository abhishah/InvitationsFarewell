package com.example.invitationfarewell;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.invitationfarewell.SwipeDetector.Action;

public class MemeActivity extends Fragment implements OnClickListener{

	ImageSwitcher imageSwitcher;
	SwipeDetector swipeDetector;
	int position;
	private int imageArra[] = {R.drawable.a_0, R.drawable.a_1, R.drawable.a_2,
		     R.drawable.a_3, R.drawable.a_4,
		     R.drawable.a_5, R.drawable.a_6,
		     R.drawable.a_7,R.drawable.a_8,R.drawable.a_9,R.drawable.a_10,R.drawable.a_11,R.drawable.a_12,R.drawable.a_13 };

	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.meme_active, container, false);
	    swipeDetector=new SwipeDetector();
	    v.setOnTouchListener(swipeDetector);
	    v.setOnClickListener(this);
	    position=0;
	    imageSwitcher = (ImageSwitcher)v.findViewById(R.id.imageSwitcher1);
	    imageSwitcher.setFactory(new ViewFactory() {
	       public View makeView() {
	    	   ImageView imageView = new ImageView(getActivity());
	    	    imageView.setBackgroundColor(0xFF000000);
	    	    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	    	    imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
	    	            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    	    return imageView;
	       }
	    });
	    imageSwitcher.setImageResource(imageArra[0]);
	    return v;
	}
	
	public void next(View view){
	    
	      position++;
	      Animation in = AnimationUtils.loadAnimation(this.getActivity(),
	      R.animator.slide_in_right);
	      Animation out = AnimationUtils.loadAnimation(this.getActivity(),
	      R.animator.slide_out_left);
	      imageSwitcher.setInAnimation(in);
	      imageSwitcher.setOutAnimation(out);
	      imageSwitcher.setImageResource(imageArra[position]);
	   }
	   public void previous(View view){
	     
	      position--;
	      Animation in = AnimationUtils.loadAnimation(this.getActivity(),
	      android.R.anim.slide_out_right);
	      Animation out = AnimationUtils.loadAnimation(this.getActivity(),
	      android.R.anim.slide_in_left);
	      imageSwitcher.setInAnimation(out);
	      imageSwitcher.setOutAnimation(in);
	      imageSwitcher.setImageResource(imageArra[position]);
	   }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(swipeDetector.getAction() == Action.RL){
			if(position<imageArra.length-1)
			next(this.getView());
			else 
				((HomeActivity)getActivity()).flipCardRight();
		}                                                                                                       
		else if(swipeDetector.getAction() == Action.LR){
			if(position>0)
			previous(this.getView());
		}
	}
}
