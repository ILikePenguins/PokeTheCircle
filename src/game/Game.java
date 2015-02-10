//package game;
//
//import shape.Circle;
//import shape.Shape;
//import sound.Sound;
//
//public class Game 
//{
//    public void poked(float x, float y)
//    {
//    	  sound= new Sound(this.getContext());
//    	//sound= new Sound(this.getContext());
//    	for(Shape s: shapes)
//    	{
//    		if(s.isPoked(x, y) && s.getType()==2)
//    		{
//    			System.out.println("poked a square");
//    		}
//    		
//    		//ensure its a circle getting poked
//
//    		if(s.isPoked(x,y) && s.getType()==1)
//    		{
//    			//playsound if its loaded
//    			sound.isLoaded();
//    			     
//    			System.out.println("poked a circle!");
//    			//remove the clicked circle, and add a new one
//    			shapes.remove(s);
//    			shapes.add(new Circle());
//    			//turn off shrinking of circles
//    			shrink=false;
//    			score++;
//    			
//    			if(score%15==0)
//    			{
//    				//make circles shrink faster
//    				sc.setSleepTime(sc.getSleepTime()-100);
//    			}
//    			if(score%20==0)
//    			{
//    				//add another circle to the game
//    				shapes.add(new Circle());
//    			}
//    			//redraw
//    			invalidate();
//    			break;
//    		}
//    	}
//    }
//}
