package com.game.clean;

public class NPC {
	private Car car;
	@SuppressWarnings("unused")
	private int type;
	
	public NPC(Car car)
	{
		this.car = car;
	}
	
	public Car getCar()
	{
		return car;
	}
}
