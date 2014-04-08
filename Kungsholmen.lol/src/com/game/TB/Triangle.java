package com.game.TB;

public class Triangle {
	private Point a;
	private Point b;
	private Point c;
    private double ax;
    private double ay;
    private double bx;
    private double by;
    private double cx;
    private double cy;
    //added these variables because I use them so frequently when calculating angles, area, perimeter, etc.
    private double sideAB;
    private double sideBC;
    private double sideAC;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3)
    {
    	a = new Point(x1, y1);
    	b = new Point(x2, y2);
    	c = new Point(x3, y3);
        ax = x1;
        ay = y1;
        bx = x2;
        by = y2;
        cx = x3;
        cy = y3;
        sideAB= Math.abs(Math.sqrt(Math.pow(bx-ax,  2)+Math.pow(by-ay, 2)));
        sideBC= Math.abs(Math.sqrt(Math.pow(cx-bx,  2)+Math.pow(cy-by, 2)));
        sideAC= Math.abs(Math.sqrt(Math.pow(cx-ax,  2)+Math.pow(cy-ay, 2)));
    }
    public double getPerimeter()
    {
        //add the 3 sides together for the perimeter
        double perimeter = sideAB + sideBC + sideAC;
        return perimeter;
    }
    public double getArea()
    {
        //used Heron's formula to find the area of the triangle
        double s = (sideAB + sideBC + sideAC)/2;
        double area = Math.sqrt(s*(s - sideAB)*(s - sideBC)*(s - sideAC));
        return area;
    }
    public double getSideAB()
    {
        return sideAB;
    }
    public double getSideBC()
    {
        return sideBC;
    }
    public double getSideAC()
    {
        return sideAC;
    }
    public double getAngleC()
    {
        //Law of cosines to find the angle
        double a2 = Math.pow(sideAB, 2);
        double b2 = Math.pow(sideBC, 2);
        double c2 = Math.pow(sideAC, 2);
        double cosC = ((b2 + c2)-a2)/((2*sideBC)*sideAC);
        double angleC = Math.acos(cosC);
        angleC = Math.toDegrees(angleC);
        return angleC;
    }
    public double getAngleB()
    {
        double a2 = Math.pow(sideAB, 2);
        double b2 = Math.pow(sideBC, 2);
        double c2 = Math.pow(sideAC, 2);
        double cosB = ((a2+b2-c2)/(2*sideAB*sideBC));
        double angleB = Math.acos(cosB);
        angleB = Math.toDegrees(angleB);
        return angleB;
    }
    public double getAngleA()
    {
        double a2 = Math.pow(sideAB, 2);
        double b2 = Math.pow(sideBC, 2);
        double c2 = Math.pow(sideAC, 2);
        double cosA = ((a2+c2-b2)/(2*sideAB*sideAC));
        double angleA = Math.acos(cosA);
        angleA = Math.toDegrees(angleA);
        return angleA;
    }
    public double maxSide()
    {
        //if-else if-else statements for max and min sides functions
        if (sideAB >= sideBC && sideAB >= sideAC)
        {
            return sideAB;
        }
        else if(sideBC >= sideAB && sideBC >= sideAC)
        {
            return sideBC;
        }
        else
        {
            return sideAC;
        }
    }
    public double minSide()
    {
        if (sideAB <= sideBC && sideAB <= sideAC)
        {
            return sideAB;
        }
        else if(sideBC <= sideAB && sideBC <= sideAC)
        {
            return sideBC;
        }
        else
        {
            return sideAC;
        }
    }
    public double maxAngle()
    {
        double a2 = Math.pow(sideAB, 2);
        double b2 = Math.pow(sideBC, 2);
        double c2 = Math.pow(sideAC, 2);
        double cosC = ((b2 + c2)-a2)/((2*sideBC)*sideAC);
        double angleC = Math.acos(cosC);
        angleC = Math.toDegrees(angleC);
        double cosB = ((a2+b2-c2)/(2*sideAB*sideBC));
        double angleB = Math.acos(cosB);
        angleB = Math.toDegrees(angleB);
        double cosA = ((a2+c2-b2)/(2*sideAB*sideAC));
        double angleA = Math.acos(cosA);
        angleA = Math.toDegrees(angleA);
        if (angleA >= angleB && angleA >= angleC)
        {
            return angleA;
        }
        else if(angleB >= angleA && angleB >= angleC)
        {
            return angleB;
        }
        else
        {
            return angleC;
        }       
    }
    public double minAngle()
    {
        double a2 = Math.pow(sideAB, 2);
        double b2 = Math.pow(sideBC, 2);
        double c2 = Math.pow(sideAC, 2);
        double cosC = ((b2 + c2)-a2)/((2*sideBC)*sideAC);
        double angleC = Math.acos(cosC);
        angleC = Math.toDegrees(angleC);
        double cosB = ((a2+b2-c2)/(2*sideAB*sideBC));
        double angleB = Math.acos(cosB);
        angleB = Math.toDegrees(angleB);
        double cosA = ((a2+c2-b2)/(2*sideAB*sideAC));
        double angleA = Math.acos(cosA);
        angleA = Math.toDegrees(angleA);
        if (angleA <= angleB && angleA <= angleC)
        {
            return angleA;
        }
        else if(angleB <= angleA && angleB <= angleC)
        {
            return angleB;
        }
        else
        {
            return angleC;
        }       
    }
}