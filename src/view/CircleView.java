package view;

import game.Game;

import shape.Circle;
import shape.Shape;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View  
{
	private Game game;

	public CircleView(Context context)
    {
         super(context);
         game=new Game(this);
         //bkg color
         setBackgroundColor(Color.BLACK);
        
       //  initCircleList(3);
         setOnTouchListener(new TouchListener());
    }
	
	 public Game getGame() 
	 {
			return game;
	 }
    
    protected void onDraw(Canvas canvas) 
    {
      super.onDraw(canvas);

      if(game.getLevel().getShapes().size()>0)
      {  
	      for(Shape s :game.getLevel().getShapes())
	      {
	    	  if(s instanceof  Circle)
	    	  {
	    		  //set up circle parameters
	    		  checkCircleSize((Circle)s);
	    	  }
	    	  //draw shapes
	    	  s.draw(canvas);
	      } 
	     // game.setShrink(true);
      }
   }
   
   public void checkCircleSize(Circle c)
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
				     game.getLevel().setShapes(game.poked(me.getX(pointerIndex),me.getY(pointerIndex),game.getLevel().getShapes()));
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
	    	//stop running threads
	    	game.getSc().setRun(false);
	    	game.closeThreads();
	    	
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
