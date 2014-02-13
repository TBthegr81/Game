package com.game.clean;

import com.badlogic.gdx.graphics.Texture;

public class Boat {
	int width;
	int height;
	
	private double weight;
	private int engine;
	private int turnability;
	private Texture Texture;
	
	public Boat(Texture Texture, int width, int height, int weight, int engine, int turnability)
	{
		this.Texture = Texture;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.engine = engine;
		this.turnability = turnability;
	}
}
