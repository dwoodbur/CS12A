/**
 * Program: Tester
 * Authors: Dylan Woodbury driving for 80 minutes
 *
 * CMP 12A/L, Fall 2012
 * Programming Lab #2
 * October 12, 2012
 *
 * This program uses a random number generator to output arithmetic problems, depending on the user's inputted skill
 * level. The user's score is kept track of, to be displayed at the end of the 5 question test. Also, if a user improves
 * or worsens in the next test, he/she is notified of it.
 *
 * Input:
 * Grade, guess, continue?.
 *
 * Output:
 * Random numbers, random operator, score.
 *
 * Bugs and limitations:
 * Division problems with decimals are rounded down to the integer below, making these problems illogical to solve at times.
 */




import java.util.Scanner;
import java.util.Random;

public class Tester {
	
	/**
	 * main
	 *
	 * Inputs grade, gets random numbers and operator, displays problem, calculates answer, inputs guess, checks, keeps
	 * track of score, displays score in end.
	 *
	 * Input:  Grade, guesses, continue?.
	 * Output: Random problems, score.
	 */

	
	public static void main (String[] args){
		SuperRandom randomNumberGenerator = new SuperRandom(); //for getting random integers for math problems
		Random generator = new Random(); //for getting random number to get operator
		final int numberOfQuestions = 5;  //length of test
		int grade; //skill level
		int counter = 0; //used to count problems completed
		int x; //first number
		int y; //second number
		int rand; // random number used to get operator
		int sign; // the modulus of the rand variable (1 or -1 equates to add/mult, 0 equates to sub/div)
		int answer; // correct answer to problem
		int guess; // user guess to problem
		int correct = 0; // score
		int temp; // temporary variable used to swap x and y if needed
		int lastCorrect = -1; // score on last test
		Boolean cont = true; // does player want to continue? condition of the main while loop
		String playAgain; // user input if wants to play again
		Scanner input = new Scanner(System.in); // scanning in numbers
		Scanner inputString = new Scanner(System.in); // scanning in strings
		
		randomNumberGenerator.getNextRandom(numberOfQuestions);
		/* Introduction text */
		System.out.println("Welcome to the human arithmetic efficiency test subject 4815162342.");
		System.out.println("Please answer all questions to the nearest whole number.");
		
		/* Game loop */
		while(cont == true){
			/* Display grade menu */
			System.out.println("Please select your grade level:");
			System.out.println("1. First Grade");
			System.out.println("2. Second Grade");
			System.out.println("3. Quit (recommended for you)");
			System.out.print("ENTER:");
			/* Input skill level */
			grade = input.nextInt();
			/*Skill Level */ 
			if(grade == 1) {
				while(counter < numberOfQuestions){
					/* Randomly generate 2 integers */
					x = randomNumberGenerator.getNextRandom(numberOfQuestions);
					y = randomNumberGenerator.getNextRandom(numberOfQuestions);
					/* Take modulus of random number for the use of randomly selecting either addition or subtraction */
					rand = generator.nextInt();
					sign = rand % 2;
					/* Start display of problem */
					System.out.print("What is ");
					System.out.print(x);
					/* If the modulus of the random number is 1 or -1 (50%), make addition problem */
					if(sign == 1 || sign == -1){
						/*Calculate answer to addition problem */
						answer = x + y;
						/* Display operator for addition problem */
						System.out.print(" + ");
					}
					/* If the modulus of the random number is 0 (50%), make subtraction problem */
					else {
						/* Calculate answer to subtraction problem */
						answer = x - y;
						/* Display operator for subtraction problem */
						System.out.print(" - ");
					}
					/* Finish display of problem */
					System.out.print(y);
					System.out.println("?");
					/* Input user's guess to problem's solution */
					guess = input.nextInt();
					/* Add 1 to score if user is correct */
					if(guess == answer){
						correct++;
					}
					/* The number of problems counter goes up 1*/
					counter++;
				}
			}
			/* Skill Level 2 */
			else if(grade == 2) {
				while(counter < numberOfQuestions){
					/* Randomly generate 2 integers */
					x = randomNumberGenerator.getNextRandom(numberOfQuestions);
					y = randomNumberGenerator.getNextRandom(numberOfQuestions);
					/* Take modulus of random number for the use of randomly selecting either addition or subtraction */
					rand = generator.nextInt();
					sign = rand % 2;
					/* Start display of problem */
					System.out.print("What is ");
					/* If the modulus of the random number is 1 or -1 (50%), make multiplication problem */
					if(sign == 1 || sign == -1){
						/* Continue display of problem */
						System.out.print(x);
						System.out.print(" * ");
						/* Calculate answer to multiplication problem */
						answer = x * y;
						
					}
					/* If the modulus of the random number is 0 (50%), make subtraction problem */
					else {
						/* If the first number has a smaller magnitude than the second for the division problem,
						 * switch the two numbers, so the answer to the question is not automatically 0.
						 */
						if(Math.abs(x) < Math.abs(y)){
							temp = x;
							x = y;
							y = temp;
							
						}
						/* Start display of division problem */
						System.out.print(x);
						System.out.print(" / ");
						/* Calculate answer to division problem */
						answer = x / y;
					}
					/* Finish display of problem */
					System.out.print(y);
					System.out.println("?");
					/* Input user guess to the problem */
					guess = input.nextInt();
					/* If the user's guess is right, add 1 to the score */
					if(guess == answer){
						correct++;
					}
					/* The number of problems counter goes up by 1 */
					counter++;
				}
			}
			/* If user enters 3 at menu (QUIT), then set variable to false, which will make central WHILE loop condition false */
			else {
				cont = false;
			}
			/* Display score */
			System.out.println("You got " + correct + "/" + numberOfQuestions);
			/* Display a specific remark depending on the user's score */
			if(correct == 0){
				System.out.println("You are the prime exhibitor of your race, as shown by your low intelligence and complete lack in ability.");
			}
			else if(correct == 1){
				System.out.println("A monkey did better on this test yesterday.");
			}
			else if(correct == 2){
				System.out.println("If Darwin was right, your race won't be around much longer, as shown by that performance.");
			}
			else if(correct == 3){
				System.out.println("You got more than half. I am a glass-half-empty computer though.");
			}
			else if(correct == 4){
				System.out.println("You are a little smarter than you look.");
			}
			else {
				System.out.println("Pure luck. Let's see if you can do that again.");
			}
			/* If this is not the user's first time playing (can tell because lastCorrect was initialized as -, an impossible score),
			 * then display whether the user did better or worse than last time.
			 */
			if(lastCorrect != -1){
				if(correct > lastCorrect){
					System.out.println("Hey you did a tiny bit better this time. Congrats?");
				}
				else {
					System.out.println("You did even worse than last time. Shocker!");
				}
			}
			/* Reset important counters to 0 */
			counter = 0;
			lastCorrect = correct;
			correct = 0;
			
			System.out.println("Would you like to play again?");
			/* Ask if user wants to play again (can type in 'Y', 'y', or 'yes', otherwise the WHILE loop condition will be set to false */
			playAgain = inputString.nextLine();
			/* Play again */
			if(playAgain.equals("Y") || playAgain.equals("y") || playAgain.equals("yes")){
				System.out.println("Up for another beating eh?\n");
			}
			/* Quit */
			else {
				System.out.println("I thought you looked like a quitter...");
				cont = false;
			}
		}
	
	}
}