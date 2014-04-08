package com.game.clean;

public class Train {
	private int line;
	private Point location;
	private PathNode nextNode;
	
	public Train(int line, PathNode start)
	{
		this.line = line;
		location = start.getLocation();
	}
	
	public void setNextNode(PathNode nextNode)
	{
		this.nextNode = nextNode;
	}
	
	public PathNode getNextNode()
	{
		return nextNode;
	}
	
	public int getLine()
	{
		return line;
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public void setLocation(Point location)
	{
		this.location = location;
	}
}
