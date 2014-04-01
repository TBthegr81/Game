package com.game.clean;

public class Player {
	private Point location;
	//private int direction;
	//private DirectionalVector dirVectorX;
	//private DirectionalVector dirVectorY;
	private Car currentCar;
	private String name;
	//keyboard controls
    boolean leftHeld = false, rightHeld = false;
    boolean upHeld = false, downHeld = false;
	//vehicle controls
    @SuppressWarnings("unused")
    private float steering = 0; //-1 is left, 0 is center, 1 is right
    @SuppressWarnings("unused")
    private float throttle = 0; //0 is coasting, 1 is full throttle
    @SuppressWarnings("unused")
    private float brakes = 0; //0 is no brakes, 1 is full brakes
	
	public Player(Point locaction, int direction, Car startCar, String name)
	{
		this.name = name;
		this.currentCar = startCar;
		//this.direction = direction;
	}
	
	public Player()
	{
		
	}
	
	/*public int getDirection()
	{
		return direction;
	}*/
	
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
	
	/*public void setDirection(int direction)
	{
		this.direction = direction;
	}*/
	
	public String getName()
	{
		return name;
	}
	
	//process keyboard input
    public void ProcessInput()
    {
        if (leftHeld)
            steering = -1;
        else if (rightHeld)
            steering = 1;
        else
            steering = 0;

        if (upHeld)
            throttle = 1;
        else
            throttle = 0;

        if (downHeld)
            brakes = 1;
        else
            brakes = 0;
    }
}
