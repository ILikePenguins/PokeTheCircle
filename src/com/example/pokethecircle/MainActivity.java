package com.example.pokethecircle;

import java.util.ArrayList;

import shape.Circle;
import shape.Coords;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public class MainActivity extends ActionBarActivity 
{
	//private Circle circles[] = new Circle[10];
	private Paint paint = new Paint();
	//private HashMap<Coords,Long> circleCoords= new HashMap<Coords,Long>();
	private Coords coords;
	private int radius=60;
	private ArrayList<Circle> circles= new ArrayList<Circle>();
	private MyView view;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        paint.setColor(Color.BLACK);
        
        initCircleList(10);
        view = new MyView(this);
        view.setOnTouchListener(new TouchListener());
        setContentView(view);
    }
    
    public void initCircleList(int x)
    {
	    Display display = getWindowManager().getDefaultDisplay();
	    Point size = new Point();
	    display.getSize(size);
    
	    Circle.setMaxHeight(size.y);
        Circle.setMaxWidth(size.x);
        
    	for(int i=0;i<x;i++)
    	{
    		circles.add(new Circle());
    	}
    }
    
    public class TouchListener implements OnTouchListener
    {

		public boolean onTouch(View v, MotionEvent me)
		{
			if(me.getAction()==MotionEvent.ACTION_DOWN)
			{
				//get location of click
				coords=new Coords(me.getX(),me.getY());
				//System.out.println(coords);
				pokeCircle(me.getX(),me.getY());
			}
			return false;
		}
    	
    }
    public boolean pokeCircle(float x, float y)
    {
    
    	for(Circle c: circles)
    	{
    		if(x>=c.getX1() && x<=c.getX2() &&y>=c.getY1()&&y<=c.getY2())
    		{
    			System.out.println("poked a circle!");
    			c.setDraw(false);
    			circles.remove(c);
    			view.invalidate();
    			return true;
    		}
    	}
    	
    	return false;
    }
   

    public class MyView extends View 
    {
        public MyView(Context context)
        {
             super(context);
             
        }

        protected void onDraw(Canvas canvas) 
        {
        	//draw circle
          super.onDraw(canvas);
        
          for(Circle c :circles)
          {
        	  //c=new Circle();
        	  if(c.getDraw())
        	  {
	              canvas.drawCircle(c.getCenterX(),c.getCentertY() , c.getRadius(), paint);
	             // circ.add(c);
	              //0-120 when radius is 60
	              System.out.println(c.getCenterX()+","+c.getCentertY());
	              System.out.println("id: "+c.getID());
        	  }
              
          } 
       }
    }
}

