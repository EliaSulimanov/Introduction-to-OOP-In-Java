/**
 * Matala 12 - Using a class to represent a given city
 * 
 * @author Elia Sulimanov
 * @version 2019a
 */

public class City
{
    private String _cityName;
    private Point _cityCenter, _centralStation;
    private long _numOfResidents;
    private int _noOfNeighborhoods;
    
    public final int MIN_NEIGBORHOODS = 1;
    public final long MIN_RESIDENTS = 0;
    
    //constructors:
    /**
    * Constructor for objects of class City. Construct a new city with the specified info. If numOfResidents is smaller than MIN_RESIDENTS, MIN_RESIDENTS is used. If noOfNeighborhoods is smaller than MIN_NEIGBORHOODS, MIN_NEIGBORHOODS is used.
    * @param cityName The name of the city
    * @param cityCenterX The X coordinate of the center point of the city
    * @param cityCenterY The Y coordinate of the center point of the city
    * @param centralStationX The X coordinate of the central station of the city
    * @param centralStationY The Y coordinate of the central station of the city
    * @param numOfResidents The number of residents in the city
    * @param noOfNeighborhoods The number of neighborhoods in the city
    */
   
    public City(String cityName, double cityCenterX, double cityCenterY, double centralStationX, double centralStationY, long numOfResidents, int noOfNeighborhoods)
    {
        _cityName = cityName;
        _cityCenter = new Point(cityCenterX, cityCenterY);
        _centralStation = new Point(centralStationX, centralStationY);
        _numOfResidents = (numOfResidents < MIN_RESIDENTS) ? MIN_RESIDENTS : numOfResidents;
        _noOfNeighborhoods = (noOfNeighborhoods < MIN_NEIGBORHOODS) ? MIN_NEIGBORHOODS : noOfNeighborhoods;
    }
    
    /**
    * Constructor for objects of class City. Copy constructor, construct a city using another city.
    * @param other The city from which to construct the new object
    */
    public City(City other)
    {
        _cityName = other.getCityName();
        _cityCenter = new Point(other.getCityCenter());
        _centralStation = new Point(other.getCentralStation());
        _numOfResidents = other.getNumOfResidents();
        _noOfNeighborhoods = other.getNoOfNeighborhoods();
    }
    
    /**
    * Returns the name of the city.
    * @return The name of the city
    */
    public String getCityName()
    {
        return _cityName;
    }

    /**
    * Returns the center of the city as a Point object.
    * @return The center point of the city
    */
    public Point getCityCenter()
    {
        return _cityCenter;
    }
    
    /**
    * Returns the centeral station of the city as a Point object.
    * @return The central station of the city
    */
    public Point getCentralStation()
    {
        return _centralStation;
    }
    
    /**
    * Returns the number of residents of the city.
    * @return The number of residents of the city
    */
    public long getNumOfResidents()
    {
        return _numOfResidents;
    }
    
    /**
    * Returns the number of neighborhoods of the city.
    * @return The number of neighborhoods of the city
    */
    public int getNoOfNeighborhoods()
    {
        return _noOfNeighborhoods;
    }
    
    /**
    * Sets the name of the city.
    * @param cityName The new name of the city
    */
    public void setCityName(String cityName)
    {
        _cityName = cityName;
    }
    
    /**
    * Sets the center point of the city.
    * @param cityCenter The new center point of the city
    */
    public void setCityCenter(Point cityCenter)
    {
        _cityCenter = new Point(cityCenter);
    }
    
    /**
    * Sets the central station point of the city.
    * @param centralStation The new central station point of the city
    */
    public void setCentralStation(Point centralStation)
    {
        _centralStation = new Point(centralStation);
    }
    
    /**
    * Sets the number of residents of the city, but only if it is bigger or equal to MIN_RESIDENTS.
    * @param numOfResidents The new number of residents in the city
    */
    public void setNumOfResidents(long numOfResidents)
    {
        _numOfResidents = (numOfResidents >= MIN_RESIDENTS) ? numOfResidents : _numOfResidents;
    }
    
    /**
    * Sets the number of neighborhoods of the city, but only if it is bigger or equal to MIN_NEIGBORHOODS.
    * @param noOfNeighborhoods The new number of neighborhoods in the city
    */
    public void setNoOfNeighborhoods(int noOfNeighborhoods)
    {
        _noOfNeighborhoods = (noOfNeighborhoods >= MIN_NEIGBORHOODS) ? noOfNeighborhoods : _noOfNeighborhoods;
    }
    
    /**
    * Returns a string representation of this City in the format
    * <pre>
    * City Name: Tel Aviv
    * City Center: (5.0,8.0)
    * Central Station: (3.0,4.0)
    * Number of Residents: 1004
    * Number of Neighborhoods: 5
    * </pre>
    * @return A String representation of this city
    */
   public String toString()
   {
       return "City Name: " + getCityName() + "\n" +
              "City Center: " + getCityCenter() + "\n" +
              "Central Station: " + getCentralStation() + "\n" +
              "Number of Residents: " + getNumOfResidents() + "\n" +
              "Number of Neighborhoods: " + getNoOfNeighborhoods();
   }
   
   /**
    * Adds the given number of residents - either positive or negative number - to the city. 
    * If the resulted number of residents is smaller than MIN_RESIDENTS, MIN_RESIDENTS is set to be the new number of residents of the city, and false is returned. 
    * Otherwise (i.e. the resulted number is bigger or equal to MIN_RESIDENTS), true is returned.
    * @return false if resulted number of residents is smaller than MIN_RESIDENTS, true otherwise
    */
   public boolean addResidents(long residentsUpdate)
   {
       if(getNumOfResidents() + residentsUpdate >= MIN_RESIDENTS)
       {
           _numOfResidents = getNumOfResidents() + residentsUpdate;
           return true;
       }
       else
       {
           _numOfResidents = MIN_RESIDENTS;
           return false;
       }
   }
   
   /**
    * Move the location of the central station of the city with the given deltas.
    * If the new location has a negative coordinate - the central station keeps its original location.
    * @param deltaX How much the x coordinate of the central station of the city is to be moved
    * @param deltaY How much the y coordinate of the central station of the city is to be moved
    */
   public void moveCentralStation(double deltaX, double deltaY)
   {
       _centralStation.move(deltaX, deltaY);
   }
   
   /**
    * Calculates the distance between the center of this city and its central station.
    * @return The distance between the center of this city and its central station
    */
   public double distanceBetweenCenterAndStation()
   {
       return _cityCenter.distance(_centralStation);
   }
   
   /**
    * Creates a new city with a new name, and where its center and central station are moved by the given deltas from this city. 
    * Its number of residents is set to MIN_RESIDENTS and its number of neightborhoods is set to MIN_NEIGBORHOODS.
    * @param newCityName The name of the new city
    * @param dx How much the x coordinates of the city's center and central station are to be moved for the new city
    * @param dy How much the y coordinates of the city's center and central station are to be moved for the new city
    * @return A new city with the given values
    */
   public City newCity(String newCityName, double dX, double dY)
   {
      City ghostCity = new City(_cityName, _cityCenter.getX(), _cityCenter.getY() , _centralStation.getX(), _centralStation.getY(), MIN_RESIDENTS, MIN_NEIGBORHOODS);
      ghostCity.moveCentralStation(dX,dY);
      
      Point ghostCityCenter = new Point(ghostCity.getCityCenter());
      ghostCityCenter.move(dX,dY);
      ghostCity.setCityCenter(ghostCityCenter);
      
      return ghostCity;
   }
}
