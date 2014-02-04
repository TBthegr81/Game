package com.game.TB;
public class Mathtest {
	
	private static int playerTurn = 20;
	private static void turnLeft()
    {
    	playerTurn -= 10;
    	if(playerTurn < 0)
    	{
    	int tempTurn = 360;
    		for(int i = playerTurn; i < 0; i++)
    		{
    			tempTurn--;
    		}
    		playerTurn = tempTurn;
    	}
    }
	
	private static void turnRight()
    {
    	playerTurn += 10;
    	if(playerTurn > 360)
    	{
    	int tempTurn = 0;
    		for(int i = playerTurn; i > 360; i--)
    		{
    			tempTurn++;
    		}
    		playerTurn = tempTurn;
    	}
    }
	
	public static void main(String[] args) {
		System.out.println("PT: " + playerTurn);
		turnLeft();
		System.out.println("PT: " + playerTurn);
		turnLeft();
		System.out.println("PT: " + playerTurn);
		turnLeft();
		System.out.println("PT: " + playerTurn);
		turnLeft();
		System.out.println("PT: " + playerTurn);
		
		turnRight();
		System.out.println("Right PT: " + playerTurn);
		turnRight();
		System.out.println("Right PT: " + playerTurn);
		turnRight();
		System.out.println("Right PT: " + playerTurn);
		turnRight();
		System.out.println("Right PT: " + playerTurn);
		turnRight();
		System.out.println("Right PT: " + playerTurn);
		turnRight();
		System.out.println("Right PT: " + playerTurn);
		
		
		
	}

}
