import java.util.*;
public class BattleshipBoard extends Board {

   private Ship[] ships = new Ship[]{new AirCarrier(), new Battleship(), new Sub(), new Destroyer(), new LB()};
   private int shots = 50;
   private int hits = 0;
   private boolean isCheating = false;

   public BattleshipBoard() {
      super();
      placeShips();
   }
   public void clearBoard() {
      int[][] b = this.getBoard();
      for (int r = 0; r < b.length; r++) {
         for(int c = 0; c < b[0].length; c++) {
            b[r][c] = 0;
         }
      }
   }
   
   public void placeHorizontal(int limit, Ship s) {
      int[][] board = this.getBoard();
      int r = (int)(Math.random()*10);
      int c = (int)(Math.random()*(board[0].length-limit-1));
      int num = c+1;
      if(c == limit) {
         num = 0;
      }
      for(int i = 0; i < limit; i++) {
         board[r][c] = limit;
         if(s.getName().equals("Destroyer!")) {
            board[r][c] += 1;
         }
         else if (s.getHealth() >= 4) {
            board[r][c] += 1;
         }
         c++;         
      }
   }
   
   public void placeVertical(int limit, Ship s) {
      int[][] board = this.getBoard();
      int r = (int)(Math.random()*(board.length-limit-1));
      int c = (int)(Math.random()*10);
      int num = c+1;
      if(num == 10) {
         num = 0;
      }
      for(int i = 0; i < limit; i++) {
         board[r][c] = limit;
         if(s.getName().equals("Destroyer!")) {
            board[r][c] += 1;
         }
         else if (s.getHealth() >= 4) {
            board[r][c] += 1;
         }
         r++;         
      }
   }

/**
Remember: 0 and 1 are numbers for fog of war.
-1: A missed shot
0: Open water
1: A ship spot
2: A hit ship
*/   
   public void placeShips() {
      int[][] b = this.getBoard();
      while (true) {
         clearBoard();
         for(int ship = 0; ship < 5; ship++) {
            int limit = ships[ship].getHealth();
            Ship s = ships[ship];
            boolean vertical = (int)(Math.random()*2) == 1;
            if(vertical) {
               placeVertical(limit, s);
            }
            else {
               placeHorizontal(limit, s);
            }
         }
         int checker = 0;
         for(int r = 0; r < b.length; r++) {
            for(int c = 0; c < b[0].length; c++) {
               if(b[r][c] == 0) {
                  checker++;
               }
            }  
         }
         if(checker == 83) {
            break;
         }          
      }
   }
   
   public String checkShot(String coord) {
      if(coord.equals("F69")) {
         isCheating = true;
         return "Oh, guess the radar was just a little dirty. Now they're just sitting ducks.";
      }
      if(coord.length() < 2) {
         return "Um captain, I\'m not sure where that is.";
      }
      String caseCheck = coord.substring(0, 1).toUpperCase();
      char letter = caseCheck.charAt(0);
      int r = letter-65;
      int c = coord.charAt(1)-49;
      if(c == -1) {
         c = 9;
      }
      int[][] b = this.getBoard();
      boolean rowValid = r >= 0 && r < b.length; 
      boolean colValid;
      if(coord.length() > 2) {
         try {
            c += coord.charAt(2)-49;
            colValid = false;
         }
         catch(Exception e) {
            return "Um captain, I\'m not sure where that is.";
         }
      }
      colValid = c >= 0 && c < b[0].length;
      if(!(rowValid && colValid)) {
         return "The ships won\'t be that far out captain. No point firing there.";
      }
      if(b[r][c] == 0) {
         b[r][c] = -1;
         shots--;
         return "Nothing there captain. That\'s a miss.";
      }
      else if(b[r][c] == -1 || b[r][c] == 1) {
         return "Captain, we\'ve already shot there. No need to fire again.";
      }
      else {
         String s = "";
         if(b[r][c] == 2) {
            ships[4].setHealth(ships[4].getHealth()-1);
            s = ships[4].isSunk();
            b[r][c] = 1;
            hits++;
            shots--;
            return "Good shot captain! That\'s a hit! " + s;
         }
         else if(b[r][c] == 3) {
            ships[3].setHealth(ships[3].getHealth()-1);
            s = ships[3].isSunk();
            b[r][c] = 1;
            hits++;
            shots--;
            return "Good shot captain! That\'s a hit! " + s;
         }
         else if(b[r][c] == 4) {
            ships[2].setHealth(ships[2].getHealth()-1);
            s = ships[2].isSunk();
            b[r][c] = 1;
            hits++;
            shots--;
            return "Good shot captain! That\'s a hit! " + s;
         }
         else if(b[r][c] == 5) {
            ships[1].setHealth(ships[1].getHealth()-1);
            s = ships[1].isSunk();
            b[r][c] = 1;
            hits++;
            shots--;
            return "Good shot captain! That\'s a hit! " + s;
         }
         else {
            ships[0].setHealth(ships[0].getHealth()-1);
            s = ships[0].isSunk();
            b[r][c] = 1;
            hits++;
            shots--;
            return "Good shot captain! That\'s a hit! " + s;
         }
      }  
   }
   
   public int getShots() {
      return shots;
   }
   
   public int getHits() {
      return hits;
   }

   public String toString() {
      int[][] b = this.getBoard();
      char x = 65;
      if(shots == 0) {
         isCheating = true;
      }
      String S = "    1   2   3   4   5   6   7   8   9   0\n";
      for(int r = 0; r < b.length; r++) {
         S += x + "   ";
         for(int c = 0; c < b[0].length; c++) {
            if(b[r][c] == -1) {
               S += "M   ";
            }
            else if (b[r][c] == 1) {
               S += "H   ";
            }
                        
            else {
               if(isCheating) {
                  S += b[r][c] + "   ";
               }
               else {
                  S += "@   ";
               }
            }
         }
         S += "\n";
         x++;
      }
      return S;
   }
}