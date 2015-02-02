package com.example.pokethecircle;

import view.CircleView;
import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity
{
	
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       
        setContentView(new CircleView(this));
    }
    

}
