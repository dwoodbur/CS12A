/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package format;

import java.util.Scanner;

/**
 *
 * @author The Dylan
 */
public class Format {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int width; // Width of line as declared by user
        int length; // Length of the string inputted by user
        Scanner inputInt = new Scanner(System.in); // Scanner for inputting strings (sentence and continue response)
        Scanner inputStr = new Scanner(System.in); // Scanner for inputting integers (width of lines)
        String sentence = "This is a string"; // Sentence inputted for formatting
        String cont= "L"; // String for response to the type of formatting (L, R, C, B, Q)
        
        /* Introductory text */
        System.out.println("Welcome to the simple text formatter.");
            
        /* Inputs how long the user wants each line to be */
        System.out.println("Enter the line width:");
        width = inputInt.nextInt();
        /* Inputs the String the user wants to have formatted */
        System.out.println("Enter the text to be formatted:"); 
        sentence = inputStr.nextLine();
        /* Calculates length of the sentence inputted by user before tabs and extra spaces are removed */   
        length = sentence.length();
        /* Method deletes tabs and extra spaces from the String */
        sentence = trimSentence(sentence, length);
        /* Calculates length of the sentence inputted by user after the trimming of tabs and extra spaces */   
        length = sentence.length();
               
        /* Main Loop, runs until user enters "Q" */
        do{
        	/* Inputs how user wants the String to be formatted, or if he/she wants to quit */
            System.out.println("Select a format: (L)eft-adjusted, (R)ight-adjusted, (C)entered, (B)oth, or (Q)uit");
            cont = inputStr.nextLine();
            /* Method prints the numbers for each character in a row */
            printNumbers(width);
            
            /* Justify to left if user inputted "L" */
            if(cont.equals("L")){
                leftJustified(sentence, length, width);
            }
            /* Justify to right if user inputted "R" */
            else if(cont.equals("R")){
                rightJustified(sentence, length, width);
            }
            /* Justify to center if user inputted "C" */
            else if(cont.equals("C")){
                centerJustified(sentence, length, width);
            }
            /* Justify to both left and right if user inputted "B" */
            else if(cont.equals("B")){
                bothJustified(sentence, length, width);
            }
        /* If user inputted "Q", exit main loop */
        }while(!cont.equals("Q"));
    /* Closing text */
    System.out.println("Bye!");
    }
    
    
    
    
    public static void printNumbers(int w){
        int i=1; // Integer for displaying the numbers at the top for each character in the string
        int counter=0; // Integer for keeping track of how many numbers have been printed; loop closes when it gets to total length of the String
        
        /* Outputs numbers until the number of numbers printed is equal to the length of the String */
        do{
        	/* If i is 10, print "+" instead of number, restart i at 1, and add 1 to the counter (number of numbers outputted) */
            if(i == 10){
                System.out.print("+");
                i=1;
                counter++;
            }
            /* If i is not 10, output the number i, add 1 to it, and add 1 to counter (number of numbers outputted) */
            else{
                System.out.print(i);
                i++;
                counter++;
            }
        /* Exit loop when the counter (number of numbers) is equal to the total length of the String */
        }while(counter < w);
        /* Skips line */
        System.out.println();
    }
    
    
    
    
    public static String trimSentence(String sentenceSpaced, int totalLength){
        
        String sent = sentenceSpaced.replace('\t', ' '); // Replaces the tabs with spaces in the original String (sentenceSpaced) and assigns it to String sent
        int prevSpace = 99; // The last position a space was found in the String
        int j; // cuontineaontiainetaonoaent(#U)(U@#
        boolean blanksAtEnd = true; // If there are spaces at end of String
        
        /* Runs code while the position on the String i is not beyond the limits of the String, adds 1 each time */
        for(int i = 0; i < totalLength-1; i++){
        	/* If there are two spaces in a row, run the code */
            if((sent.charAt(i) == ' ') && (prevSpace == i-1)){
            	/* Substring s1 is the String before the second space */
                String s1 = sent.substring(0, i);
                /* Substring s2 is the String after the second space until the end */
                String s2 = sent.substring(i+1, totalLength);
                /* Sent is now the two substrings put together, essentially getting rid of the second space in the String */
                sent = s1 + s2;
                /* Total length of String decreases by one with the extraction of the second space */
                totalLength--;
                /* i is decreased by 1 to account for the shift in characters in the String with the extraction of the second space */
                i--;    
            }
            /* If there is a space and not a space before it, run code */
            else if((sent.charAt(i) == ' ') && (prevSpace != i-1)){
            	/* Set the position of prevSpace to current position with space */
                prevSpace = i;
            }
        }
        
        j = totalLength-1;
        /* Run code while there are spaces at the end of the String */
        while(blanksAtEnd == true){
        	
            if(sent.charAt(j) == ' '){
                sent = sent.substring(0, totalLength-1);
                totalLength--;
                j--;
            }
            else{
                blanksAtEnd = false;
            }
        }    
        
    return sent;    
    }
    
    
    
    
    public static void leftJustified(String sent, int totalLength, int totalWidth){
        String s1 = "something";
        int end=0, c=0;
        Boolean spaceFound = false;
        
        do{
            for(end = totalWidth; spaceFound == false; end--){
                c++;
                if(sent.charAt(end) == ' '){
                    s1 = sent.substring(0, end);
                    spaceFound = true;
                }   
            }
            System.out.println(s1);
            spaceFound = false;
            
            sent = sent.substring(end+2, totalLength);
            totalLength = sent.length();
            c=0;
        }while(totalWidth < totalLength);
        if(totalLength > 0){
            System.out.println(sent);
        }  
    }
    
    
    
    
    public static void rightJustified(String sent, int totalLength, int totalWidth){
        String s1 = "something";
        int end=0, c=0;
        Boolean spaceFound = false;
        int subLength, numSpaces;
    
        while(totalWidth < totalLength){
            for(end = totalWidth; spaceFound == false; end--){                
                c++;
                if(sent.charAt(end) == ' '){
                    s1 = sent.substring(0, end);
                    spaceFound = true;
                }   
            }
            subLength = s1.length();
            numSpaces = totalWidth - subLength;
            for(int i = 0; i < numSpaces; i++){
                System.out.print(" ");
            }
            
            System.out.println(s1);
            spaceFound = false;
            
            sent = sent.substring(end+2, totalLength);
            totalLength = sent.length();
            c=0;
        };
        if(totalLength > 0){
            subLength = sent.length();
            numSpaces = totalWidth - subLength;
            for(int i = 0; i < numSpaces; i++){
                System.out.print(" ");
            }
            System.out.println(sent);
        }    
    }
    
    
    
    
    public static void centerJustified(String sent, int totalLength, int totalWidth){
        String s1 = "something";
        int end=0, c=0;
        Boolean spaceFound = false;
        int subLength, numSpaces;
        
        while(totalWidth < totalLength){
            for(end = totalWidth; spaceFound == false; end--){                
                c++;
                if(sent.charAt(end) == ' '){
                    s1 = sent.substring(0, end);
                    spaceFound = true;
                }   
            }
            subLength = s1.length();
            numSpaces = totalWidth - subLength;
            
            for(int i = 0; i < numSpaces/2; i++){
                System.out.print(" ");
            }
            
            System.out.println(s1);
            spaceFound = false;
            
            sent = sent.substring(end+2, totalLength);
            totalLength = sent.length();
            c=0;
        };
        if(totalLength > 0){
            subLength = sent.length();
            numSpaces = totalWidth - subLength;
            for(int i = 0; i < numSpaces/2; i++){
                System.out.print(" ");
            }
            System.out.println(sent);
        }
        
    }
    
    
    
    
    public static void bothJustified(String sent, int totalLength, int totalWidth){
        String s1 = "something", s2 = "something else";
        int end=0, spacesBetweenWord, extraSpaces, lastSpace=0;
        Boolean spaceFound = false;
        int subLength, numSpaces, numBreaks = 0;
        
        while(totalWidth < totalLength){
            for(end = totalWidth; spaceFound == false; end--){                
                if(sent.charAt(end) == ' '){
                    s1 = sent.substring(0, end);
                    spaceFound = true;
                }   
            }
            subLength = s1.length();
            
            numSpaces=0;
            for(int n = 0; n < subLength; n++){
                if(sent.charAt(n) == ' '){
                    numSpaces++;
                }
            }
            numSpaces += (totalWidth - subLength);
            numBreaks = 0;
            
            for(int k = 0; k < subLength; k++){
                if(s1.charAt(k) == ' '){
                    numBreaks++;
                }
            }
            
            spacesBetweenWord = numSpaces / numBreaks;
            extraSpaces = numSpaces % numBreaks;
            
            System.out.println("LENGTH: " + subLength);
            System.out.println("NUM_SPACES: " + numSpaces);
            System.out.println("NUM_BREAKS: " + numBreaks);
            System.out.println("SPACES_BETWEEN_WORDS: " + spacesBetweenWord);
            System.out.println("EXTRA_SPACES: " + extraSpaces);
            
            for(int l = 0; l < subLength; l++){
                if(s1.charAt(l) == ' '){
                    s2 = s1.substring(lastSpace, l);
                    lastSpace = l;
                    System.out.print(s2);
                    for(int m = 0; m < spacesBetweenWord; m++){
                        System.out.print(" ");
                    }
                    if(extraSpaces > 0){
                        System.out.print(" ");
                    }
                    lastSpace = l;
                }
            }
            
            System.out.println();
            spaceFound = false;
            
            sent = sent.substring(end+2, totalLength);
            totalLength = sent.length();
            
        };
        if(totalLength > 0){
            subLength = sent.length();
            numSpaces = totalWidth - subLength;
            for(int i = 0; i < numSpaces/2; i++){
                System.out.print(" ");
            }
            System.out.println(sent);
        }
        
    }
    
}
