package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Explosion{
	
	private Component whichBlock;
	private boolean paintme = false;
	
	public int scale, size = 0;
	
	int X, Y;
	
	public void setBlastCoordinates(int x, int y){
		X = x + 30;
		Y = y + 40;
	}
	
	public void run(Main main){
		/* grow */
		
		scale = 10;
		size = 1;
		do{
			paintme = true;
			main.repaint ();
			size++;
		    try  {
		    	Thread.sleep (50);
		    }
		    catch (InterruptedException e) {}
		}
		while (size <= 100);

		/* shrink */
		scale = -10;
		do	{
			paintme = true;
		    main.repaint ();
		    scale++;
		    try  {
		    	Thread.sleep (100);
		    }
		    catch (InterruptedException e) {}
	
		}
		while (size >= 0);
	
		paintme = true;
		main.repaint ();
	}
	
	 public void paint (Graphics g, Ship ship)
	 {
			if (paintme)
			{
			    if (scale < 0)
			    {
			    	g.setColor (Color.lightGray);
			    	g.fillOval (X- size/2, Y - size/2, size, size);
			    }
		
			    size += scale;
		
			    g.setColor (Color.red);
			    g.fillOval (X - size/2, Y - size/2, size, size);
			    paintme = false;
			    ship.erase();
			}
	    }

	public boolean isHit(Component [] arrayOfBlocks, Boolean [] isBlockUp){
		for (int i = 0; i < Obstacles.thisIndex; i++) {
			Component thisBlock = arrayOfBlocks[i];
			if(Ship.currX == thisBlock.getX()){
				if(isBlockUp[i]){
					if(Ship.currY < thisBlock.getHeight()){
						System.out.println("Collided");
						whichBlock = thisBlock;
						return true;
					}
				}
				else{
					if(Ship.currY >= 300-thisBlock.getHeight()){
						System.out.println("Collided");
						whichBlock = thisBlock;
						return true;
					}
				}
				break;
			}
		}
		return false;
	}
	
	public Component getCollidedBlock(){
		return whichBlock;
	}
	
	public void collisionAnime(Main main,  Component obs, Graphics g){
		//main.removeComponent(ship);
		main.removeComponent(obs);
		g.setColor(Color.red);
		g.fillOval(Ship.currX, Ship.currY, 60, 40);
		//System.out.println("Thread should stop now");
		//main.clearScreen();
	}
}
