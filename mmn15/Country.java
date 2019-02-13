
/*
    Class Country
    Elia Sulimanov
    2019a
 */

public class Country {
    private CityNode _head;
    private String _countryName;

    /**
     * Country class constructor
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @param countryName country name
     */
    public Country(String countryName)
    {
        _countryName = countryName;
        _head = null;
    }

    /**
     * add new city to the list
     * Time complexity = O(n)
     * Space complexity = O(1)
     * @param name new city name
     * @param xCenter x coordinate of the center of the new city
     * @param yCenter y coordinate of the center of the new city
     * @param xStation x coordinate of the central station of the new city
     * @param yStation y coordinate of the central station of the new city
     * @param numOfResidents number of residents in the new city
     * @param noOfNeighborhoods number of neighborhoods in the new city
     * @return true if city added successfully false if not
     */
    public boolean addCity(String name, double xCenter, double yCenter, double xStation, double yStation, long numOfResidents, int noOfNeighborhoods)
    {
        if (name.equals("") || xCenter < 0 || yCenter < 0 || xStation < 0 || yStation < 0)
            return false; //bad input

        CityNode helper = _head;

        while (helper != null)
        {
            if (_head.getCity().getCityName().equals(name))
                return false; //City already exist
            helper = helper.getNext(); //check on the next city
        }

        City newCity = new City(name, xCenter, yCenter, xStation, yStation, numOfResidents, noOfNeighborhoods);
        CityNode node = new CityNode(newCity);

        CityNode temp = _head; //add new city to the starting of the list
        _head = node;
        node.setNext(temp);

        return true;
    }

    /**
     * get list of cites norther to the given one
     * Time complexity = O(n)
     * Space complexity = O(1)
     * @param cityName the name of the city too find cites norther of
     * @return list of cities norther given city
     */
    public String citiesNorthOf(String cityName)
    {
        Point cityCenter = getCityCenterByName(cityName);

        if(cityCenter == null)
            return "There is no city with the name " + cityName;

        CityNode temp = _head;
        String returnStr = "";

        while (temp != null)
        {
            if(temp.getCity().getCityCenter().isAbove(cityCenter))
            {
                returnStr += "\n" + temp.getCity().toString() + "\n";
            }
            temp = temp.getNext();
        }

        if (returnStr.equals(""))
            return  "There are no cities north of " + cityName;

        return "The cities north of " + cityName + " are:" + returnStr;
    }

    /**
     * get this country copy
     * Time complexity = O(n)
     * Space complexity = O(1)
     * @return this country copy
     */
    public Country getCities()
    {
        Country rtnCountry = new Country(_countryName);

        CityNode temp = _head;

        while (temp != null)
        {
            rtnCountry.addCity(temp.getCity().getCityName(),
                    temp.getCity().getCityCenter().getX(), temp.getCity().getCityCenter().getY(),
                    temp.getCity().getCentralStation().getX(), temp.getCity().getCentralStation().getY(),
                    temp.getCity().getNumOfResidents(), temp.getCity().getNoOfNeighborhoods());
            temp = temp.getNext();
        }

        return rtnCountry;
    }

    /**
     * get country name
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @return country name
     */
    public String getCountryName()
    {
        return _countryName;
    }

    /**
     * get number of cites
     * Time complexity = O(n)
     * Space complexity = O(1)
     * @return number of cites in the country
     */
    public int getNumOfCities()
    {
        int counter = 0;
        CityNode temp = _head;

        while (temp != null)
        {
            counter++;
            temp = temp.getNext();
        }

        return counter;
    }

    /**
     * getting the number of residents in the country
     * Time complexity = O(n)
     * Space complexity = O(1)
     * @return number of residents in the country
     */
    public long getNumOfResidents()
    {
        CityNode temp = _head;
        int counter = 0;

        while (temp != null)
        {
            counter += temp.getCity().getNumOfResidents();
            temp = temp.getNext();
        }

        return counter;
    }

