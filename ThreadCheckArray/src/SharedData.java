/**
 * This class is used for sharing data between different components of the program.
 * It contains an integer array, a boolean array to indicate winning status, 
 * a boolean flag, and a constant integer 'b'.
 */
public class SharedData 
{
    private int[] array; 
    private boolean[] winArray; 
    private boolean flag;
    private final int b; 

    /**
     * Constructor that initializes the shared data.
     * @param array An array of integers
     * @param b A constant integer value
     */
    public SharedData(int[] array, int b) {
        this.array = array;
        this.b = b;
    }

    /**
     * Returns the winning status array.
     * @return A boolean array indicating which elements are winners
     */
    public boolean[] getWinArray() 
    {
        return winArray;
    }

    /**
     * Updates the winning status array.
     * @param winArray A new boolean array for winning status
     */
    public void setWinArray(boolean[] winArray) 
    {
        this.winArray = winArray;
    }

    /**
     * Returns the integer array.
     * @return An array of integers
     */
    public int[] getArray() 
    {
        return array;
    }

    /**
     * Returns the constant value 'b'.
     * @return The constant integer 'b'
     */
    public int getB() 
    {
        return b;
    }

    /**
     * Returns the flag state.
     * @return A boolean representing the flag state
     */
    public boolean getFlag() 
    {
        return flag;
    }

    /**
     * Sets the flag state.
     * @param flag A boolean value to set the flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
