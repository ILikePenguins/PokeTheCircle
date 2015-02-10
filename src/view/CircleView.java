package view;

import game.Game;

import java.util.ArrayList;


import shape.Circle;
import shape.Shape;
import shape.Square;
import android.content.Context;
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
	private Game game;
    public Game getGame() {
		return game;
	}

	public CircleView(Context context)
    {
         super(context);
         game=new Game(this);
         //bkg color
         setBackgroundColor(Color.BLACK);
        
         initCircleList(3);
        
         setOnTouchListener(new TouchListener());

       
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
	      game.setShrink(true);
      }
   }
   
   public void initCircle(Circle c)
   {
	   if(c.getRadius()<15)
 	  {
		   //circle got too small, end game
     	  System.out.println("gameover");
     	  game.gameOver();

 	  }
 	  else if(game.isShrink())
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
			    //int pointerId = me.getPointerId(pointerIndex);
			    //me.getX(pointerId);
				switch (maskedAction) 
				{

				    case MotionEvent.ACTION_DOWN: //1st finger
				    case MotionEvent.ACTION_POINTER_DOWN: //2nd finger
				    {
				    	//check if a shape was poked
				      shapes=game.poked(me.getX(pointerIndex),me.getY(pointerIndex),shapes);
				      break;
				    }
				    case MotionEvent.ACTION_UP:
				    case MotionEvent.ACTION_POINTER_UP:
				    	break;
				}
			return false;
		}
    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) 
	    {
	    	game.getSc().setRun(false);
	    	game.getAnimate().setRun(false);
	    	
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
