package shape;

import android.graphics.Canvas;
import android.graphics.Color;

public class Square extends Shape
{


	private float x1;
	private float y1;
	private int length;
	
	public Square()
	{
		length=35;
		randomCoordinates();
		type=2;
	}
	
	public void setX1(float x1) {
		this.x1 = x1;
	}

	public void setY1(float y1) {
		this.y1 = y1;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

	public void randomCoordinates()
	{
		x1=(int) (Math.random() * ( (maxWidth- (length*4)) )) + length*2;
		y1=x1+length;
	}
	
	public float getX1() {
		return x1;
	}

	public float getY1() {
		return y1;
	}

	
	public boolean isPoked(float x, float y)
	{
		if( (x>=x1 && x<=(x1+length)) && (y>=(y1-length) &&y<=y1) )
		{
			return true;
		}
		return false;
	}
	

	
	public void draw(Canvas canvas)
	{
		paint.setColor(Color.WHITE);
		canvas.drawRect(x1,x1,y1,y1,paint);
		//System.out.println("coords:"+x1+","+y1);
		
	}
}
