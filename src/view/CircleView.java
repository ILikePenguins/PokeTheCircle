package view;

import java.util.ArrayList;

import com.example.pokethecircle.GameOverActivity;

import shape.Circle;
import shape.Shape;
import shape.Square;
import sound.Sound;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View  
{
	private ArrayList<Shape> shapes= new ArrayList<Shape>();
	private ShrinkCircles sc;
	private boolean shrink;

	private int score;
	
	private Sound sound;
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
	    Shape.setMaxHeight(metrics.heightPixels);
        Shape.setMaxWidth(metrics.widthPixels);
        //initialize circles
    	for(int i=0;i<x;i++)
    	{
    		shapes.add(new Circle());
    	}
    	//shapes.add(new Square());
    }

    protected void onDraw(Canvas canvas) 
    {
      super.onDraw(canvas);

      if(shapes.size()>0)
      {  
	      for(Shape s :shapes)
	      {
	    	  if(s instanceof  Circle)
	    	  {
	    		  //set up circle parameters
	    		  initCircle((Circle)s);
	    		
	    	  }
	    	  //draw shapes
	    	  s.draw(canvas);
		    	  
	      } 
	      shrink=true;
      }
   }
   
   public void initCircle(Circle c)
   {
	   if(c.getRadius()<15)
 	  {
		   //circle got too small, end game
     	  System.out.println("gameover");
     	  //stop the shrink thread
     	  sc.setRun(false);
     	  //circle got too small, game is over
     	  gameOver();
 	  }
 	  else if(shrink)
	    	  c.setRadius((float) (c.getRadius()*c.getShrinkFactor()));

   }
    
    public class TouchListener implements OnTouchListener
    {

		public boolean onTouch(View v, MotionEvent me)
		{
			
			//multi-touch...hopefully
			  int pointerIndex = me.getActionIndex();

			    // get masked (not specific to a pointer) action
			    int maskedAction = me.getActionMasked();
			    int pointerId = me.getPointerId(pointerIndex);
			    //me.getX(pointerId);
				switch (maskedAction) 
				{

				    case MotionEvent.ACTION_DOWN: //1st finger
				    case MotionEvent.ACTION_POINTER_DOWN: //2nd finger
				    {
				    	//check if a shape was poked
				      poked(me.getX(pointerIndex),me.getY(pointerIndex));
				      break;
				    }
				    case MotionEvent.ACTION_UP:
				    case MotionEvent.ACTION_POINTER_UP:
				    	break;
				}
			return false;
		}
    }
    
    public void poked(float x, float y)
    {
    	  sound= new Sound(this.getContext());
    	//sound= new Sound(this.getContext());
    	for(Shape s: shapes)
    	{
    		if(s.isPoked(x, y) && s.getType()==2)
    		{
    			System.out.println("poked a square");
    		}
    		
    		//ensure its a circle getting poked

    		if(s.isPoked(x,y) && s.getType()==1)
    		{
    			//playsound if its loaded
    			sound.isLoaded();
    			     
    			System.out.println("poked a circle!");
    			//remove the clicked circle, and add a new one
    			shapes.remove(s);
    			shapes.add(new Circle());
    			//turn off shrinking of circles
    			shrink=false;
    			score++;
    			
    			if(score%15==0)
    			{
    				//make circles shrink faster
    				sc.setSleepTime(sc.getSleepTime()-100);
    			}
    			if(score%20==0)
    			{
    				//add another circle to the game
    				shapes.add(new Circle());
    			}
    			//redraw
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
    
	public boolean isShrink() {
		return shrink;
	}

	public void setShrink(boolean shrink) {
		this.shrink = shrink;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) 
	    {
	    	sc.setRun(false);
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
