package eightqueens;

import java.util.Scanner;

/**
 *120 minutes
 * @author The Dylan
 */
public class EightQueens {

    
    public static void main(String[] args) {
        int[][] board;
        board = new int[8][8];
        String positionWord;
        int x, y, pieces=0;
        char xChar, yChar;
        String fail="";
        
        for(int a = 0; a < 8; a++){
            for(int b = 0; b < 8; b++){
                board[a][b] = 0;
            }
        }
        
        displayBoard(board);
        
        do{
            positionWord = inputPos();
        
            positionWord = positionWord.toLowerCase();
        
            xChar = positionWord.charAt(1);
            yChar = positionWord.charAt(0);
            x = assignHorizVal(xChar);
            y = assignVertVal(yChar);
            
            if(x == 99 || y == 99){
            	System.out.println("Invalid position " + positionWord + ".");
            }
            else if(board[x][y] == 1){
                System.out.println("Removing piece from " + positionWord + ".");
                board[x][y] = 0;
                pieces--;
            }
            else{
                fail = checkVertFail(x, board);
                if(fail == ""){
                    fail = checkHorizFail(y, board);
                }
                if(fail == ""){   
                    fail = checkDiagFail(x, y, board);
                }
                if(fail == ""){
                    board[x][y] = 1;
                    pieces++;
                }
                else{
                    System.out.println("Illegal move at " + fail);
                }
           }
            displayBoard(board);
        }while(pieces <8);
        
        System.out.println("CONGRATULATIONS!!!");
        
    }
    
    
    
    public static String inputPos(){
        Scanner scan = new Scanner(System.in);
        String position;
        
        System.out.print("Enter the position of a queen piece: ");
        position = scan.nextLine();
        
        return position;
    }
    
    
    public static int assignHorizVal(char characterB){
        int xPos=0;
        
        if(characterB == '1'){
            xPos = 0;
        }
        else if(characterB == '2'){
            xPos = 1;
        }
        else if(characterB == '3'){
            xPos = 2;
        }
        else if(characterB == '4'){
            xPos = 3;
        }
        else if(characterB == '5'){
            xPos = 4;
        }
        else if(characterB == '6'){
            xPos = 5;
        }
        else if(characterB == '7'){
            xPos = 6;
        }
        else if(characterB == '8'){
            xPos = 7;
        }
        else{
        	xPos = 99;
        }
        
        return xPos;
    }
    
    
    public static int assignVertVal(char characterA){
        int yPos=0;
        
        if(characterA == 'a'){
            yPos = 0;
        }
        else if(characterA == 'b'){
            yPos = 1;
        }
        else if(characterA == 'c'){
            yPos = 2;
        }
        else if(characterA == 'd'){
            yPos = 3;
        }
        else if(characterA == 'e'){
            yPos = 4;
        }
        else if(characterA == 'f'){
            yPos = 5;
        }
        else if(characterA == 'g'){
            yPos = 6;
        }
        else if(characterA == 'h'){
            yPos = 7;
        }
        else{
        	yPos = 99;
        }
        
        return yPos;
    }
    
    
    public static String checkVertFail(int xVal, int[][] chessBoard){
        int yVal;
        
        for(yVal=0; yVal<8; yVal++){
            if(chessBoard[xVal][yVal] == 1){
                return checkError(xVal, yVal);
            }
        }
        
        return "";
    }
    
    
    public static String checkHorizFail(int yVal, int[][] chessBoard){
        int xVal;
        
        for(xVal=0; xVal<8; xVal++){
            if(chessBoard[xVal][yVal] == 1){
                return checkError(xVal, yVal);
            }
        }
        
        return "";
    }
    
    public static String checkDiagFail(int xVal, int yVal, int[][] chessBoard){
        int tempX, tempY, xAdd, yAdd, i = 0;
        
        tempX = xVal+1; tempY = yVal+1;
        while(tempX != 8 && tempY != 8){
            if(chessBoard[tempX][tempY] == 1){
                return checkError(tempX, tempY);
            }
            
            tempX++;
            tempY++;
        }
        
        tempX = xVal+1; tempY = yVal-1; 
        while(tempX != 8 && tempY != -1){
            if(chessBoard[tempX][tempY] == 1){
                return checkError(tempX, tempY);
            }
            
            tempX++;
            tempY--;
        }
        
        tempX = xVal-1; tempY = yVal+1;
        while(tempX != -1 && tempY != 8){
            if(chessBoard[tempX][tempY] == 1){
                return checkError(tempX, tempY);
            }
            
            tempX--;
            tempY++;
        }
        
        tempX = xVal-1; tempY = yVal-1;
        while(tempX != -1 && tempY != -1){
            if(chessBoard[tempX][tempY] == 1){
                return checkError(tempX, tempY);
            }
            
            tempX--;
            tempY--;
        }
        
        return "";
    }
    
    public static void displayBoard(int[][] boardDisp){
        int c, d;
        
        System.out.println("  a b c d e f g h");
        
        for(c=7; c>=0; c--){
            System.out.print((c+1) + " ");
            for(d=0; d<8; d++){
                if(boardDisp[c][d] == 1){
                    System.out.print("Q ");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println((c+1));
        }
        
        System.out.println("  a b c d e f g h");
    }
    
    public static String checkError(int x, int y){
    	String errorPstn;
    	String yPos="", xPos="";
    	
    	
    	if(y == 0){
    		yPos = "a";
    	}
    	else if(y == 1){
    		yPos = "b";
    	}
    	else if(y == 2){
    		yPos = "c";
    	}
    	else if(y == 3){
    		yPos = "d";
    	}
    	else if(y == 4){
    		yPos = "e";
    	}
    	else if(y == 5){
    		yPos = "f";
    	}
    	else if(y == 6){
    		yPos = "g";
    	}
    	else if(y == 7){
    		yPos = "h";
    	}
    	
    	if(x == 0){
    		xPos = "1";
    	}
    	else if(x == 1){
    		xPos = "2";
    	}
    	else if(x == 2){
    		xPos = "3";
    	}
    	else if(x == 3){
    		xPos = "4";
    	}
    	else if(x == 4){
    		xPos = "5";
    	}
    	else if(x == 5){
    		xPos = "6";
    	}
    	else if(x == 6){
    		xPos = "7";
    	}
    	else if(x == 7){
    		xPos = "8";
    	}
    	
    	errorPstn = yPos + xPos;
    	
    	return errorPstn;
    }
        
}
