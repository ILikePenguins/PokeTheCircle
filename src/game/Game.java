package game;

import java.util.ArrayList;

import android.content.Intent;
import animate.Animate;
import animate.ShrinkCircles;

import com.example.pokethecircle.GameOverActivity;


import shape.Circle;
import shape.Shape;
import shape.Square;
import sound.Sound;
import view.CircleView;

public class Game 
{
	private Sound sound;
	private int score;
	private CircleView cv;
	private boolean shrink;
	private ShrinkCircles sc;
	private Animate animate;
	private Level level;
	private ArrayList<Animate> animateList;
	private ArrayList<Integer> positions;
	public Game(CircleView cv)
	{
		this.cv=cv;
		shrink=true;
		sc=new ShrinkCircles(cv);
		animateList=new ArrayList<Animate>();
		
	   // animate=new Animate(cv);
	    level=new Level(3,0);
	    
	    Thread shrinkThread = new Thread(sc);
	    shrinkThread.start();
	}
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Animate getAnimate() {
		return animate;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isShrink() {
		return shrink;
	}
	public void setShrink(boolean shrink) {
		this.shrink = shrink;
	}
	
	public ShrinkCircles getSc() {
		return sc;
	}
    public ArrayList<Shape> poked(float x, float y, ArrayList<Shape> shapes)
    {
    	for(int i=shapes.size()-1;i>-1;i--)
    	//for(Shape s: shapes)
    	{
    		if(shapes.get(i).getType()==2 && shapes.get(i).isPoked(x,y))
    		{
    			System.out.println("poked a square");
    			gameOver();
    		}
    		
    		//ensure its a circle getting poked

    		if( shapes.get(i).getType()==1 && shapes.get(i).isPoked(x,y))
    		{
    			//playsound if its loaded
    			sound= new Sound(cv.getContext());
    			sound.isLoaded();
    			     
    			//remove the clicked circle, and add a new one
    			shapes.remove(shapes.get(i));
    			shapes.add(0,new Circle());
    			//turn off shrinking of circles
    			shrink=false;
    			score++;
    			
    			if(score%15==0)
    			{
    				//make circles shrink faster
    				sc.setSleepTime(sc.getSleepTime()-100);
    			}
    			if(score%22==0)
    			{
    				//add another circle to the game
    				shapes.add(0,new Circle());
    			}
    			
    			if(score==35)
    			{
    				addSquare(0,Shape.getMaxHeight()/2);

    			}
    			if(score==70)
    			{
    				addSquare(1,Shape.getMaxHeight()/2);
    			}
    			
    			if(score==90)
    			{
    				addSquare(2,0);
    			}
    			if(score==100)
    			{
    				animate.setIncrement(20);
    			}
    			
    			//redraw
    			cv.postInvalidate();
    			shrink=true;
    			return shapes;
    		}
    	}
		return shapes;
    }
    
    public void addSquare(int direction, int start_pos)
    {
    	//move square back and forth
    	Square square=new Square(start_pos);
    	
    	level.getShapes().add(square);
    //	positions.add(level.getShapes().size()-1);
    	//animate.setSquare(square);
    	animateList.add(new Animate(cv,square,direction));
 	    Thread animateThread = new Thread(animateList.get(animateList.size()-1));
 	    
 	    animateThread.start();
    }
    
    public void closeThreads()
    {
    	if(animateList.size()>0)
    	{
    		for(Animate a: animateList)
    		{
    			a.setRun(false);
    		}
    	}
    }
    
    public void gameOver()
    {
    	//stop running threads
    	sc.setRun(false);
    	closeThreads();
    	
    	
    	//start game over activity
    	Intent gameOver = new Intent(cv.getContext(),GameOverActivity.class);
    	gameOver.putExtra("score",score+"");
    	System.out.println("score: "+ score);
      	cv.getContext().startActivity(gameOver);
    }
}
