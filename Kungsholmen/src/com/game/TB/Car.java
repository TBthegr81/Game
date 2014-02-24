package com.game.TB;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Car {
	private Texture carTexture;
	private Sprite carSprite;
	
	Rectangle RecCar;
	
	private int carTurn;
	
	private double accforward = 0.001;
	private int hp;
	private double weight;
	private int engine;
	private int turnability;
    
    public void turn(boolean way)
    {
    	int tempTurn = 0;
    	if(way) carTurn -= 10;
    	else carTurn += 10;
    	
    	if(carTurn < 0 && way)
    	{
    		tempTurn = 360;
    		for(int i = carTurn; i < 0; i++)
    		{
    			tempTurn--;
    		}
    		carTurn = tempTurn;
    	}
    	if(carTurn > 360 && !way)
    	{
    		tempTurn = 0;
	    	for(int i = carTurn; i > 360; i--)
			{
				tempTurn++;
			}
	    	carTurn = tempTurn;
    	}
    	
    }
    
    public Car(String carname, int width, int height, int weight, int engine, int turnability)
    {
    	carTexture = new Texture(Gdx.files.internal("Cars/" + carname + ".png"));
    	carSprite = new Sprite(carTexture);
    	//System.out.println("Width: " + width + " Height: " + height);
    	this.engine = engine;
    	this.weight = weight;
    	this.turnability = turnability;
    	RecCar = new Rectangle();
    	RecCar.setHeight(height);
    	RecCar.setWidth(width);
    }

   public Sprite getSprite()
   {
	   return carSprite;
   }
   
   public int getTurnability()
   {
	   return turnability;
   }
   
   public Rectangle getRectangle()
   {
	   return RecCar;
   }
}
