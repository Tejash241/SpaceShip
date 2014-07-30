package graphics;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class StartScreen extends Applet{

	public static boolean paintme = false;
	Main main = null;
	
	public StartScreen(Main main){
		this.main = main;
		paintme = true;
	}
	
	public void init(Main main){
		/*setSize(504,300);
		setBackground(Color.yellow);*/
		//addMouseListener(this);
		//main.addKeyListener(this);
	}
	
	public void paint(Graphics g, int w){
		if(paintme){
			int x = 504/2 - w/2;
		    int y = 300/2;
		    g.setColor (Color.red);
		    g.drawString (main.welcomeString, x, y);
		    paintme = false;
		}
	    //System.out.println("StartScreen Paint entered");
	}
	
	public void paint(Graphics g){
		paint(g, 240);
	}
}
