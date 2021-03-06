package game;

import java.util.ArrayList;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import shape.Circle;
import shape.Shape;
import shape.Square;

public class Level 
{
	//make a timer that counts down
	private static int num_level=1;
	private int num_circles;
	private int num_squares;
	private ArrayList<Shape> shapes;
	
	public Level()
	{
		
	}

	public Level(int num_circles, int num_squares)
	{
		
		this.num_circles=num_circles;
		this.num_squares=num_squares;
		
    	//get screen size of device
    	DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
	    Shape.setMaxHeight(metrics.heightPixels);
        Shape.setMaxWidth(metrics.widthPixels);
        
		shapes= new ArrayList<Shape>();
		initShapeList();
	}
	
	public static int getLevel_number() {
		return num_level;
	}

	public static void setNum_level(int level_number) {
		Level.num_level = level_number;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	
	public void initShapeList()
    {
		shapes.clear();
        //initialize circles
    	for(int i=0;i<num_circles;i++)
    	{
    		shapes.add(new Circle());
    	}
    	for(int i=0;i<num_squares;i++)
    	{
    		shapes.add(new Square());
    	}

    }
	
	public void pause()
	{
		// make pop up for new level
	}
	
	public void levelUp()
	{
		num_level++;
		pause();
		switch(num_level)
		{
			case 2:// new circle added
				num_circles++;
				break;
			case 3: //speed up shrinking
				break;
			case 4: //new circle added
				num_circles++;
				break;
			case 5: //non moving square
				break;
			case 6://speed up
				break;
			case 7: //square moves
				break;
			case 8: // more circles
				num_circles++;
				break;
		}
		initShapeList();
	}

}
