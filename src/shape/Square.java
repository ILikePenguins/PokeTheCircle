package shape;

import android.graphics.Canvas;
import android.graphics.Color;

public class Square extends Shape
{


	private float x1;
	private float y1;
	private int length;
	private float y2;
	private float x2;

	public Square()
	{
		length=80;
		randomCoordinates();
		type=2;
	}
	
	public Square(int x1)
	{
		this.x1=x1;
		length=80;
		coordinates();
		type=2;
	}
	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}
	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
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
		x2=x1+(length);
		y2=y1+(length);
		System.out.println(x1);
	}
	
	public void coordinates()
	{
		x1=(int) (Math.random() * ( (maxWidth- (length*4)) )) + length*2;
		y1=x1+length;
		x2=x1+(length);
		y2=y1+(length);
		System.out.println(x1);
	}
	
	public float getX1() {
		return x1;
	}

	public float getY1() {
		return y1;
	}

	
	public boolean isPoked(float x, float y)
	{
		//if( (x>=x1 && x<=(x1+length)) && (y>=(y1-length) &&y<=y1) )
		//if( (x>=20 && x<=(40)) && (y>=(30) &&y<=50) )
		if( (x>=x1 && x<=(x2)) && (y>=(y1) &&y<=y2) )
		
		{
			System.out.println("poked square");
			return true;
		}
		return false;
	}
	
	public void draw(Canvas canvas)
	{
		paint.setColor(Color.WHITE);
		//canvas.drawRect(x1,x1,y1,y1,paint);
		canvas.drawRect(x1,y1,x2,y2,paint);
		//left, top, right, bottom
		//canvas.drawRect(20,30,40,50,paint);
		//System.out.println("coords:"+x1+","+y1);
		
	}
}
