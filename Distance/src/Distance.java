/**
 * Program: Distance
 * Author: Driving 30 minutes
 * 
 * CMP 12A/L, Fall 2012
 * Programming Assignment #2
 * October 5, 2012
 * 
 * This program calculates the distance between two points given their Cartesian coordinates,
 * both the Euclidean distance and the Manhattan distance.
 * 
 * Input:
 * The x and y coordinates of two points, (x1, y1) and (x2, y2).
 * 
 * Output:
 * The Euclidean distance between two points and the Manhattan distance between two points.
 * 
 * Bugs and Limitations:
 * This program works for all real points on the Cartesian plane with coordinates that fall
 * inside the range (negative infinity, positive infinity).
 */
import java.util.Scanner;
import java.lang.Math;

public class Distance {
	/**
	 * main()
	 * 
	 * Finds and displays the Euclidean distance and Manhattan distance between two points
	 * given by the user.
	 * 
	 * Input: 2 Cartesian points, (x1, y1) and (x2, y2).
	 * Output: The Euclidean distance and Manhattan distance.
	 */
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		float x1, y1; //first point
		float x2, y2; //second point
		double euclideanDistance, manhattanDistance; //distances
		
		/* Prompt and input the coordinates of the first point */
		System.out.println("Enter coordinates for point 1:");
		x1 = input.nextFloat();
		y1 = input.nextFloat();
		/* Prompt and input the coordinates for the second point */
		System.out.println("Enter coordinates for point 2:");
		x2 = input.nextFloat();
		y2 = input.nextFloat();
		/*
		 * Calculate the Euclidean distance, the shortest distance between two points, or
		 * the sum of the squared changes in x and y, all square rooted.
		 */
		euclideanDistance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		/*
		 * Calculate the Manhattan distance, the sum of the changes in x and y.
		 */
		manhattanDistance = Math.abs(x2 - x1) + Math.abs(y2 - y1);
		/* Display the Euclidean and Manhattan distances */
		System.out.printf("The Euclidean distance between the two points is: %.2f", euclideanDistance);
		System.out.printf("\nThe Manhattan distance between the two points is: %.2f", manhattanDistance);
	}
}
