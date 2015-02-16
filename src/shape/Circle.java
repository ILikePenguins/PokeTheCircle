package shape;

import android.graphics.Canvas;

public class Circle extends Shape
{

	private float centerX;
	private float centerY;

	private float x1;
	private float x2;
	private float y1;
	private float y2;
	static int minSize=60;
	static int maxSize=90;
	private float radius;

	public Circle()
	{
		id = nextId.incrementAndGet();
		setRandomColor();
		setRandomRadius();
		shrinkFactor = 0.9;
		type=1;
		// shrinkFactor= (double)((generator.nextInt(5)+4))/10;
		// setRandomRadius();

		randomX();
		randomY();
		
	}


	public void setRandomRadius()
	{
		radius = (int) ((Math.random() * (maxSize))) + minSize;
		//System.out.println(radius);
	}
	
	public void randomX()
	{
		// get random center x coordinate within bounds of users device
		centerX = (int) (Math.random() * ( (maxWidth- (maxSize*4)) )) + maxSize*2;
		x1=centerX-radius;
		x2=centerX+radius;
	}
	
	public void randomY()
	{
		// get random center y coordinate within bounds of users device
		centerY = (int) (Math.random()*( (maxHeight -(maxSize*4)))) +maxSize*2;
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
	
	public boolean isPoked(float x, float y)
	{
		if(x>=x1 && x<=x2 &&y>=y1 &&y<=y2)
			return true;
		
		return false;
	}
	
	public void draw(Canvas canvas)
	{
		paint.setColor(getColor());

		canvas.drawCircle(centerX,
				centerY,
				radius, paint);
		
	}

}
