// Ebrima Ceesay
// CS 143
// HW #2: Sudoku #2 (isValid, isSolved)
// HW #2 Core Topics: 2D arrays, Sets, Maps, Sudoku validation
//
// In this program a sudoku board validator is provided. 
// It ensures that a 9x9 sudoku board meets the standard game rules: 
// no repeating digits along rows, columns or mini grids 3x3. 
// It also checks whether the board is solved completely (has each of the digits in 1-9 each exhibiting nine times).


/*
   This is started code for Crystal's Sudoku #2.
   The code is not pretty, but it works.
*/

import java.util.*;
import java.io.*;

public class MySudokuBoard {
    public final int SIZE = 9;
    protected char[][] myBoard;

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
    public boolean isValid() {
        return isValidChars() && isValidRows() && isValidCols() && isValidGrids();
    }

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

