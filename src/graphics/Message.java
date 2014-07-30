package graphics;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class Message extends Applet implements Runnable{
	    private String message;
	    private int blinks;
	    private int delay;
	    private int blinkCount;
	    
	   // Main parent = new Main();
	    int X, Y;
	    Color color;

	    Thread thread;
	    /*Message (Main parent, String message, int x, int y, int blinks, int delay){
		
			this.parent = parent;
			this.message = message;
			X = x;
			Y = y;
			this.blinks = blinks;
			this.delay = delay;
			color = Color.black;
	    }

	    Message (Main parent, String message, int x, int y, int blinks)    {
	    	this (parent, message, x, y, blinks, 500); // if not mentioned, delay = 0.5 seconds
	    }

	    Message (Main parent, String message, int x, int y)
	    {
	    	this (parent, message, x, y, 3, 500); // if not mentioned, no.of blinks = 3
	    }*/

	    public void init(){ // for testing only
	    	setBackground(Color.lightGray);
	    	setSize(600, 600);
	    	message = "Hello";
	    	blinks = 3;
	    	delay = 500;
	    	X = Y = 300;
	    }
	    
	    public void run ()
	    {
			//Thread.currentThread().setPriority (Thread.MIN_PRIORITY);
			for (blinkCount = 0; blinkCount < 2*blinks; blinkCount++){
		
			    repaint();
			    try 
			    {
			    	Thread.sleep (delay);
			    }
			    catch (InterruptedException e){}
			}
		
			repaint ();
	    }

	    public void paint (Graphics g){
			if ((blinkCount % 2) == 0)
			{
			    g.setColor (color);
			}
			else
			{
			    g.setColor (Color.lightGray);
			}
			g.drawString (message, X, Y);
	    }
	    
	    public void start(){
	    	thread = new Thread(this);
	    	thread.start();
	    }

	    public void erase (Graphics g){
			g.setColor (Color.lightGray);
			g.drawString (message, X, Y);
	    }
}
