package shape;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import android.graphics.Color;


public class Circle
{
	private static int maxWidth;
	private static int maxHeight;
	private float centerX;
	private float centerY;

	private float x1;
	private float x2;
	private float y1;
	private float y2;
	private float radius;
	private Random generator;
	static AtomicInteger nextId = new AtomicInteger();
    private long id;
    private double shrinkFactor;
    private int color;
	public Circle()
	{
		generator = new Random(); 
		id=nextId.incrementAndGet();
		
		radius= (int) ((Math.random()*(90) ))+80;
		shrinkFactor=0.8;
		//shrinkFactor= (double)((generator.nextInt(5)+4))/10;
		//setRandomRadius();
		color = Color.argb(255, (generator.nextInt(246)+10), generator.nextInt(256), generator.nextInt(256)); 
		randomX();
		randomY();
		
	}
	public int getColor() {
		return color;
	}
	public double getShrinkFactor() {
		return shrinkFactor;
	}
	public void setShrinkFactor(double shrinkFactor) {
		this.shrinkFactor = shrinkFactor;
	}
	public void setRandomRadius()
	{
		radius=(int) ((Math.random()*(120) ))+20;
		System.out.println(radius);
	}
	
	public float getX1() {
		return x1;
	}

	public float getX2() {
		return x2;
	}
	public float getY1() {
		return y1;
	}

	public float getY2() {
		return y2;
	}

	
	public void randomX()
	{
		centerX = (int) (Math.random() * ( (maxWidth- (radius*4)) )) + radius*2;
		x1=centerX-radius;
		x2=centerX+radius;
	}
	
	public void randomY()
	{
		centerY = (int) (Math.random()*( (maxHeight -(radius*4)))) +radius*2;
		y1=centerY-radius;
		y2=centerY+radius;
	}
	public float getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	
	}
	
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	
	}

	public float getCentertY() {
		return centerY;
	}



	public float getRadius() {
		return radius;
	}

	public void setRadius(float d) {
		this.radius = d;
	}

	public static int getMaxWidth() {
		return maxWidth;
	}

	public static void setMaxWidth(int maxWidth) {
		Circle.maxWidth = maxWidth;
	}

	public static int getMaxHeight() {
		return maxHeight;
	}

	public static void setMaxHeight(int maxHeight) {
		Circle.maxHeight = maxHeight;
	}
	public long getID()
	{
		return id;
	}

	


}
