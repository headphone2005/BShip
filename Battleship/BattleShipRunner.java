import java.util.Scanner;
public class BattleShipRunner {

   public static void main(String[] args) throws Exception {
   
      Scanner input = new Scanner(System.in);
      System.out.println("Captain! We\'ve found the zone of where the ships are located.");
      Thread.sleep(2000);
      System.out.println("However, our radars aren\'t effective enough to find their exact locations...");
      Thread.sleep(2000);
      System.out.println("We\'ve narrowed it to a 100 square mile area (10x10 board).");
      Thread.sleep(2000);
      System.out.println("And we only have 50 shots...");
      Thread.sleep(2000);
      System.out.println("Time is of the essence. They're getting ready to attack our home soon! Let\'s get firing captain!");
      Thread.sleep(2000);
      System.out.println("(Press Enter to Start)");
      input.nextLine();
      Board board = new BattleshipBoard();
      int shots = ((BattleshipBoard)board).getShots();
      String check = "";
      int hits = 0;      
      do {
         System.out.println();
         System.out.println(board);
         System.out.println(check);
         System.out.println("We have " + shots + " shots left");
         System.out.println("Where should we fire captain? (Letter then number!)");
         String shot = input.nextLine();
         check = ((BattleshipBoard)board).checkShot(shot);
         shots = ((BattleshipBoard)board).getShots();
         hits = ((BattleshipBoard)board).getHits();
         if (hits == 17) {
            break;
         }
      } while(shots != 0);
         System.out.println(board);
         if (hits == 17) {
            System.out.println("Congrats captain! We\'ve sunk all their ships!");
         }
         else {
            System.out.println("We put up a valiant fight captain...But we weren\'t fast enough...");
         }
   }

}