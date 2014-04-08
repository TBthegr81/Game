package com.game.clean;

public class NPC {
	private Car car;
	@SuppressWarnings("unused")
	private int type;
	private Point location;
	
	public NPC(Car car)
	{
		this.car = car;
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
}
