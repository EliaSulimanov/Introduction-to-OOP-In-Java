/**
 *  Exercise 14
 *  Author - Elia Sulimanov
 *  Version - 2019a
 */

public class Ex14 {

    /**
     * function that help amir win a coin game
     * the function also print the progress of the game
     * time complexity: o(n)
     * space complexity: o(1);
     * @param arr coin array
     */
    public static void win (int [] arr)
    {
        int amirTotal = 0, tamarTotal = 0;

        int stepsCounter = 0;

        int i = 0, j = arr.length - 1; //array indexes

        if(arr.length == 2) //if only 2 coins
        {
            System.out.println("Amir took " + Math.max(arr[0], arr[1]));
            amirTotal += Math.max(arr[0], arr[1]); //amir take the bigger one
            tamarTotal += arr[0] + arr[1] - amirTotal;
            System.out.println("Tamar took " + tamarTotal);
            stepsCounter = arr.length; //so we dont go into the while loop
        }

        while(i < arr.length - 1 && j > -1 && stepsCounter < arr.length) //first check if the coming step is out of bounds, next, if all coins been taken.
        {
            if(arr[i] + Math.min(arr[i + 1], arr[j]) >= arr[j] + Math.min(arr[i], arr[j - 1])) {
                //if amir take the leftmost coin, tamar will the biggest coin between the left once, and same for rightmost coin.
                //therefore I want to check if (the taken coin + the coin left after tamar take her coin) is bigger then taking the other side coin.

                System.out.println("Amir took " + arr[i]);
                amirTotal += arr[i];
                stepsCounter++;
                i++;
            }
            else {
                if(amirTotal + arr[j] <= tamarTotal + Math.max(arr[i], arr[j - 1])) {
                    // there can be situation in which is better of amir to take the small valued coin, as there coming an big valued one

                    System.out.println("Amir took " + arr[i]);
                    amirTotal += arr[i];
                    stepsCounter++;
                    i++;
                }
                else {
                    System.out.println("Amir took " + arr[j]);
                    amirTotal += arr[j];
                    stepsCounter++;
                    j--;
                }

            }

            if(arr[i] >= arr[j]) {
                System.out.println("Tamar took " + arr[i]);
                tamarTotal += arr[i];
                stepsCounter++;
                i++;
            } //tamar always take the bigger coin
            else {
                System.out.println("Tamar took " + arr[j]);
                tamarTotal += arr[j];
                stepsCounter++;
                j--;
            }
        }

        System.out.println("Final Score:");
        System.out.println("Amir total " + amirTotal);
        System.out.println("Tamar total " + tamarTotal);
    }

    /*
     * A. The function what, determine whether or not 'num' can be shown
     *    as sum of ints in an given array 'a'.
     *    It also prints what indexes in the array is the bounds of the nums
     *    summed.
     * B. The function time complexity is O(n^3) space complexity is O(1).
     * D. time complexity: O(n), the outer for loop is O(n), and in the loop you might have up to 2 operations, add and remove from sum.
     *    space complexity: O(1), no variables created inside no loop, so space is constant.
     */

    /**
     * determine whether or not 'num' can be shown as sum of ints in an given array 'a'.
     * @param a array of ints
     * @param num number we desire to determine about
     * @return true and false values
     */
    public static boolean what(int[]a,int num)
    {
        int sum = 0, positionHelper = 0;

        for (int i = 0; i <= a.length; i++) //I must check <= rather then < because on 1 int array this wont work other wise.
        {
            // if sum bigger then num, then remove numbers from the start.
            // continue to do so until sum is smaller the num again.
            while (sum > num && positionHelper < i-1)
            {
                sum = sum - a[positionHelper];
                positionHelper++;
            }

            // if sum equal to num, we found what we was looking for.
            if (sum == num)
            {
                System.out.println("between " + positionHelper + " and " + (i-1));
                return true;
            }

            if(i < a.length) //because loop is going from 0 to (array length) rather then (array length-1) it might throw OOB exception.
                sum += a[i];
        }

        System.out.println("No ");
        return false;
    }

