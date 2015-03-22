import java.util.Scanner;

/**
 * 
 * Program: Sums
 * Author: Driving 90 minutes
 * 
 * CMP 12A/L, Fall 2012
 * Programming Lab #4
 * October 26, 2012
 * 
 * This program inputs a number, n, to mark the last number in the range for the summation of 1/n, which is then used as the max limit
 * for the summation of 2n.
 * 
 * Input:
 * Number.
 * 
 * Output:
 * Result of the summation of (2n) with the max limit of n being the rounded summation of 1/n, which has the max limit inputted by user.
 * 
 * Bugs and Limitations:
 * Program can only calculate the summation of 2n to n=7441 before error (due to Java's handling of recursion).
 * For summation of 1/n, the the fractions the computer is summing will eventually be so small that they cannot have any effect on the 
 * sum due to the limitation of the hardware.
 */

public class Sums {
	
	/**
	 * main()
	 * 
	 * Runs the methods of the program, looped until user responds to the continuation prompt negatively. First gets the max limit
	 * for n. Then calculates the summation of 1/n from 1 to the max limit. Then calculates and displays the summation of 2n using
	 * the previously calculated, rounded summation of 1/n as the max limit. Finally, asks user if he/she wants to continue, and 
	 * will quit if answer is negative (no, N, etc.). 
	 * 
	 * Input: n/a
	 * Output: Summation of 2n with the max limit of n being the rounded summation of 1/n, which has the max limit inputted by user.
	 */
	
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in); // Scanner for inputting the whether or not the user wants to continue
        String cont; // String for holding user's response for the continuation question
        double maxNumber; // The max limit for n in the summation calculation
        
       /* Runs loop of code until user types in a negative response to the continuation prompt */
        do{
        	/* The max limit for the summation is set equal to the result of getNumber(), the number inputted by user */
            maxNumber = getNumber(); 
            /* Display the summation of 2n with the max limit of n being the rounded summation of 1/n, which has the max limit
             * of the number inputted by the user
             */
            System.out.println(sumDoub(Math.round(sumFrac(maxNumber, 0.00, 1.00)), 0, 1));
            /* Asks if user wants to enter a new number */
            System.out.println("Would you like to enter a new number?");
            /* Input string response for continuation */
            cont = in.nextLine();
        }while(!(cont.equals("N") || cont.equals("n") || cont.equals("No") || cont.equals("no") || cont.equals("NO")));
            
        
    }
    
    
    /**
	 * introduction()
	 * 
	 * Displays the introduction line for the program.
	 * 
	 * Input: n/a
	 * Output: Opening message.
	 */
    
    
    public static void introduction(){
    	/* Display the introduction line for the program */
        System.out.println("Welcome to the sum of sums program!");
    }
    
    /**
	 * getNumber()
	 * 
	 * Prompts the user for a number and inputs the max number n for the summation.
	 * 
	 * Input: Number (max limit for summation).
	 * Output: n/a (just prompts for number).
	 */
    
    public static int getNumber(){
        int number; // Integer for holding the inputted number, max limit of n
        Scanner input = new Scanner(System.in); // Scanner for reading in max limit of n
        
        /* Prompt user for number, a max limit of n for summation */
        System.out.println("Please enter a number:"); 
        /* Input the max limit of n for the summations of 1/n later on */
        number = input.nextInt();
        /* Return the number inputted for the max limit, the result of the program */
        return number;
    }
    
    /**
	 * sum Frac()
	 * 
	 * Calculates the summation of (1/n) from 1 to the number the user input previously. It calculates 1/n, adds it to
	 * the sum, and uses recursion, running the function again with the new sum and incremented n, until n is larger than
	 * the max limit, when the method returns the sum.
	 * 
	 * Input: n/a (arguments for method: the maximum number the user input, the sum (starts at 0), and the counter n).
	 * Output: Calculates summation of (1/n) from 1 to the number the user input. (not really output, but sets up output).
	 */
    
    public static double sumFrac(double maxNum, double sum, double n){
        /* If the incremented n passes the maximum limit inputted previously by the user */
        if(n > maxNum){
        	/* Return the sum, the summation of 1/n from 1 to maxNum */
            return sum;
        }
        /* If the incremented n has not passed the maxNumber inputted previously by the user */
        else{
        	/* The result of 1/n is added to the sum thus far */
            sum += (1.00/n);
            /* Recursion - run the method again using the same maxNum, the updated sum, and the incremented n */
            return sumFrac(maxNum, sum, n+1);
        }    
    }
    
    /**
	 * sumDoub()
	 * 
	 * Calculates the summation of (2n) from 0 to the limit of the rounded summation of 1/n. It calculates 2n, adds it to
	 * the sum, and uses recursion, running the function again with the new sum and incremented n, until n is larger than
	 * the max limit, when the method returns the sum. If n = 1, 2n = 1, an exception in the program, so the calculation
	 * of the sum changes in this instance.
	 * 
	 * Input: n/a (arguments for method: the maximum number (calculated summation of 1/n), the sum (starts at 0), and the counter n).
	 * Output: Calculates summation of (2n) from 1 to the number the user input. (not really output, but sets up output)
	 */
    
    public static int sumDoub(long maxNum, int sum, int n){
    	/* If the incremented n passes the maximum limit (calculated summation of 1/n with max n of user inputted number) */
        if(n > maxNum){
        	/* Return the sum, the summation of 2n from 1 to maxNum */
            return sum;
        }
        /* If the incremented n has not passed the maxNumber sum of 1/n and this is the first run of this method */
        else if(n == 1){
        	/* n, 1, is added to the sum, for a sum of 1; an exception for this program */
        	sum += n;
        	/* Recursion - run the method again using the same maxNum, the updated sum, and the incremented n */
        	return sumDoub(maxNum, sum, n+1);
        }
        /* If the incremented n has not passed the maxNumber sum of 1/n and this is not the first run of this method*/
        else{
        	/* The result of 2n is added to the sum thus far */
            sum += (2*n);
            /* Recursion - run the method again using the same maxNum, the updated sum, and the incremented n */
            return sumDoub(maxNum, sum, n+1);
        }
    }
    
}
