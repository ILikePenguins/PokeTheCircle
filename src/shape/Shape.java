package shape;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public abstract class  Shape 
{
	 static int maxWidth;
	 static int maxHeight;
	 int color;
	 double shrinkFactor;
	 Random generator = new Random();
	 static AtomicInteger nextId = new AtomicInteger();
	 long id;
	 int type;
	 protected Paint paint = new Paint();
	public int getType() {
		return type;
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
	public static int getMaxWidth() {
		return maxWidth;
	}

	public static void setMaxWidth(int maxWidth) {
		Shape.maxWidth = maxWidth;
	}

	public static int getMaxHeight() {
		return maxHeight;
	}

	public static void setMaxHeight(int maxHeight) {
		Shape.maxHeight = maxHeight;
	}
	public long getID()
	{
		return id;
	}
	
	public boolean isPoked(float x, float y)
	{
		
		return false;
	}
	
	public void setRandomColor()
	{
		color = Color.argb(255, (generator.nextInt(246) + 10),
				generator.nextInt(256), generator.nextInt(256));
	}
	
	public void draw(Canvas canvas)
	{
		
	}
}
