package animate;

import view.CircleView;
import android.os.SystemClock;

public class ShrinkCircles implements Runnable
{

	private CircleView cv;
	private boolean run;
	private int sleepTime;
	private boolean shrink;

	public boolean isShrink() {
		return shrink;
	}
	public void setShrink(boolean shrink) {
		this.shrink = shrink;
	}
	public ShrinkCircles(CircleView cv)
	{
		this.cv=cv;
		run=true;
		sleepTime=1000;
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
			shrink=true;
			cv.postInvalidate();
			SystemClock.sleep(sleepTime);
			
			
		}
	}
}