import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/************************************************************************
 * Program: ImageProc.java; class ImageProc				
 * Authors: Patrick Au (xxxxx@ucsc.edu)
 * 	    Dave Rajczyk (xxxxxx@ucsc.edu)
 * Modified by: Alex Gainer, 2/13/2011
 * Modified by: Alex Pang,   2/19/2011
 * Modified by: Dimitrios Skourtis, 11/4/2012
 * Modified by: Tom Austin, 11/5/2012
 * Modified by: your name,   date here
 *
 * CMP 12A/L: Fall 2012 DRIVING TIME 70 MINUTES
 * Lab Assignment #6
 * 							
 *									
 * This is an image processing program. It has 5 basic functionalities	
 * for the user: 1. Read 2. Edge Detection 3. Smooth 4. Save 5. Quit
 * You will need to provide an edge detection method, as well as
 * the method to save your file.
 *									
 * Input:								
 * Filename to read from. Filename to write to.				
 * (r)ead, (E)dge detection, (S)mooth, (s)ave, (q)uit
 *									
 * Output:								
 * It will create pgm files in the working directory.			
 **************************************************************************/

class ImageProc {
	private static int width;	// These are all global variables, a very useful tool.
	private static int height;
	private static int scale;

	public static void main(String[] args) {

		System.out.println("Welcome to the CMPS 12L Basic Image Processing Program." + "\n");

		boolean   	run           = true,	// true if not ready to quit
				fileRead      = false,	// true if a file has been read
				imageModified = false;	// true if a file has been smoothed or edged

		File inFile;
		PrintWriter outFile;

		int[][] pictureArray = null;		// Points to image data. Initially empty (i.e. null).

		Scanner scan = new Scanner(System.in);

		do {
			System.out.print("What would you like to do: (r)ead, (E)dge detection, (S)mooth, (s)ave, (q)uit: ");
			char userSelection = scan.next().charAt(0);
			switch(userSelection) {

			case 'r':

				/******************************************************************************************
				 * This case switch will read in a PGM file to the array pictureArray. It checks if
				 * there has already been a file read in, in which case it will prompt the user if they
				 * wish to overwrite the file. It also handles IO exceptions, where the file the user
				 * tries to read in cannot be found or may not exist.
				 ******************************************************************************************/

				try {
					if(fileRead) {
						System.out.print("Do you want to overwrite the file (y/n)? ");

						char choice = scan.next().charAt(0);

						if(choice == 'n')
							break;
					}

					System.out.print("Enter the file to read: ");

					String filename = scan.next();

					inFile = new File(filename);
					pictureArray = readInFile(inFile);

					System.out.print(filename + " is ");
					System.out.print(pictureArray[0].length + " by ");
					System.out.println((pictureArray.length) + ".");
					fileRead = true;
				}
				catch(Exception e) {
					System.out.println("Sorry; file wasn't found.");
				}

				break;

			case 's':

				/******************************************************************************************
				 * This case switch will write out to a file the array pictureArray. It checks if
				 * there is a picture in the array to save first.
				 ******************************************************************************************/

				if(fileRead) {
					try {
						System.out.print("Save as: ");

						scan.nextLine();
						String newFilename = scan.nextLine();

						outFile = new PrintWriter(newFilename);
						writeOutFile(outFile, pictureArray);

						System.out.println(newFilename + " saved.");
						imageModified = false;
					}
					catch(Exception e) {
						System.out.println("Sorry; file wasn't found.");
					}
				}
				else {
					System.out.println("Sorry; there is no file to save.");
				}
				break;

			case 'S':

				/******************************************************************************************
				 * This case switch smooths an image that is read in. It does so by going to each pixel
				 * in the image and replacing the value there with the average of the 3x3 square around
				 * it. This case switch checks to see if an image has been read in first.
				 ******************************************************************************************/

				if(fileRead) {
					pictureArray = smooth(pictureArray);
					System.out.println("The image has been smoothed.");
					imageModified = true;
				}
				else {
					System.out.println("Sorry; there is no image to smooth.");
				}

				break;

			case 'q':

				/******************************************************************************************
				 * This case switch will quit the program. It checks if an image has been modified
				 * first, in which case it asks the user if they want to save the image before exiting.
				 ******************************************************************************************/

				if(imageModified) {
					System.out.print("Do you want to save the modified image (y/n)? ");
					char choice = scan.next().charAt(0);

					if(choice == 'n');				//Null statement; will do the else if not n.
					else {
						try {
							System.out.print("Save as: ");

							scan.nextLine();
							String newFilename = scan.nextLine();

							outFile = new PrintWriter(newFilename);

							writeOutFile(outFile, pictureArray);
							System.out.println(newFilename + " saved.");
							imageModified = false;
						}
						catch(Exception e) {
							System.out.println("Sorry; there is no file to save.");
						}
					}
				}

				System.out.println("The program is ending. Bye.");
				run = false;
				break;

			case 'E':
				// You need to fill this in for edge detection
				// Hint: structure looks very similar to case for (S)mooth

				if(fileRead) {
					pictureArray = edge(pictureArray);
					System.out.println("The image has been edged.");
					imageModified = true;
				}
				else {
					System.out.println("Sorry; there is no image to edge.");
				}

				break;

			default:
				System.out.println("That is not an option. Try again.");
				break;

			}

		} while(run);

	}

	/********************************************************************************
	 * This method is called to read an image file to an array.
	 * It reads from a PGM file in the working directory.
	 *										
	 * Input: A PGM file found in the working directory.
	 * Output: An instance of a ReadInput object.
	 ********************************************************************************/

