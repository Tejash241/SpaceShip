package graphics;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Random;

import commons.CommonMethods;

public class NewObstacles{
	
	Random random = new Random();
	Thread thread = null;
	public static int thisX = 400;
	
	public static int obstacleNumber = 0; //for testing
	
	Component [] arrayOfBlocks = new Component[6];
	Boolean[] isBlockUp = new Boolean[6];
	static int thisIndex = 0;
	
	static int randomHeight = 0;
	static boolean lastUp = true;
	public static boolean isFirstObstacle = true;
	
	public void paint(Graphics g){
		g.setColor(Color.blue);
	}
	
	public void run(Main main){
			if(thisIndex >= 6){
				for (int i = 0; i < 6; i++) {
					moveBlock(main, arrayOfBlocks[i]);
				}
				adjustBlocks();
			}
			else{
				//drawBlock(main);
				for (int i = 0; i < thisIndex; i++) {
					moveBlock(main, arrayOfBlocks[i]);
				}
			}
			drawBlock(main);
			//thisX -= 80;
	}
	
	public void drawBlock(Main main){
		Button rect = new Button();
		boolean isUp = CommonMethods.getRandomBoolean();
		
		randomHeight = CommonMethods.getRandomNumber(main.level, randomHeight, lastUp, isUp);
		System.out.println("Random h " + randomHeight);
		
		if(isUp){
			rect.setBounds(thisX, 0, 50, randomHeight);
			isBlockUp[thisIndex] = true;
			lastUp = true;
		}
		else{
			rect.setBounds(thisX, 300-randomHeight, 50, randomHeight);
			isBlockUp[thisIndex] = false;
			lastUp = false;
		}
		
		rect.setBackground(Color.blue);
		rect.setEnabled(false);
		rect.setLabel(String.valueOf(obstacleNumber));
		obstacleNumber++;
		
		main.addComponent(rect);
		arrayOfBlocks[thisIndex] = rect;
		thisIndex++;
		isFirstObstacle = false;
	}
	
	public void moveBlock(Main main, Component thisBlock){
		main.removeComponent(thisBlock);
		thisBlock.setLocation(thisBlock.getX()-80, thisBlock.getY());
		if(thisBlock.getX()>=0){
			main.addComponent(thisBlock);
		}
	}
	
	public void adjustBlocks(){
		for (int i = 0; i < 6; i++) {
			if(i != 5){
				arrayOfBlocks[i] = arrayOfBlocks[i+1];
				isBlockUp[i] = isBlockUp[i+1];
			}
			else{
				arrayOfBlocks[5] = null;
			}
		}
		thisIndex = 5;
	}
}
