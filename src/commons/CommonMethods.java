package commons;

import graphics.Obstacles;

import java.util.Random;

public class CommonMethods {

	public static boolean getRandomBoolean(){
		Random random = new Random();
		boolean r = random.nextBoolean();
		return r;
	}
	
	public static int getRandomNumber(int level, int lastHeight, boolean lastUp, boolean thisUp){
		Random random = new Random();
		int r = -1; 
		int thisHeight;
		if(level == 1){
			r = random.nextInt(3);
		}
		else if(level == 2){
			r = random.nextInt(4);
		}
		else if(level == 3){
			r = random.nextInt(5);
		}
		
		if(r == 0){
			thisHeight = 60;
		}
		else if(r == 1){
			thisHeight = 120;
		}
		else{
			if(!Obstacles.isFirstObstacle){
				thisHeight = 180;
			}
			else{
				thisHeight = 120;
			}
		}
		/*System.out.println("thisHeight " + thisHeight);
		System.out.println("lastHeight " + lastHeight);*/
		if(thisHeight > 240 - lastHeight){
			if (lastUp != thisUp){
				thisHeight = 240 - lastHeight;
			}
		}
		return thisHeight;
	}
	
	
}
