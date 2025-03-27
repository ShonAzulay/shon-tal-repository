/**
 * This class implements the Runnable interface and is responsible for checking 
 * whether a subset of the array elements sums to a given target value (b).
 * It uses recursion and synchronization to ensure thread safety.
 */
public class ThreadCheckArray implements Runnable 
{
    private boolean flag; 
    private boolean[] winArray; 
    private SharedData sd; 
    private int[] array; 
    private int b;

    /**
     * Constructor that initializes the thread with shared data.
     * @param sd The shared data object containing the array and target value
     */
    public ThreadCheckArray(SharedData sd) 
    {
        this.sd = sd;    
        synchronized (sd) 
        {
            array = sd.getArray();
            b = sd.getB();
        }        
        winArray = new boolean[array.length];
    }

    /**
     * Recursive function to check if a subset of the array elements sums to b.
     * If a valid subset is found, it updates the winArray and sets the flag.
     * @param n The current index in the array
     * @param b The remaining sum to be checked
     */
    void rec(int n, int b)
    {
        synchronized (sd) 
        {
            if (sd.getFlag()) 
                return;
        }    

        if (n == 1)
        {
            if (b == 0 || b == array[n - 1])
            {
                flag = true;
                synchronized (sd) 
                {
                    sd.setFlag(true);
                }            
            }
            if (b == array[n - 1])
                winArray[n - 1] = true;
            return;
        }
        
      
        rec(n - 1, b - array[n - 1]);
        if (flag)
            winArray[n - 1] = true;
        
        synchronized (sd) 
        {
            if (sd.getFlag()) 
                return;
        }    

        // Recursive call including the current element
        rec(n - 1, b);
    }

    /**
     * The run method executed when the thread starts.
     * It checks if a valid subset exists and updates the shared data accordingly.
     */
    public void run() {
        // If the array has more than one element, start the recursive check
        if (array.length != 1)
        {
            if (Thread.currentThread().getName().equals("thread1"))
                rec(array.length - 1, b - array[array.length - 1]);
            else 
                rec(array.length - 1, b);
        }

        // Special case for an array with a single element
        if (array.length == 1)
        {
            if (b == array[0] && !flag)
            {
                winArray[0] = true;
                flag = true;
                synchronized (sd) 
                {
                    sd.setFlag(true);
                }
            }
        }

        // If a valid subset was found, update the shared winArray
        if (flag)
        {
            if (Thread.currentThread().getName().equals("thread1"))
                winArray[array.length - 1] = true;
            synchronized (sd) 
            {
                sd.setWinArray(winArray);
            }    
        }
    }
}
