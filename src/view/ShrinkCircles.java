package view;

import android.os.SystemClock;

public class ShrinkCircles implements Runnable
{

	private CircleView cv;
	private boolean run;
	private int sleepTime;
	

	public ShrinkCircles(CircleView cv)
	{
		this.cv=cv;
		run=true;
		sleepTime=950;
	}
	public void setRun(boolean run)
	{
		this.run=run;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime)
	{
		if(sleepTime>200)
			this.sleepTime = sleepTime;
	}
	
	public void run() 
	{
		while(run)
		{
			//System.out.println("invalidated");
			//cv.setShrink(true);
			cv.postInvalidate();
			SystemClock.sleep(sleepTime);
			
			
		}
	}
}
