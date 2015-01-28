package shape;

public class Coords 
{
	private float x;
	private float y;
	
	public Coords()
	{
		
	}
	public Coords(float x,float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public float getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public String toString() {
		return "Coords [x=" + x + ", y=" + y + "]";
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
