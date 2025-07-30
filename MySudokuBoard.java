// Ebrima Ceesay
// CS 143
// HW #3: Sudoku #3 (solve)
//  HW #3: Core Topics: recursive backtracking, 2D arrays, set/hashset   

import java.util.*;
import java.io.*;

//   This program define a class to hold a Sudoku board and has functions that:
//   - Determine whether a board is solved or not
//   - Puzzle recursively solved by backtracking
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
    // Pre: myBoard initializes and is valid
    // Post: It returns true on seeing that board is cleared out (all digits 1-9 appear in the board exactly 9 times)

    public boolean isSolved() {
        if (!isValid()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char[] row : myBoard) {
            for (char cell : row) {
                if (map.containsKey(cell)) {
                    map.put(cell, map.get(cell) + 1);
                } else {
                    map.put(cell, 1);
                }
            }
        }
        // info on Collections: https://docs.oracle.com/javase/8/docs/api/?java/util/Collections.html
        return map.keySet().size() == 9 && Collections.frequency(map.values(), 9) == 9;
    }
    // Pre: myBoard is a 9 x9 array of character initialized
    // Post: Returns true when there are no invalid characters on the board and that the board follows the rules of the game of Sudoku
    public boolean isValid() {
        // checks for bad data
        for (char[] row : myBoard) {
            for (char cell : row) {
                if (cell != '.' && (cell < '1' || cell > '9')) {
                    return false;
                }
            }
        }

        // checks for row/col violations
        for (int r = 0; r < myBoard.length; r++) {
            Set<Character> trackingRow = new HashSet<>();
            Set<Character> trackingCol = new HashSet<>();
            for (int c = 0; c < myBoard[r].length; c++) {
                // check for row violation
                if (trackingRow.contains(myBoard[r][c])) {
                    return false;
                } else if (myBoard[r][c] != '.') {
                    trackingRow.add(myBoard[r][c]);
                }

                // check for col violation
                if (trackingCol.contains(myBoard[c][r])) {
                    return false;
                } else if (myBoard[c][r] != '.') {
                    trackingCol.add(myBoard[c][r]);
                }
            }
        }

        // check for mini-squares
        for (int square = 1; square <= 9; square++) {
            char[][] mini = miniSquare(square);
            Set<Character> trackingMini = new HashSet<>();
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) // check for mini violation
                {
                    if (trackingMini.contains(mini[r][c])) {
                        return false;
                    } else if (mini[r][c] != '.') {
                        trackingMini.add(mini[r][c]);
                    }
                }
            }
        }

        // if there weren't any violations above...
        return true;
    }
    // A helper method that returns the 3x3 mini-square numbered starting at 1 to the left to the right, top to bottom.
    // pre: spot is in range 1-9
    // post: Returns a 3x3 array of character representing the mini-square

    private char[][] miniSquare(int spot) {
        char[][] mini = new char[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                // whoa - wild! This took me a solid hour to figure out.
                // This translates between the "spot" in the 9x9 Sudoku board
                // and a new mini square of 3x3
                mini[r][c] = myBoard[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
            }
        }
        return mini;
    }
    //Returns the string representation of the current board.
    // pre: Board is initialized
    // post: Displays a string that describes the 9x9 game layout

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
