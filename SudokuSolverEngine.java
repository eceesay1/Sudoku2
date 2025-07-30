// Ebrima Ceesay
// CS 143
// HW #3: Sudoku #3 (solve)
//  HW #3: Core Topics: recursive backtracking, 2D arrays, set/hashset
  
//Tests different Sudoku boards files by loading it into the MySudokuBoard,
// show the status of their validity and trying to solve the board.
public class SudokuSolverEngine {
 
   //pre: Corresponding .sdk files (such as "empty.sdk", "valid-incomplete.sdk", etc.)
   // has to be current in the project directory.
   // post: show results to the console: test results, board with the solved board.
   public static void main(String[] args) {
      MySudokuBoard board = new MySudokuBoard("boards/very-fast-solve.sdk");

      System.out.println("Initial board:");
      System.out.println(board);

      if (!board.isValid()) {
         System.out.println("Board does not have a solution and is invalid.");
      } else if (board.isSolved()) {
         System.out.println("Board is already solved.");
      } else {
         System.out.print("Solving board...");
         long start = System.currentTimeMillis();
         boolean solvedSolution = board.solve();
         long stop = System.currentTimeMillis();

         if (solvedSolution) {
            System.out.printf("SOLVED in %.3f seconds.\n", ((stop - start) / 1000.0));
            System.out.println("\nSolved board:");
            System.out.println(board);
         } else {
            System.out.printf("FAILED to solve in %.3f seconds.\n", ((stop - start) / 1000.0));
         }
      }
   }
}

// Output

/*
 ----jGRASP exec: java SudokuSolverEngine
 Initial board:
 My Board:
 
 .34678912
 .72195348
 198342567
 ..9.61423
 .26853791
 .13924.56
 .61537284
 .8.419635
 345.86179
 
 Solving board...SOLVED in 0.012 seconds.
 
 Solved board:
 My Board:
 
 534678912
 672195348
 198342567
 859761423
 426853791
 713924856
 961537284
 287419635
 345286179
 
  ----jGRASP: Operation complete.

*/