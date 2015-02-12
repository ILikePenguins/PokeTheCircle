package animate;

import shape.Shape;
import shape.Square;
import view.CircleView;
import android.os.SystemClock;

public class Animate implements Runnable
{
	
	int startx;
	int starty;
	int endx;
	int endy;
	private CircleView cv;
	private Square square;


	private boolean forward;
	private boolean run;
	

	public Animate(CircleView cv, Square square)
	
	{
		 this.cv=cv;
		 endx=Shape.getMaxWidth()-35;
		 endy=Shape.getMaxWidth();
		 
		 this.square=square;
		 square.setX1(0);
		 square.setY1(55);
		 forward=true;
		 run=true;
	}
	
public Animate(CircleView cv)
	
	{
		 this.cv=cv;
		 run=true;
		 forward=true;
	}
	
	public Square getSquare() {
		return square;
	}



	public void setSquare(Square square) {
		this.square=square;
		 square.setX1(0);
		 square.setY1(55);
		 endx=Shape.getMaxWidth()-35;
		 endy=Shape.getMaxWidth();
	}
	

	public boolean isRun() {
		return run;
	}
	
	public void setRun(boolean run) {
		this.run = run;
	}
	
	public void moveForwad()
	{
		//System.out.println("x1: " +square.getX1()+" end:"+endx);
		while(square.getX1()<endx)
		{
			//System.out.println("forward");
			square.setX1(square.getX1()+10);
			square.setY1(square.getY1()+10);
			cv.getGame().setShrink(false);
			cv.postInvalidate();
			SystemClock.sleep(100);
			forward=false;
		}
	}
	
	public void moveBack()
	{
		while(square.getX1()>0)
		{
			//System.out.println("back");
			square.setX1(square.getX1()-10);
			square.setY1(square.getY1()-10);
			cv.getGame().setShrink(false);
			cv.postInvalidate();
			SystemClock.sleep(100);
			forward=true;
		}
	}

	public void run() 
	{
		System.out.println("starting");
		while(run)
		{
			if(forward)
				moveForwad();
			else
				moveBack();
		}
	}

}
