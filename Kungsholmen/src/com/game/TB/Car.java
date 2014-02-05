package com.game.TB;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Car {
	private Texture carTexture;
	private Sprite carSprite;
	
	Rectangle Car;
	
	private int carTurn;
	
	private double dirVectx;
	private double dirVecty;
	private double VeloX;
	private double VeloY;
	private double accforward = 0.001;
    
	private long previousTime = TimeUtils.millis();
	private long currentTime;
	private long dt;
    
	private int hp;
	private double weight;
    
    public void calcDT()
    {
    	currentTime = TimeUtils.millis();
    	dt = currentTime - previousTime;
    	previousTime = currentTime;
    	//System.out.println("CurrentTime: " + currentTime + " - PreviousTime: " + previousTime);
    	//System.out.println("DT: " + dt);
    }
    
    public void calcDirectionVector()
    {
    	 dirVectx = Math.cos(Math.toRadians(carTurn));
         dirVecty = Math.sin(Math.toRadians(carTurn));
         //System.out.println("DirectVelocityX: " + dirVectx + " DirectVelocityY: " + dirVecty);
    }
    
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
    
    public Car(String carname)
    {
    	carTexture = new Texture(Gdx.files.internal("Cars/" + carname + ".png"));
    	carSprite = new Sprite(carTexture);
    }
    
   public void throttle()
   {
		VeloX += accforward * dirVectx * dt;
		VeloY += accforward * dirVecty * dt;
		//System.out.println("VeloX: " + VeloX + " VeloY: " + VeloY);
		// Simulate friction
		VeloX *= .99;
		VeloY *= .99;
   }
   
   public void updatePosition()
   {
	   Car.x += VeloX * dt;
	   Car.y += VeloY * dt;
   }
   
   public Sprite getSprite()
   {
	   return carSprite;
   }
   
   public void colide()
   {
	   hp--;
	   System.out.println("Current HP: " + hp);
   }
}
