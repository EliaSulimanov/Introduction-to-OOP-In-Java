
/*
    Class CityNode
    Elia Sulimanov
    2019a
 */

public class CityNode {
    private City _city;
    private CityNode _next;

    /**
     * Constructor of class CityNode
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @param c city to be pointed from current node
     */
    public CityNode (City c)
    {
        _city = c;
        _next = null;
    }

    /**
     * Constructor of class CityNode
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @param c city to be pointed from current node
     * @param n node to be pointed from current node
     */
    public CityNode (City c, CityNode n)
    {
        _city = c;
        _next = n;
    }

    /**
     * Copy constructor of class CityNode
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @param c node to be copied
     */
    public CityNode (CityNode c)
    {
        _city = c.getCity();
        _next = c.getNext();
    }

    /**
     * Get current node city attribute
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @return current node city attribute
     */
    public City getCity()
    {
        return _city;
    }

    /**
     * Get next node
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @return next node
     */
    public CityNode getNext()
    {
        return _next;
    }

    /**
     * Set current node city attribute
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @param c city to be pointed
     */
    public void setCity(City c) {
        _city = c;
    }

    /**
     * Set next node attribute
     * Time complexity = O(1)
     * Space complexity = O(1)
     * @param next node to be pointed
     */
    public void setNext(CityNode next) {
        _next = next;
    }
}
