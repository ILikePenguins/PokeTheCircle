package com.potatoes.pokethecircle;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SplashActivity extends ActionBarActivity 
{
	private InterstitialAd interstitial;
	boolean ready=false;
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		if(isNetworkAvailable(getApplicationContext()))
			interstitialAds();
		new Handler().postDelayed(new Runnable() {
		    public void run() 
		    {
		    	  Intent startActivity = new Intent(SplashActivity.this, StartActivity.class);
		          startActivity(startActivity);
		    }
		}, 3000);
     	}
    
    public class Watch implements Runnable
    {
    	
    	long start= System.currentTimeMillis();
    	SplashActivity activity;
    	public Watch(SplashActivity activity)
    	{
    		this.activity=activity;
    		
    	}
		public void run() 
		{
			System.out.println("start");
			while(System.currentTimeMillis()-start<10000)
			{
				//System.out.println(System.currentTimeMillis()-start);
			}
			System.out.println("end");
			ready=true;
		}
    	
    }
    
    public void interstitialAds()
    {
        
     // Prepare the Interstitial Ad
     		interstitial = new InterstitialAd(this);
     		// Insert the Ad Unit ID
     		interstitial.setAdUnitId("ca-app-pub-2553845214307469/6008808239");
     		// Request for Ads
     		AdRequest adRequest = new AdRequest.Builder().build();
     		// Add a test device to show Test Ads
     		 //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
     		 //.addTestDevice("AA5F7FB3C7F9AB43984003D0C8004957")
     				//.build();
     		
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
			{
				interstitial.show();
				//start();
		}
		
	}
	
    private static boolean isNetworkAvailable(Context  context) 
    {
        ConnectivityManager connectivityManager 
             = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
    
    public void start()
    {
    	
    }
    
  
//    public static boolean hasActiveInternetConnection(Context context) 
//    {
//        if (isNetworkAvailable(context)) 
//        {
//            try 
//            {
//            	
//                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
//                urlc.setRequestProperty("User-Agent", "Test");
//                urlc.setRequestProperty("Connection", "close");
//                urlc.setConnectTimeout(1500); 
//                urlc.connect();
//                System.out.println("afssssssss");
//                return (urlc.getResponseCode() == 200);
//            } 
//            catch (IOException e)
//            {
//                e.printStackTrace();//Log.e(LOG_TAG, "Error checking internet connection", e);
//            }
//        } 
//        else 
//        {
//        	System.out.println("no network");
//           // Log.d(LOG_TAG, "No network available!");
//        }
//        return false;
//    }
  
}

