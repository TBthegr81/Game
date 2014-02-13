package com.game.clean;

public class PathNode {
	private Point location;
	private int nextNode;
	private int lastNode;
	
	public PathNode(int x, int y, int nextNode)
	{
		location = new Point(x, y);
		this.nextNode = nextNode;
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public int nextNode()
	{
		return nextNode;
	}
	
	public int lastNode()
	{
		return lastNode;
	}
	
	public void setLocation(Point location)
	{
		this.location = location;
	}
	
	public void setNextNode(int nextNode)
	{
		this.nextNode = nextNode;
	}
	
	public void setLastNode(int lastNode)
	{
		this.lastNode = lastNode;
	}
}
