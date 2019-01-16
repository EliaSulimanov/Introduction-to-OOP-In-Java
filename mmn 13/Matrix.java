/**
 * Matala 13 - Using a class to represent an image 
 *
 * @author Elia Sulimanov
 * @version 2019a
 */
public class Matrix
{
    //attribute:
    int[][] imageArray;

    //constructors:
    /**
     * Constructs a Matrix from a two-dimensional array, 
     * the dimensions as well as the values of this Matrix 
     * will be the same as the dimensions and values of the two-dimensional array.
     * @param array given array of colors
     */
    public Matrix(int[][] array)
    {
        imageArray = new int[array.length][array[0].length]; //make new array, the size of the given one
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array[0].length; j++)
            {
                imageArray[i][j] = array[i][j]; //initialize every single index in the array by the given one
            }
        }
    }
    
    /**
     * Constructs a size1 by size2 Matrix of zeros.
     * @param size1 columns size
     * @param size2 rows size
     */
    public Matrix(int size1, int size2)
    {
        imageArray = new int[size1][size2];
        //I do not initialize every single item in the array as it constructs as zero by default
    }
    
    /**
     * string representing the matrix in table format
     * @return string representing the matrix
     */
    public String toString()
    {
        String returnValue = "";
        for(int i = 0; i < imageArray.length; i++)
        {
            for(int j = 0; j < imageArray[0].length; j++)
            {
                returnValue += (j == imageArray[0].length - 1) ? imageArray[i][j] : imageArray[i][j] + "\t"; //initialize every single index in the array by the given one
            }
            returnValue += "\n";
        }
        return returnValue;
    }
    
    /**
     * flip the Matrix vertically
     * @return Matrix object, vertically flipped
     */
    public Matrix flipVertical()
    {
        int[] rowHelper = new int[imageArray.length]; //1d array that represent single row in the original array
        int[][] flippedArray = new int[imageArray.length][imageArray[0].length];
        
        for(int i =0; i < imageArray.length / 2; i++)
        {
            rowHelper = imageArray[imageArray.length - i - 1]; //taking an row from the other end of the matrix
            flippedArray[imageArray.length - i - 1] = imageArray[i]; //initializing this side row to the other side
            flippedArray[i] = rowHelper;  //initialize other side row to the current row
        }
        return new Matrix(flippedArray);
    }
    
    /**
     * flip the Matrix horizontally
     * @return Matrix object, horizontally flipped
     */
    public Matrix flipHorizontal()
    {
        int[][] flippedArray = new int[imageArray.length][imageArray[0].length];
        
        for (int i = 0; i < imageArray.length; i++)
        {
            for(int j = 0; j < imageArray[0].length / 2; j++)
            {
                int columnHelper = imageArray[i][imageArray[0].length - j - 1]; //take the opposite value of the matrix
                flippedArray[i][imageArray[0].length - j -1] = imageArray[i][j]; //put this value to the opposite index
                flippedArray[i][j] = columnHelper; //put opposite value and put to this side
            }
        }
        return new Matrix(flippedArray);
    }

    /**
     * rotate the Matrix clockwise
     * @return Matrix object, clockwise rotated
     */
    public Matrix rotateClockwise()
    {
        int[][] rotatedArray = new int[imageArray[0].length][imageArray.length]; //create new array, but rotated size
        int[] rowHelper;
        for (int i = 0; i < imageArray.length; i++) //for every row in the original matrix
        {
            rowHelper = imageArray[i]; //take the row from the given matrix
            for (int j = 0; j < imageArray[0].length; j++) //for every column in original matrix
            {
                rotatedArray[j][i] = rowHelper[j]; //initialize the rotated array
            }    
        }
        return new Matrix(rotatedArray).flipHorizontal(); //finally, flip the matrix, as the initialization was made the right way
    }

    /**
     * rotate the Matrix counter-clockwise
     * @return Matrix object, counter-clockwise rotated
     */
    public Matrix rotateCounterClockwise()
    {
        Matrix counterRotatedMatrix = new Matrix(imageArray).rotateClockwise().flipHorizontal().flipVertical(); //create matrix copy. counter clock wise rotate is actually an simple flip so flip it.
        return new Matrix(counterRotatedMatrix.imageArray);
    }
}
