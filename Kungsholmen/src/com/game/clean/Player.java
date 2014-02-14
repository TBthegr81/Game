package com.game.clean;

public class Player {
	private Point location;
	private int direction;
	private DirectionalVector dirVectorX;
	private DirectionalVector dirVectorY;
	private Car currentCar;
	private String name;
	
	public Player(Point locaction, int direction, Car startCar, String name)
	{
		this.name = name;
		this.currentCar = startCar;
		this.direction = direction;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public Car getCurrentCar()
	{
		return currentCar;
	}
	
	public void setCurrentCar(Car newCar)
	{
		currentCar = newCar;
	}
	
	public void setLocation(Point location)
	{
		this.location = location;
	}
	
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	
	public String getName()
	{
		return name;
	}
}