	public static int[][] readInFile(File file) throws Exception{

		Scanner scan = new Scanner(file);

		String imageFormat = scan.nextLine();	//Stores picture format ID; not used
		width = scan.nextInt();					//Stores picture width
		height = scan.nextInt();				//Stores picture height
		scale = scan.nextInt();					//Stores scale value

		int[][] pictureArray = new int[height][width];

		// Assumes a rectangular image.  Read pixel values in row-wise order 
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++){
				pictureArray[i][j] = scan.nextInt();
			}
		}

		return pictureArray;
	}

	/********************************************************************************
	 * This method is called to save an image in an array to a file.
	 * It outputs to a PGM file in the working directory.
	 *
	 * Input: A referenced data array and an instance of a FileWriter file.
	 * Output: A PGM file created in the working directory.				
	 ********************************************************************************/

	public static void writeOutFile(PrintWriter newFile, int[][] pictureArray) {

		// Fill in this code.  PrintWriter has a newFile.println() method
		// and a newFile.print().
		// Use newFile.close() to finalize the save.
		// Hint: about 10 lines of code
		
		newFile.println("P2");
		newFile.println(width + " " + height);
		newFile.println(scale);
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(j != 0){
					newFile.print(" ");
					if(pictureArray[i][j] < 10){
					newFile.print(" ");	
					}
				}
				newFile.print(pictureArray[i][j]);
			}
			newFile.println();
		}
		newFile.close();
	}

	/********************************************************************************
	 * This method will apply a simple averaging operation to each input pixel.
	 * Image is assumed to have a zero-boundary.
	 * First, we create a copy of the input image into a larger array with zero-boundaries.
	 * Then, we apply the smoothing template to the non-boundary pixels.
	 * Finally, we prepare the smoothed, non-boundary pixels to be returned.
	 * Smoothing is done by simple averaging of the 9 original pixels.
	 * Note: not as efficient, but hopefully, more understandable.
	 *
	 * Input:  The referenced data array.
	 * Output: A smoothed image file/data array.
	 ********************************************************************************/

	public static int[][] smooth(int[][] a) {

		int[][] b = new int[height+2][width+2];		// larger array with zero boundaries
		int[][] f = new int[height  ][width  ];		// will contain the final smoothed image
		int[][] s = { {1,1,1},				// smoothing is equivalent to using
				{1,1,1},				// this 3x3 template
				{1,1,1} };
		int	row, col;

		// initialize the larger array to all zeroes
		for (row = 0; row < height+2; row++)
			for (col = 0; col < width+2; col++)
				b[row][col] = 0;

		// copy the input array and center it within the larger array
		for (row = 0; row < height; row++)
			for (col = 0; col < width; col++)
				b[row+1][col+1] = a[row][col];

		// now calculate the average of every pixel in b
		for (row = 0; row < height; row++)
			for (col = 0; col < width; col++)
				f[row][col] = convolve( s, b, row+1, col+1 );		// apply the 3x3 template

		return f;
	}

	/*******************************************************************************
	 * This method will apply the template to the data array and calculate a
	 * weighted sum, divide by 9, and rounded to the closest integer.
	 *
	 * Input:  A 3x3 template, the image array with zero-boundaries, the row and col
	 *	  where to center the template
	 * Output: The convolved (weighted) value rounded to an integer
	 ********************************************************************************/

	public static int convolve( int[][] template, int[][] data, int row, int col ) {

		int	r, c, sum = 0;

		for (r = -1; r <= +1; r++)
			for (c = -1; c <= +1; c++)
				sum = sum +
				data[row+r][col+c] *		// goes through points around row,col
				template[r+1][c+1];		// pair with corresponding elements of template

		return (int) Math.round( sum / 9.0 );
	}


	/*******************************************************************************
	 * Your method for edge detection (sobel) should be here.
	 * Note: you can use the convolve method above by passing it the appropriate
	 * parameters.
	 ********************************************************************************/

	public static int[][] edge(int[][] a) {

		int[][] b = new int[height+2][width+2];		// larger array with zero boundaries
		int[][] h = new int[height  ][width  ];		// will contain horizontal edges
		int[][] v = new int[height  ][width  ];		// will contain vertical edges
		int[][] f = new int[height  ][width  ];		// will contain the final edge image
		int[][] gx= { {-1,0,1},				// Gx template
				{-2,0,2},				// used to find vertical edges
				{-1,0,1} };
		int[][] gy= { {-1,-2,-1},			// Gy template
				{ 0, 0, 0},			// used to find horizontal edges
				{ 1, 2, 1} };
		int	row, col;

		// You have to fill in the rest of this method.
		// Structure looks quite similar to the Smooth method.
		// Hint: About 20 lines of code
		
		for (row = 0; row < height+2; row++){
			for (col = 0; col < width+2; col++){
				b[row][col] = 0;
			}
		}
		
		for (row = 0; row < height; row++){
			for (col = 0; col < width; col++){
				b[row+1][col+1] = a[row][col];
			}
		}
		for (row = 0; row < height; row++){
			for (col = 0; col < width; col++){
				h[row][col] = convolve( gx, b, row+1, col+1 );
			}
		}
		
		for (row = 0; row < height; row++){
			for (col = 0; col < width; col++){
				v[row][col] = convolve( gy, b, row+1, col+1 );
			}
		}
		
		for(row = 0; row < height; row++){
			for(col = 0; col < width; col++){
				f[row][col] = (int) Math.round(Math.sqrt((h[row][col])*(h[row][col]) + (v[row][col])*(v[row][col])));
			}
		}
		
		return f;
	}

} //end of class
