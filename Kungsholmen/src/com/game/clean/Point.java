package com.game.clean;

public class Point {
	private float x;
	private float y;
	
	public Point(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float x()
	{
		return x;
	}
	
	public float y()
	{
		return y;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public String toString()
	{
		return "X: " + x + " Y: " + y;
	}
}
