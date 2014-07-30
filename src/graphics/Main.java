package graphics;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class Main extends Applet implements Runnable{

	Ship ship = new Ship(this);
	Obstacles obs = new Obstacles();
	Explosion expl = new Explosion();
	StartScreen ss = new StartScreen(this);
	Bullet bullet = null;
	Thread thread = null;
	
	public static boolean isKeyPressed = false;
	public boolean isSpacePressed = false;
	boolean isFirst = true;
	boolean done = false;
	
	 /* State variables. */
    public boolean firstScreen = true;
    public static boolean shouldRun = false;
    
	int level = 3;
	int delay;
	
	FontMetrics fontMetrics;
	Font font;
	
	String welcomeString = "Press Space to continue";
	
	
	Graphics g;
	
	public void init(){
		setSize(504,300);
		setBackground(Color.lightGray);
		setFocusable(true);
		
		font = new Font ("TimesRoman", Font.BOLD, 24);
		fontMetrics = getFontMetrics (font);
		setFont (font);

		delay = 350 - (50*level);
		
	}
	
	 /* Start a new game. */
    public synchronized void newGame (){
		thread = new Thread (this);
		thread.start ();
    }
	
	public void paint (Graphics g) {
		if(firstScreen){
			setFont (font);
		    int w = fontMetrics.stringWidth (welcomeString);
		    int x = 504/2 - w/2;
		    int y = 300/2;
		    g.setColor (Color.red);
		    g.drawString (welcomeString, x, y);
			if(isSpacePressed){
			    firstScreen = false;
			    isKeyPressed = false;
		    }
		}
		else{
			ship.update();
			ship.paint(g);
			expl.paint(g, ship);
			if(bullet != null){
				bullet.paint(g);
			}
			this.g = g;
			updateBorder(g);
			if (!shouldRun) {
				shouldRun = true;
				newGame();
			}
		}
	}
	
	/* Draw the borders. */
    public void updateBorder (Graphics g) {
    	setBackground(Color.lightGray);
    	g.setColor (Color.black);
		g.drawRect (0, 0, 504 - 1, 300 - 1);
		g.drawLine (504, 0, 504, 300);
    }
	
	@SuppressWarnings("static-access")
	public void run(){
		while(shouldRun){
			if(isKeyPressed){
				isKeyPressed = false;
				repaint();
			}
			obs.run(this);
			if(expl.isHit(obs.arrayOfBlocks, obs.isBlockUp)){
				expl.collisionAnime(this, expl.getCollidedBlock(), g);
				expl.setBlastCoordinates(ship.currX, ship.currY);
				expl.run(this);
			}
			try {
					thread.sleep(delay); //Idea! Try and use for loop of shorter times to obtain sensibility
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addComponent(Component c){
		add(c);
	}
	
	public void removeComponent(Component c){
		remove(c);
	}
	
	public void stop(){
		thread = null;
		shouldRun = false;
		clearScreen();
	}
	
	public void clearScreen(){
	    ship.erase();
	    shouldRun = false;
	}

}
