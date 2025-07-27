// Ebrima Ceesay
// CS 143
// HW #2: Sudoku #2 (isValid, isSolved)
// HW #2 Core Topics: 2D arrays, Sets, Maps, Sudoku validation
//
// In this program a sudoku board validator is provided. 

import java.util.*;
import java.io.*;


// It ensures that a 9x9 sudoku board meets the standard game rules: 
// no duplicate digits along rows, columns or mini grids 3x3. 
// It also checks whether the board is solved completely (has each of the digits in 1-9 each exhibiting nine times).
public class MySudokuBoard {
    public final int SIZE = 9;
    protected char[][] myBoard;
   // Pre: theFile is a valid filename to the path of the 9x9 sudoku file whose line contains 9 characters
   // Post: Initializes myBoard from the contents of the file
    public MySudokuBoard(String theFile) {
        myBoard = new char[SIZE][SIZE];
        try {
            Scanner file = new Scanner(new File(theFile));
            for (int row = 0; row < SIZE; row++) {
                String theLine = file.nextLine();
                for (int col = 0; col < theLine.length(); col++) {
                    myBoard[row][col] = theLine.charAt(col);
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }
    
   // Pre: myBoard is a 9 x9 array of character initialized
   // Post: Returns true when there are no invalid characters on the board and that the board follows the rules of the game of Sudoku
    public boolean isValid() {
        return isValidChars() && isValidRows() && isValidCols() && isValidGrids();
    }
   // Pre: myBoard is full of characters
   // Post: Returns true when each of the characters consists of 1-9 or '.'
    private boolean isValidChars() {
        Set < Character > checkChars = new HashSet < > (Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '.'));
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!checkChars.contains(myBoard[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }

   // Pre: myBoard is valid 9x9 array of characters
   // Post: Returns true if no rows contains duplicate digits 1-through 9
    private boolean isValidRows() {
        for (int row = 0; row < SIZE; row++) {
            Set < Character > listSet = new HashSet < > ();
            for (int col = 0; col < SIZE; col++) {
                char ch = myBoard[row][col];
                if (ch != '.') {
                    if (listSet.contains(ch)) return false;
                    listSet.add(ch);
                }
            }
        }
        return true;
    }

   // Pre: myBoard is valid 9x9 array of characters
   // Post: Returns true if no columns contains duplicate digits 1-through 9
    private boolean isValidCols() {
        for (int col = 0; col < SIZE; col++) {
            Set < Character > listSet = new HashSet < > ();
            for (int row = 0; row < SIZE; row++) {
                char getCh = myBoard[row][col];
                if (getCh != '.') {
                    if (listSet.contains(getCh)) return false;
                    listSet.add(getCh);
                }
            }
        }
        return true;
    }
   // Pre: myBoard is valid 9x9 array of characters
   // Post: Returns true if all 3x3 subgrids contains duplicate digits 1-through 9

    private boolean isValidGrids() {
        for (int sRow = 0; sRow < SIZE; sRow += 3) {
            for (int sCol = 0; sCol < SIZE; sCol += 3) {
                Set < Character > listSet = new HashSet < > ();
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        char getCh = myBoard[sRow + row][sCol + col];
                        if (getCh != '.') {
                            if (listSet.contains(getCh)) return false;
                            listSet.add(getCh);
                        }
                    }
                }
            }
        }
        return true;
    }
   // Pre: myBoard initializes and is valid
   // Post: It returns true on seeing that board is cleared out (all digits 1-9 appear in the board exactly 9 times)
    public boolean isSolved() {
        if (!isValid()) return false;

        Map < Character, Integer > count = new HashMap < > ();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char getCh = myBoard[row][col];
                if (getCh == '.') return false;
                count.put(getCh, count.getOrDefault(getCh, 0) + 1);
            }
        }

        for (char c = '1'; c <= '9'; c++) {
            if (count.getOrDefault(c, 0) != 9) return false;
        }

        return true;
    }
   // Pre: myBoard initializes and is valid
   // Post: String representation of the board returns
    public String toString() {
        String result = "My Board:\n\n";
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                result += (myBoard[row][col]);
            }
            result += ("\n");
        }
        return result;
    }

}

