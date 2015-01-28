package shape;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Circle
{
	private static int maxWidth;
	private static int maxHeight;
	private int centerX;
	private int centerY;

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int radius;
	private Random generator;
	static AtomicInteger nextId = new AtomicInteger();
    private long id;
    private Coords coords;
    private Coords center;
    boolean draw;
	public Circle()
	{
		radius=60;
		generator = new Random(); 
		randomX();
		randomY();
		id=nextId.incrementAndGet();
		coords=new Coords();
		center= new Coords();
		draw=true;
	}
	public boolean getDraw()
	{
		return draw;
	}
	public void setDraw(boolean draw)
	{
		this.draw=draw;
	}
	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}
	public int getY1() {
		return y1;
	}

	public int getY2() {
		return y2;
	}

	
	public void randomX()
	{
		centerX = generator.nextInt(maxWidth- (radius*2)) + radius*2;
		x1=centerX-radius;
		x2=centerX+radius;
	}
	
	public void randomY()
	{
		centerY = generator.nextInt(maxHeight -(radius*2)) + radius*2;
		y1=centerY-radius;
		y2=centerY+radius;
	}
	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
		center.setX(centerX);
	
	}
	
	public void setCenterY(int centerY) {
		this.centerY = centerY;
		center.setY(centerY);
	
	}

	public int getCentertY() {
		return centerY;
	}



	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
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

	public Coords getCenter()
	{
		return center;
	}


	public Coords getCoords() {
		return coords;
	}



	public void setCoords(Coords coords) {
		this.coords = coords;
	}


}
