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
    	  sound= new Sound(cv.getContext());
    	for(Shape s: shapes)
    	{
    		if(s.getType()==2 && s.isPoked(x, y)  )
    		{
    			System.out.println("poked a square");
    			gameOver();
    		}
    		
    		//ensure its a circle getting poked

    		if( s.getType()==1&& s.isPoked(x,y))
    		{
    			//playsound if its loaded
    			sound.isLoaded();
    			     
    			//remove the clicked circle, and add a new one
    			shapes.remove(s);
    			shapes.add(new Circle());
    			//turn off shrinking of circles
    			shrink=false;
    			score++;
    			
    			if(score%15==0)
    			{
    				//make circles shrink faster
    				sc.setSleepTime(sc.getSleepTime()-100);
    			}
    			if(score%25==0)
    			{
    				//add another circle to the game
    				shapes.add(new Circle());
    			}
    			
    			if(score==35)
    			{
    				addSquare(0);

    			}
    			if(score==70)
    			{
    				addSquare(1);
    			}
    			
    			if(score==90)
    			{
    				addSquare(2);
    			}
    			
    			//redraw
    			cv.postInvalidate();
    			shrink=true;
    			return shapes;
    		}
    	}
		return shapes;
    }
    
    public void addSquare(int direction)
    {
    	//move square back and forth
    	Square square=null;
    	if(direction==0 || direction ==1 )
    	{
    		square=new Square(Shape.getMaxHeight()/2);
    	}
    	else if(direction ==2)
    	{
    		square=new Square(0);
    	}
    	level.getShapes().add(square);
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
