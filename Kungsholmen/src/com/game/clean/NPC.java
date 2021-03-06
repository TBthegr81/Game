package com.game.clean;

public class NPC {
	private Car car;
	@SuppressWarnings("unused")
	private int type;
	private Point location;
	boolean direction;
	
	public NPC(Car car, boolean direction)
	{
		this.car = car;
		this.direction = direction;
	}
	
	public Car getCar()
	{
		return car;
	}
	
	public void setLocation(Point location)
	{
		this.location = location;
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public boolean getDirection()
	{
		return direction;
	}
}
