package blackJack;

public class GameRule {
	   private boolean isBust;
	   private boolean isOverSevenTeen;
	   
	   public GameRule() {
	      this.isBust = false;
	      this.isOverSevenTeen = false;
	   }
	   
	   public boolean getIsBust(int score, String players) {
		   String player = players;
	      if (score > 21) {
	         isBust = true;
	         System.out.println(player + "Bust !!");
	      } else {
	         isBust = false;
	      }
	      return isBust;
	   }
	   
	   public boolean getIsOverSevenTeen(int score) {
		   if (score > 17) {
			   this.isOverSevenTeen = true;
			   System.out.println();
		   } else {
			   this.isOverSevenTeen = false;
			   System.out.println();
		   }
		   
		   return this.isOverSevenTeen;
	   }
	   
	   public boolean gameResult(int gamerScore, int dealerScore) {
		   int gamerMin = 21 - gamerScore;
		   int dealerMin = 21 - dealerScore;
		   
		   if(gamerMin < dealerMin) {
			   return true;
		   } else {
			   return false;
		   }
	   }
	}