    /**
     * get the longest distance between the cities in the country
     * Time complexity = O(n^2)
     * Space complexity = O(1)
     * @return longest distance between the cities in the country
     */
    public double longestDistance()
    {
        if (getNumOfCities() < 2)
            return 0;

        CityNode temp = _head, helper = temp.getNext();
        double distance = 0;

        while (temp != null)
        {
            helper = temp.getNext();
            while (helper != null)
            {
                if (temp.getCity().getCityCenter().distance(helper.getCity().getCityCenter()) > distance) //if this distance is bigger then the one we already found
                    distance = temp.getCity().getCityCenter().distance(helper.getCity().getCityCenter());
                helper = helper.getNext();
            }
            temp = temp.getNext();
        }

        return distance;
    }

    /**
     * get the southernmost city.
     * Time complexity = O(n)
     * Space complexity = O(1)
     * @return the southernmost city, if there is no cites, return null
     */
    public City southernmostCity()
    {
        if(getNumOfCities() == 0)
        {
            return null;
        }

        CityNode temp = _head;
        City southest = temp.getCity();

        while (temp != null)
        {
            if (temp.getCity().getCityCenter().isUnder(southest.getCityCenter()))
                southest = temp.getCity();
            temp = temp.getNext();
        }
        return southest;
    }

    /**
     * string representation of this country
     * Time complexity = O(n)
     * Space complexity = O(1)
     * @return list of cities in this country
     */
    public String toString()
    {
        String rtnString = "";

        CityNode temp = _head;
        while (temp != null)
        {
            rtnString += ("\n" + temp.getCity().toString() + "\n");
            temp = temp.getNext();
        }

        if (rtnString.equals(""))
            return "There are no cities in this country.";
        return "Cities of " + getCountryName() + ":\n" + rtnString;
    }

    /**
     * unify 2 cities to one
     * Time complexity = O(n)
     * Space complexity = O(1)
     * @param cityName1 first city name
     * @param cityName2 second city name
     * @return united city
     */
    public City unifyCities(String cityName1, String cityName2)
    {
        City city1 = getCityByName(cityName1);
        City city2 = getCityByName(cityName2);

        Point centralStation = (city1.getCentralStation().isLeft(city2.getCentralStation())) ? city1.getCentralStation() : city2.getCentralStation();

        City unifedCity = new City(cityName1 + "-" + cityName2,
                (city1.getCityCenter().getX() + city2.getCityCenter().getX())/2, (city1.getCityCenter().getY() + city2.getCityCenter().getY())/2,
                centralStation.getX(), centralStation.getY(),
                city1.getNumOfResidents() + city2.getNumOfResidents(), city1.getNoOfNeighborhoods() + city2.getNoOfNeighborhoods());

        if(city1.getNumOfResidents() >= city2.getNumOfResidents())
        {
            removeCity(cityName2);

            CityNode temp = _head;
            while (temp != null)
            {
                if(temp.getCity().getCityName().equals(cityName1))
                    temp.setCity(new City(unifedCity));
                temp = temp.getNext();
            }
        }
        else
        {
            removeCity(cityName1);

            CityNode temp = _head;
            while (temp != null)
            {
                if(temp.getCity().getCityName().equals(cityName2))
                    temp.setCity(new City(unifedCity));
                temp = temp.getNext();
            }
        }

        return unifedCity;
    }

    private Point getCityCenterByName (String cityName)
    {
        CityNode temp = _head;
        while (temp != null)
        {
            if (temp.getCity().getCityName().equals(cityName)) //find city the same name as this
                return temp.getCity().getCityCenter();
            temp = temp.getNext();
        }
        return null;
    }

    private City getCityByName (String cityName)
    {
        CityNode temp = _head;
        while (temp != null)
        {
            if (temp.getCity().getCityName().equals(cityName)) //find city the same name as this
                return temp.getCity();
            temp = temp.getNext();
        }
        return null;
    }

    private void removeCity(String cityName)
    {
        // removing node just like its done in the presentations book.
        if (_head != null) {
            if (_head.getCity().getCityName().equals(cityName))
                _head = _head.getNext();
            else {
                CityNode behind = _head;
                while (behind.getNext() != null) {
                    if (behind.getNext().getCity().getCityName().equals(cityName)) {
                        behind.setNext(behind.getNext().getNext());
                        return;
                    }
                    else
                        behind = behind.getNext();
                }
            }
        }
    }
}
