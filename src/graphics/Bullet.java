package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Bullet {

	int length;
	int startX; //coordinates of the starting point
	int startY;
	int x;
	
	final Color color = Color.red;
	
	public boolean paintme;
	
	public Bullet(int l, int x, int y){
		length = l;
		startX = x;
		startY = y;
		this.x = startX+length;
	}
	
	public void run(Main main){
		paintme = true;
		main.repaint();
	}
	
	public void paint(Graphics g){
		if (paintme)
		{
			g.setColor (color);
			g.drawLine (startX, startY, x, startY);
			
		    x += length;
		    startX += length;
		    
		   // g.drawLine (startX, startY, x, startY);
		    
		    if(startX >= 504){
		    	paintme = false;
		    }
			}
	}
}
