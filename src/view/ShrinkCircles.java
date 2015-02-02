package view;

public class ShrinkCircles implements Runnable
{

	private CircleView cv;
	private boolean run;
	private int sleepTime;
	

	public ShrinkCircles(CircleView cv)
	{
		this.cv=cv;
		run=true;
		sleepTime=3000;
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
		if(sleepTime>1500)
			this.sleepTime = sleepTime;
	}
	
	public void run() 
	{
		while(run)
		{
			cv.postInvalidate();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("invalidated");
		}
	}
}
