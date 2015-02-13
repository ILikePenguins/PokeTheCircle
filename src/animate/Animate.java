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
	private int direction;
	

	//0 horizontal
	//1 vertical
	//2 diagonal
	public Animate(CircleView cv, Square square,int direction)
	
	{
		 this.cv=cv;
		 this.square=square;
		 this.direction=direction;
		 endx=Shape.getMaxWidth()-35;
		 endy=Shape.getMaxWidth();
		 
		
		 //square.setX1(0);
		 //square.setY1(55);
		 forward=true;
		 run=true;
		 
		 
	}
	
public Animate(CircleView cv)
	
	{
		 this.cv=cv;
		 run=true;
		 forward=true;
	}
public void setDirection(int direction) {
	this.direction = direction;
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
		while(square.getX1()<endx &&run)
		{
			//System.out.println("forward");
			switch(direction)
			{
				case 0:
					square.setX1(square.getX1()+10);
					square.setX2(square.getX2()+10);
					break;
				case 1:
					square.setY1(square.getY1()+10);
					square.setY2(square.getY2()+10);
					break;
				case 2:
					square.setX1(square.getX1()+10);
					square.setY1(square.getY1()+10);
					square.setX2(square.getX2()+10);
					square.setY2(square.getY2()+10);
					break;
			}
			
			
			cv.getGame().setShrink(false);
			cv.postInvalidate();
			SystemClock.sleep(100);
			forward=false;
		}
	}
	
	public void moveBack()
	{
		while(square.getX1()>0 && run)
		{
			//System.out.println("back");
			switch(direction)
			{
				case 0:
					square.setX1(square.getX1()-10);
					square.setX2(square.getX2()-10);
					break;
				case 1:
					square.setY1(square.getY1()-10);
					square.setY2(square.getY2()-10);
					break;
				case 2:
					square.setX1(square.getX1()-10);
					square.setY1(square.getY1()-10);
					square.setX2(square.getX2()-10);
					square.setY2(square.getY2()-10);
					break;
			}
			
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
