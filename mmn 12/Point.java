/**
 * Matala 12 - Using a class to represent a given point in the Polar system
 * 
 * @author Elia Sulimanov
 * @version 2019a
 */

public class Point
{
    private double _radius;
    private double _alpha;
    private final double MIN_VALUE = 0; //the min value of coordinate - so all bi in first Q
    private final double NINTY_DERGREE = 90;
    private final double RUONDER_FACTOR = 10000;
    private final int POWER = 2;
    private final double HALF_CIRCAL = 180;
    
    //constructors:
    /**
    * Constructor for objects of class Point. Construct a new point with the specified x y coordinates.
    * If the x coordinate is negative it is set to zero.
    * If the y coordinate is negative it is set to zero.
    * @param x The x coordinate
    * @param y The y coordinate
    */
   
    public Point(double x, double y)
    {
        x = (x < MIN_VALUE) ? MIN_VALUE : x;
        y = (y < MIN_VALUE) ? MIN_VALUE : y;
        
        _radius = Math.sqrt(Math.pow(x,POWER) + Math.pow(y,POWER));
        _alpha = (x <= MIN_VALUE) ? NINTY_DERGREE : myRounder(radDeg(Math.atan(y/x),true)); //check if X positive and then get the angle, convert to degree and round
    }
    
    /**
    * Constructor for objects of class Point. Copy constructor, construct a point using another point.
    * @param other The point from which to construct the new object
    */
    public Point(Point other)
    {
        _radius = Math.sqrt(Math.pow(other.getX(),POWER) + Math.pow(other.getY(),POWER));
        _alpha = myRounder(radDeg(Math.atan(other.getY()/other.getX()),true));
    }
    
    /**
    * This method returns the x coordinate of the point.
    * @return The x coordinate of the point 
    */
    public double getX()
    {
        return myRounder(_radius * Math.cos(radDeg(_alpha,false)));
    }
    
    /**
    * This method returns the y coordinate of the point.
    * @return The y coordinate of the point
    */
    public double getY()
    {
        return myRounder(_radius * Math.sin(radDeg(_alpha,false)));
    }
    
    /**
    * This method sets the x coordinate of the point. 
    * @param num The new x coordinate
    */
    public void setX(double num)
    {
        double y = getY();
        if(num >= MIN_VALUE)
        {
            _radius = Math.sqrt(Math.pow(num,POWER) + Math.pow(y,POWER));
            _alpha = (num == MIN_VALUE) ? NINTY_DERGREE : myRounder(radDeg(Math.atan(y/num),true));
        }
    }
    
    /** 
    * This method sets the y coordinate of the point.
    * @param num The new y coordinate
    */
    public void setY(double num)
    {
        double x = getX();
        if(num >= MIN_VALUE)
        {
            _radius = Math.sqrt(Math.pow(x,POWER) + Math.pow(num,POWER));
            _alpha = (num == MIN_VALUE) ? MIN_VALUE : myRounder(radDeg(Math.atan(num/x),true));
        }
    }
    
    /** 
    * Returns a string representation of Point in the format (x,y). 
    * @return A String representation of the Point
    */
    public String toString()
    {
        return "(" + getX() + "," + getY() + ")";
    }
    
    /**
    * Check if the given point is equal to this point.
    * @param other The point to check equality with
    * @return True if the given point is equal to this point
    */
    public boolean equals(Point other)
    {
        if(getX() == other.getX() && getY() == other.getY())
            return true;
        return false;
    }
    
    /**
    * Check if this point is above a received point.
    * @param The point to check if this point is above
    * @return True if this point is above the other point
    */
    public boolean isAbove(Point other)
    {
        return other.getY() < getY();
    }
    
    /**
    * Check if this point is below a received point.
    * @param The point to check if this point is below
    * @return True if this point is below the other point
    */
    public boolean isUnder(Point other)
    {
        return other.isAbove(this);
    }
    
    /**
    * Check if this point is left of a received point.
    * @param other The point to check if this point is left of
    * @return True if this point is left of the other point
    */
    public boolean isLeft(Point other)
    {
        return getX() < other.getX();
    }
    
    /**
    * Check if this point is right of a received point.
    * @param other The point to check if this point is right of
    * @return True if this point is right of the other point
    */
    public boolean isRight(Point other)
    {
        return other.isLeft(this);
    }
    
    /**
    * Check the distance between this point and a given point.
    * @param other The point to check the distance from
    * @return The distance
    */
    public double distance(Point other)
    {
        return myRounder(Math.sqrt(Math.pow(other.getY() - getY(),POWER) + Math.pow(other.getX() - getX(),POWER)));
    }
    
    /**
    * Moves a point. If either coordinate becomes negative the point remains unchanged.
    * @param dx The difference to add to x
    * @param dy The difference to add to y
    */
    public void move(double dx, double dy)
    {
        if(getX() + dx >= MIN_VALUE && getY() + dy >= MIN_VALUE)
        {
            setX(getX() + dx);
            setY(getY() + dy);
        }
    }
    
    /**
    * Radians Degrees
    * convert radians to degrees and vice versa
    * @param way true is rad to deg, false is deg to rad
    */
    private double radDeg(double angle, boolean way)
    {
        final double HALF_CIRCAL_TO_PI_RATIO = HALF_CIRCAL/Math.PI;
        final double PI_TO_HALF_CIRCAL_RATIO = Math.PI/HALF_CIRCAL;
        return (way) ? angle * HALF_CIRCAL_TO_PI_RATIO : angle * PI_TO_HALF_CIRCAL_RATIO;
    }
    
    /**
    * Rounder
    * @param round doubles
    */
    private double myRounder(double d)
    {
        return (Math.round(d * RUONDER_FACTOR) / (double)RUONDER_FACTOR);
    }
}
