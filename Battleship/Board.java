public class Board {

   private int[][] Board;
   
   public Board() {
   
      Board = new int[10][10];
   
   }
   
   public void setValue(int r, int c, int value) {
      Board[r][c] = value;
   }
   
   public int getValue(int r, int c) {
      return Board[r][c];
   } 
   
   public int[][] getBoard() {
      return Board;
   }
   
   public String toString() {
      String S = "";
      for(int r = 0; r < Board.length; r++) {
         for(int c = 0; c < Board[0].length; c++) {
            S += Board[r][c];
         }
         S += "\n";
      }
      return S;
   }

}