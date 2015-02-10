package com.example.pokethecircle;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class StartActivity extends ActionBarActivity 
{
	private InterstitialAd interstitial;
	
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		//interstitialAds();

		Button startButton = (Button) findViewById(R.id.btnStart);
		startButton.setOnClickListener(new StartListener());

		TextView score = (TextView) findViewById(R.id.tvHowTo);
		score.setText("Many circles will appear on the screen,"
				+ "\ntry to poke them before they shrink and disappear!"
				+ "\nBe careful though, as time goes on,"
				+ "\nthe circles will call their circle buddies and shrink "
				+ "even faster." + "\n\nCan you poke them all?");
     	}
    
    public void interstitialAds()
    {
        
     // Prepare the Interstitial Ad
     		interstitial = new InterstitialAd(this);
     		// Insert the Ad Unit ID
     		interstitial.setAdUnitId("ca-app-pub-123456789/123456789");
     		// Request for Ads
     		AdRequest adRequest = new AdRequest.Builder()
     		// Add a test device to show Test Ads
     		 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
     		 .addTestDevice("AA5F7FB3C7F9AB43984003D0C8004957")
     				.build();
     		
     		// Load ads into Interstitial Ads
     		interstitial.loadAd(adRequest);
      
     		// Prepare an Interstitial Ad Listener
     		interstitial.setAdListener(new AdListener() 
     		{
     			public void onAdLoaded()
     			{
     				displayInterstitial();
     			}
     		});
     		
    }
    

	public void displayInterstitial() 
	{
		// If Ads are loaded, show Interstitial else show nothing.
		if (interstitial.isLoaded()) 
			interstitial.show();
		
	}
	    
    class StartListener implements OnClickListener
    {

    	  public void onClick(View v)
    	    {
    		  Intent gameIntent = new Intent(StartActivity.this,GameActivity.class);
  	      	  startActivity(gameIntent);
    	    }
    }
  
}

