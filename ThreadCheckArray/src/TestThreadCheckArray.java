import java.util.Scanner;

/*  
 * Class to test checking for a subset of an array that sums to a given number
 * The class use two separate threads
 */

public class TestThreadCheckArray { /* read user input, create a shared data object
 									 * luanches two threads to search for a valid subset,
 									 * waits for them and prints the result
 									 **/
	public static void main(String[] args) {
		// create a Scanner to read input from the user
		try (Scanner input = new Scanner(System.in)) { 
			Thread thread1, thread2; //Declare the threads
			System.out.println("Enter array size");
			int num  = input.nextInt();
			int [] array = new int[num];
			System.out.println("Enter numbers for array");
			
			for (int index = 0; index < num; index++)  // Read values into the array
				array[index] = input.nextInt();
			
			System.out.println("Enter number");
			num = input.nextInt();
			
			SharedData sd = new SharedData(array, num);איך ניתן לעשות קיצור במאק באקליפס לתיעוד javadoc
			
			// Create two threads that will run the ThreadCheckArray test
			thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
			thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
			thread1.start();
			thread2.start();
			try 
			{
				// start both threads
				thread1.join();
				thread2.join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if (!sd.getFlag())
			{
				System.out.println("Sorry");
				return;
			}
			System.out.println("Solution for b : " + sd.getB() + ",n = " + sd.getArray().length);
			System.out.print("I:    ");
			for(int index = 0; index < sd.getArray().length ; index++)
				System.out.print(index + "    ");
			System.out.println();
			System.out.print("A:    ");
			for (int index : sd.getArray())
			{
				System.out.print(index);
				int counter = 5;
				while (true)
				{
					index = index / 10;
					counter--;
					if (index == 0)
						break;
				}
				for (int i = 0; i < counter; i++)
					System.out.print(" ");
			}

			System.out.println();
			System.out.print("C:    ");
			for (boolean index : sd.getWinArray())
			{
				if (index)
					System.out.print("1    ");
				else
					System.out.print("0    ");	
			}
		}
	}

}
