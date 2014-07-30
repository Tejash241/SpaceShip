package graphics;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class SpiralMonster extends Applet implements Runnable{

	private Point lastPoint = new Point(50,50);
	//private Point firstPoint = new Point (60,60);
	private Point newPoint;
	
	private Point center;
	
	// t of parametric equations for spiral
	private double theta;
	
	// How many points to draw per circle
	private static double STEPS_PER_ROTATION = 25;
	
	// Amount to add to angle at each step
	private double increment = 2*Math.PI/STEPS_PER_ROTATION;
	
	private int centerY = 60;
	
	Thread thread = new Thread(this);
	
	public void start(){
		thread.start();
	}
	
	public void init(){
		setSize(504,300);
		setBackground(Color.lightGray);
		setForeground(Color.black);
	}
	
	public void paint(Graphics g){
		System.out.println("Paint");
		center = new Point(60, centerY);
		lastPoint = center;
		theta = increment;

		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
		
		while( theta < 20*Math.PI) {
			newPoint =
				new Point((int)(center.getX() + theta * Math.cos(theta)),(int)(center.getY() + theta * Math.sin(theta)));
			g2.drawLine(lastPoint.x, lastPoint.y, newPoint.x, newPoint.y);
			theta = theta + increment;
			lastPoint = newPoint;
			if(lastPoint.x >= 162 || lastPoint.y >= 162){
				break;
			}
		}
		System.out.println("last X " + lastPoint.x);
		System.out.println("last Y " + lastPoint.y);
	}
	
	public void run(){
		while(true){
			System.out.println("Run");
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				stop();
			}
			if(centerY == 240){
				centerY = 60;
			}
			else{
				centerY += 60;
			}
			
		}
	}
}
