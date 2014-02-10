package com.game.TB;

public class PathNode {
	private float x;
	private float y;
	
	private int nextNode;
	
	public PathNode(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void setNextNode(int nextNode)
	{
		this.nextNode = nextNode;
	}
	
	public int getNextNode()
	{
		return nextNode;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	/*
	public void calcTriangle(PathNode othernode)
	{
		float Ax = othernode.x;
		float Ay = othernode.y;
		float Bx = x;
		float By = y;
		float Cx = othernode.x;
		float Cy = othernode.y;
		
		float lengthAC = Cx - Ax;
		float lengthBC = Cy - By;
		
		int triangleGrades = 180;
		
		triangleGrades -= 90;
	}*/
}
