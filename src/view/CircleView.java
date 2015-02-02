package view;

import java.util.ArrayList;

import com.example.pokethecircle.GameOverActivity;

import shape.Circle;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View 
{
	private Paint paint = new Paint();
	private ArrayList<Circle> circles= new ArrayList<Circle>();
	private ShrinkCircles sc;
	private boolean shrink;
	private int score;
	
    public CircleView(Context context)
    {
    
         super(context);
         //bkg color
         setBackgroundColor(Color.BLACK);
         
         initCircleList(3);
         
         setOnTouchListener(new TouchListener());
         //shrink thread
         sc=new ShrinkCircles(this);
         Thread shrinkThread = new Thread(sc);
         shrinkThread.start();
         
         shrink=true;
         score=0;
    }
    
    public void initCircleList(int x)
    {
    	//get screen size of device
    	DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
	    Circle.setMaxHeight(metrics.heightPixels);
        Circle.setMaxWidth(metrics.widthPixels);
        //initialize circles
    	for(int i=0;i<x;i++)
    	{
    		circles.add(new Circle());
    	}
    }

    protected void onDraw(Canvas canvas) 
    {
    	//draw circle
      super.onDraw(canvas);
    
      if(circles.size()>0)
      {  
	      for(Circle c :circles)
	      {
	    	  paint.setColor(c.getColor());
	    	  
	    	  if(c.getRadius()<15)
	    	  {
	    		  //circles.remove(c);
	    		  
	        	  System.out.println("gameover");
	        	  sc.setRun(false);
	        	  gameOver();
	    	  }
	    	  else if(shrink)
	    	  {
		    	  c.setRadius((float) (c.getRadius()*c.getShrinkFactor()));
		          
		          //0-120 when radius is 60
	    	  }
	    	  canvas.drawCircle(c.getCenterX(),c.getCentertY() , c.getRadius(), paint);
	    	  //System.out.println(c.getCenterX()+","+c.getCentertY()+"  "+c.getRadius());
	    	  shrink=true;
	      } 
      }
   }
    
    
    public class TouchListener implements OnTouchListener
    {

		public boolean onTouch(View v, MotionEvent me)
		{
			if(me.getAction()==MotionEvent.ACTION_DOWN)
			{
				//get location of click
				pokeCircle(me.getX(),me.getY());
			}
			return false;
		}
    	
    }
    public void pokeCircle(float x, float y)
    {
    
    	for(Circle c: circles)
    	{
    		if(x>=c.getX1() && x<=c.getX2() &&y>=c.getY1()&&y<=c.getY2())
    		{
    			System.out.println("poked a circle!");
    			//remove the clicked circle, and redraw
    			circles.remove(c);
    			circles.add(new Circle());
    			
    			shrink=false;
    			score++;
    			if(score%15==0)
    			{
    				//make circles shrink faster
    				sc.setSleepTime(sc.getSleepTime()-200);
    			}
    			if(score%20==0)
    			{
    				//add another circle to the game
    				circles.add(new Circle());
    			}
    			invalidate();
    			break;
    		}
    	}
    	
    }
    
    public void gameOver()
    {
    	Intent gameOver = new Intent(this.getContext(),GameOverActivity.class);
    	gameOver.putExtra("score",score+"");
    	System.out.println("score: "+ score);
      	this.getContext().startActivity(gameOver);
    }
 
}