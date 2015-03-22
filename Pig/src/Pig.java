import java.util.Scanner;

/**
 * 
 * Program: Pig
 * Author: Driving 90 minutes
 * 
 * CMP 12A/L, Fall 2012
 * Programming Lab #3
 * October 18, 2012
 * 
 * This program inputs a word or phrase in English and transforms it into pig latin.
 * 
 * Input:
 * Sentence.
 * 
 * Output:
 * The sentence in pig latin.
 * 
 * Bugs and Limitations:
 * Program does not check for numbers or symbols, but interperets them as if they were letters.
 */




public class Pig {
	
	/**
	 * main()
	 * 
	 * Inputs a sentence from the user and transforms it into pig latin.
	 * If a word begins in a vowel, puts it at end followed by "way", and ignores the first letter. If a word begins in a consonant
	 * other than q, puts it at end followed by "ay", and ignores the first letter. If word begins in 'q', puts at end followed
	 * by "uay" and ignores the first two letters. The first word in a sentence is capitalized.
	 * 
	 * Input: Sentence/word.
	 * Output: Same sentence/word in pig latin.
	 */
	
	public static void main(String[] args){
		int blankPos, lastBlankPos =-1, length;
		Boolean cont = true, firstWord = true;
		char c = 'y', firstLetter;
		Scanner input = new Scanner(System.in);
		String sentence, s1 = "", s2 = "", contQuestion;
		
		System.out.println("Welcome to Pig Translator!");
		
		do{
			System.out.println("Please enter word/sentence/phrase.");
			sentence = input.nextLine();
			sentence = sentence.toLowerCase();
			length = sentence.length();
			sentence = sentence + " ";
			

			while(lastBlankPos != length){
				
				blankPos = sentence.indexOf(" ", lastBlankPos+1);
				s1 = sentence.substring(lastBlankPos+1, blankPos);
				
				firstLetter = s1.charAt(0);
				if(firstLetter == 'q'){
					s1 = s1 + firstLetter + "uay";
					
					s1 = s1.substring(2);
				}
				else {
					s1 = s1 + firstLetter;
				
					if(firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i' || firstLetter == 'o' || firstLetter == 'u'){
						s1 = s1 + "way";
					}
					else {
						s1 = s1 + "ay";
					}
					s1 = s1.substring(1);
				}
				
				if(firstWord == true){
					s2 = s1.substring(0,1);
					s2 = s2.toUpperCase();
					s1 = s1.substring(1);
					System.out.print(s2 + s1 + " ");
					firstWord = false;
				}
				else {
					System.out.print(s1 + " ");
				}
				lastBlankPos = blankPos;
			}
			
			
			System.out.println("\nWould you like to continue?");
			contQuestion = input.nextLine();
			
			if(contQuestion.equals("n") || contQuestion.equals("N") || contQuestion.equals("no") || contQuestion.equals("No") || contQuestion.equals("NO")){
				cont = false;
			}
			else {
				lastBlankPos = -1;
				firstWord = true;
			}
		}while(cont == true);
		
		System.out.println("Oodbyegay!");
		
	}
	
	
	
}
