package com.potatoes.pokethecircle;

import java.util.HashMap;

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
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class SplashActivity extends ActionBarActivity 
{
	private InterstitialAd interstitial;
	boolean ready=false;
	
	// The following line should be changed to include the correct property id.
	private static final String PROPERTY_ID = "UA-59844251-2";

	//Logging TAG
	private static final String TAG = "MyApp";

	public static int GENERAL_TRACKER = 0;

	public enum TrackerName 
	{
		APP_TRACKER, // Tracker used only in this app.
		GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
		ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
	}
	HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		

		if(isNetworkAvailable(getApplicationContext()))
			interstitialAds();
		setContentView(R.layout.splash_screen);
		
		
		new Handler().postDelayed(new Runnable() 
		{
		    public void run() 
		    {
		    	
		    	  Intent startActivity = new Intent(SplashActivity.this, StartActivity.class);
		          startActivity(startActivity);
		    }
		}, 3500);
	
     }
    
    protected void onStart()
    {
    	 super.onStart();
    	 
    	//Get a Tracker (should auto-report)
    		//((SplashActivity) getApplicationContext()).getTracker(SplashActivity.TrackerName.APP_TRACKER);
 		getTracker(SplashActivity.TrackerName.APP_TRACKER);
    		
    }
    
    protected void onStop()
    {
    	super.onStop();
    	//Stop the analytics tracking
    	GoogleAnalytics.getInstance(this).reportActivityStop(this);

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
    
 
    synchronized Tracker getTracker(TrackerName trackerId) 
    {
    	if (!mTrackers.containsKey(trackerId)) 
    	{
    	GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
    	Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(R.xml.app_tracker)
    	: (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(PROPERTY_ID)
    	: analytics.newTracker(R.xml.global_tracker);
    	
    	mTrackers.put(trackerId, t);
    	}
    	return mTrackers.get(trackerId);
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

