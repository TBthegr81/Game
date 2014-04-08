package com.game.clean;

import com.badlogic.gdx.graphics.Texture;

public class Boat {
	int width;
	int height;
	
	@SuppressWarnings("unused")
	private double weight;
	@SuppressWarnings("unused")
	private int engine;
	@SuppressWarnings("unused")
	private int turnability;
	@SuppressWarnings("unused")
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
