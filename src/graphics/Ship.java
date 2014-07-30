package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Ship implements KeyListener{
	
	int []upperX = new int[3];
	int []upperY = new int[3];
	
	int []lowerX = new int[3];
	int []lowerY = new int[3];
	
	int []bigX = new int[3];
	int []bigY = new int[3];
	
	int rectWidth = 60;
	int rectHeight = 40;
	
	public static int currX = 0;
	public static int currY = 120;
	
	Main main;
	
	public Ship(int currX, int currY){
		Ship.currX = currX;
		Ship.currY = currY;
	}
	
	public Ship(Main main){
		currX = 0;
		currY = 120;
		this.main = main;
		main.addKeyListener(this);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(currX+5, currY+10, rectWidth, rectHeight);
		g.fillPolygon(bigX, bigY, 3);
		g.fillPolygon(upperX, upperY, 3);
		g.fillPolygon(lowerX, lowerY, 3);
	}
	
	public void update(){
		
		upperX[0] = currX;
		upperX[1] = currX+5;
		upperX[2] = currX+15;
		for (int i = 0; i < 3; i++) {
			lowerX[i] = upperX[i];
		}
		upperY[0] = currY;
		upperY[1] = currY+10;
		upperY[2] = currY+10;
		
		lowerY[0] = currY+60;
		lowerY[1] = currY+50;
		lowerY[2] = currY+50;
		
		bigX[0] = currX+65;
		bigX[1] = currX+65;
		bigX[2] = currX+80;
		
		bigY[0] = currY+10;
		bigY[1] = currY+50;
		bigY[2] = currY+30;		
	}
	
	public void keyPressed(KeyEvent e){

		//System.out.println("Key pressed");
		if(e.getKeyCode() == KeyEvent.VK_UP){
			if(currY != 0){
				currY = currY-60;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(currY != 240){
				currY = currY+60;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(currX != 400){
				currX = currX+80;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(currX != 0){
				currX = currX-80;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			main.bullet = new Bullet(80, bigX[2], bigY[2]);
			main.bullet.run(main);
			
		}
		else if (main.firstScreen){
			main.isSpacePressed = true;
			main.firstScreen = false;
			main.repaint(); 
		}
		Main.isKeyPressed = true;
	}
	
	public void run(){
		System.out.println("run");
		
	}
	
	public void erase(){
		currX = -1000;
		currY = -1000;
		rectHeight = rectWidth = 0;
		update();
		}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void keyReleased(KeyEvent e) {
		//System.out.println("Released");
		// TODO Auto-generated method stub
	}

}