    /**
     * find longest slope in given array and step sizes
     * @param mat 2d ints array
     * @param num steps size
     * @return longest slope in the given array, considering steps size
     */
    public static int longestSlope (int [][] mat, int num) {
        return (longestSlopePicker(mat, num, 0, 0));
    }

    private static int longestSlope(int[][] mat, int row, int column, int num) {

        // the following booleans is checking if it's possible to go in desired direction,
        // if yes, is this step is following the condition we set about slope size.
        boolean stepRight = column + 1 < mat[0].length && mat[row][column] - mat[row][column + 1] == num;
        boolean stepDown = row + 1 < mat.length && mat[row][column] - mat[row + 1][column] == num;
        boolean stepLeft = column - 1 >= 0 && mat[row][column] - mat[row][column - 1] == num;
        boolean stepUp = row - 1 >= 0 && mat[row][column] - mat[row - 1][column] == num;

        /* Mid Array Movement */
        if (stepRight && stepDown && stepLeft && stepUp) //on top of an "hill"
            return 1 + Math.max(Math.max(longestSlope(mat, row, column - 1, num), longestSlope(mat, row - 1, column, num)),
                    Math.max(longestSlope(mat, row, column + 1, num), longestSlope(mat, row + 1, column, num))); //return the bigger slope in all possible directions
        /*-----------*/

        /* Frame Movement */
        if (stepRight) //right step available
        {
            if (stepDown) //right && down step available
                return 1 + Math.max(longestSlope(mat, row, column + 1, num), longestSlope(mat, row + 1, column, num));

            if (stepLeft) //right && left step available
                return 1 + Math.max(longestSlope(mat, row, column + 1, num), longestSlope(mat, row, column - 1, num));

            if (stepUp) //right && up step available
                return 1 + Math.max(longestSlope(mat, row, column + 1, num), longestSlope(mat, row - 1, column, num));

            return 1 + longestSlope(mat, row, column + 1, num);
        }


        if (stepDown) //down step available
        {
            if (stepLeft) //down && left step available
                return 1 + Math.max(longestSlope(mat, row + 1, column, num), longestSlope(mat, row, column - 1, num));

            if (stepUp) //down && up step available
                return 1 + Math.max(longestSlope(mat, row + 1, column, num), longestSlope(mat, row - 1, column, num));

            return 1 + longestSlope(mat, row + 1, column, num);
        }

        if (stepLeft) //left step available
        {
            if (stepUp) //left && up step available
                return 1 + Math.max(longestSlope(mat, row, column - 1, num), longestSlope(mat, row - 1, column, num));

            return 1 + longestSlope(mat, row, column - 1, num);
        }

        if (stepUp) //up step available
        {
            return 1 + longestSlope(mat, row - 1, column, num);
        }
        /*-----------*/

        return 1;
    }

    private static int longestSlopePicker(int[][] mat, int num, int i, int j)
    {
        if (i < 0 || i >= mat.length - 1 || j < 0 || j >= mat[0].length - 1) //out of bounds
            return 0;

        int temp = longestSlope(mat, i, j, num); //save longest slope from here

        if (j + 1 >= mat[0].length - 1) //check if this is the end of the column
        {
            j = 0; //go to the start of the column
            i++; //go one row down
        }
        else
        {
            j++; //go on on this column
        }

        return (Math.max(temp, longestSlopePicker(mat, num, i, j))); //return the bigger size, this slope or the next one
    }

    /**
     * method that count the number of possibilities to fill n-sized array,
     * starting at 1 to max, so the array will be sorted small to big.
     * @param n array size
     * @param max biggest number in the array
     * @return number of possibilities to fill the array
     */
    public static int howManySorted(int n, int max) {
        // n is the vector space dimension and max is the destination coordinate, starting at 1.

        if (n <= 0 || max <= 0) //no vector space or destination coordinate out of grid
            return 0;

        if (n == 1) //R1 vector
            return max;

        if (max == 1) //starting at the right point.
            return 1;

        return howManySorted(n - 1, max) + howManySorted(n, max - 1);
        // by mapping our problem to "flatter" space and moving our destination coordinate closer to 1
        // we reducing the possible ways to get to our destination and making the problem much easier.
    }
}
