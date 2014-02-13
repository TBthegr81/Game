package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Car {
	private double accforward = 0.001;
	private int hp;
	private double weight;
	private int engine;
	private int turnability;
	private Texture Texture;
	
	public Car(Texture Texture, int width, int height, int weight, int engine, int turnability)
    {
    	this.Texture = Texture;
    	//System.out.println("Width: " + width + " Height: " + height);
    	this.engine = engine;
    	this.weight = weight;
    	this.turnability = turnability;
    }
	
	public int getTurnability()
	{
		return turnability;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public Texture getTexture()
	{
		return Texture;
	}
	
	public int getEngine()
	{
		return engine;
	}
	
}
