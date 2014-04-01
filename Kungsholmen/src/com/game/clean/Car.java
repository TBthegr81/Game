package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Car {
	@SuppressWarnings("unused")
	private double accforward = 0.001;
	@SuppressWarnings("unused")
	private int hp;
	private double weight;
	private int engine;
	private int turnability;
	private Texture Texture;
	private Sprite Sprite;
	private String description;
	private String name;
	
	public Car(String carname, int width, int height, int weight, int engine, int turnability)
    {
    	this.Texture = new Texture(Gdx.files.internal("Cars/" + carname + ".png"));
    	Sprite = new Sprite(Texture);
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
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Sprite getSprite()
	   {
		   return Sprite;
	   }
	
}
