package com.potatoes.pokethecircle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HowToPlayActivity extends Activity
{
	String exp;
	String exp2;
	String exp3;
	String tip;
	String tip2;
	int count=0;
	TextView tvHowTo;
	TextView tvTips;
	ImageButton backButton;
	ImageButton nextButton;
	Button playButton;
	ImageView image;
	  protected void onCreate(Bundle savedInstanceState)
	    {
			super.onCreate(savedInstanceState);

			setContentView(R.layout.how_to_play);
			
			//explanations
			exp="Circles will appear all over the screen," +
					"poke them as fast you can!";
			exp2="The Circles will shrink over time." +
					"Once a circle gets too small, its game over.";
			exp3="A square will appear at some point to block" +
					" your pokes. If you poke one, the game ends";
			//tips
			tip="Tip: Try to poke the smallest one first";
			tip2="How many can you poke?";
			
			//textviews
			tvHowTo = (TextView) findViewById(R.id.tvExplain);
			tvTips = (TextView) findViewById(R.id.tvTip);
			tvHowTo.setText(exp);
			
			//imageview
			image = (ImageView) findViewById(R.id.ivImage);
			
			//buttons
			nextButton = (ImageButton) findViewById(R.id.btnFw);
			nextButton.setOnClickListener(new NextListener());
			
			backButton = (ImageButton) findViewById(R.id.btnBw);
			backButton.setOnClickListener(new BackListener());
			backButton.setVisibility(View.GONE);
			
			playButton = (Button) findViewById(R.id.btnPlay);
			playButton.setOnClickListener(new PlayListener());
	    }
	  
	  
	  class PlayListener implements OnClickListener
	    {

	    	  public void onClick(View v)
	    	    {
	    		  //go back to start screen
	    		  Intent playIntent = new Intent(HowToPlayActivity.this,GameActivity.class);
	  	      	  startActivity(playIntent);
	    	    }
	    }
	  
	  class NextListener implements OnClickListener
	    {

	    	  public void onClick(View v)
	    	    {
	    		  backButton.setVisibility(View.VISIBLE);
	    		  count++;
	    		  if(count>1)
	    		  { 
	    			  nextButton.setVisibility(View.GONE);
	    			  count=2;
	    			  playButton.setVisibility(View.VISIBLE);
	    		  }
	    		  page();
	    	    }
	    }
	  
	  class BackListener implements OnClickListener
	    {

	    	  public void onClick(View v)
	    	    {
	    		nextButton.setVisibility(View.VISIBLE);
	    		 playButton.setVisibility(View.INVISIBLE);
	    		  count--;
	    		  if(count<1)
	    		  {
	    			  backButton.setVisibility(View.GONE);
	    			  count=0;
	    		  }
	    		  page();
	    	    }
	    }
	  
	  public void page()
	  {
		  //change text and imagec
		  switch(count)
		  {
		  	case 0:
		  		tvHowTo.setText(exp);
		  		tvTips.setText("");
		  		break;
		  	case 1:
		  		tvHowTo.setText(exp2);
		  		tvTips.setText(tip);
		  		break;
		  	case 2:
		  		tvHowTo.setText(exp3);
		  		tvTips.setText(tip2);
		  		image.setImageResource(R.drawable.square);
		  		break;
		  }
	  }
}
