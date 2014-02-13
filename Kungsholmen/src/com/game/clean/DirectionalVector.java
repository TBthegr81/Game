package com.game.clean;

public class DirectionalVector {
	 double dirVectx;
	 double dirVecty;
	 
	 public DirectionalVector(double dirVectX, double dirVectY)
	 {
		 this.dirVectx = dirVectX;
		 this.dirVecty = dirVectY;
	 }
	 
	 public void setVectX(double x)
	 {
		 dirVectx = x;
	 }
	 
	 public void setVectY(double y)
	 {
		 dirVecty = y;
	 }
	 
	 public double getVectX()
	 {
		 return dirVectx;
	 }
	 
	 public double getVectY()
	 {
		 return dirVecty;
	 }
}
