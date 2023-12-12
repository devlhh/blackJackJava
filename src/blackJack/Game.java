package blackJack;

import java.util.List;
import java.util.Scanner;

public class Game {
	   private Gamer gamer = null;
	   private Dealer dealer = null;
	   private GameRule gameRule = null;
	   private Card card = null;
	   private int initPlayerMoney = 1000000;
	   private int initDealerMoney = 50000000;
	   private String name;
	   private boolean dealerCardOpen = false;
		   
	   public void initCreator() {
	      card = new Card();
	      dealer = new Dealer("딜러", initDealerMoney);
	      gamer = new Gamer(name, initPlayerMoney);
	      gameRule = new GameRule();
	   }
		   
	   public void play() {
	      System.out.println("===============Black Jack==================");
	      System.out.print("시작하기(1) 종료하기(2) >> ");
	      Scanner sc = new Scanner(System.in);
	      String gameSelect = sc.nextLine();
	      
	      // 게임종료
	      if(gameSelect.equals("2")) {
	    	  sc.close();
	    	  return;
	      }
	      
	      // 게임시작
	      if(gameSelect.equals("1")) {
	         System.out.println("Game Start");
	         System.out.print("플레이어 이름을 입력>> ");
	         name = sc.nextLine();
	         
	         initCreator();
	         
	         // Gamer, Dealer 패 한장씩 2번 돌리기
	         initReceive();
	         gamerTurn();
	    	 dealerTurn();
	    	 
	    	 if(dealer.getIsBust()) {
	    		 System.out.println("Gamer win");
	    	 } else {
	    		 gameResult();
	    	 }
	      }
	   }
	   
	   public void initReceive() {
	      //초기에 한짱식 두번 받아야함.
	      for(int i = 0; i < 2; i++) {
	         // 딜러가 카드 뽑음
	         String drawCard = dealer.handOutCards(this.card);
	         gamer.setCardDeck(drawCard);
	         gamer.setCardScore();
	         
	         drawCard = dealer.handOutCards(this.card);
	         dealer.setCardDeck(drawCard);
	         dealer.setCardScore();
	      }
	      // 카드 돌린 후 덱 보여주기
	      showGamerDeck();
	      showDealerDeck();
	   }
	   
	   public void showGamerDeck() {
	      System.out.println(gamer.getName() + "님의 덱");
	      List<String> gamerCard = gamer.getCardDeck();
	      System.out.print("[ ");
	      for(int i = 0; i < gamerCard.size(); i++) {
	         System.out.print(gamerCard.get(i) + " ");
	      }
	      System.out.println(" ]");
	      System.out.println();
	   }
	   
	   public void showDealerDeck() {      
	      System.out.println("딜러 덱");
	      List<String> dealerCard = dealer.getCardDeck();
	      System.out.print("[ ");
	      
	      for(int i = 0; i < dealerCard.size(); i++) {
	         System.out.print(dealerCard.get(i) + " ");
	      }
	      
	      // 플레이어 Hit and Stay 끝나기전까진 첫장만 open
//		      if (!dealerCardOpen) {
//		         for(int i = 0; i < dealerCard.size(); i++) {
//		            if (i == 0) {               
//		               System.out.print(dealerCard.get(i) + " ");
//		            } else {
//		               System.out.print("?????" + " ");
//		            }
//		         }
//		      } else {         
//		         for(int i = 0; i < dealerCard.size(); i++) {
//		            System.out.print(dealerCard.get(i) + " ");
//		         }
//		      }
	      System.out.println("]");
	      System.out.println();
	   }
	   
	   public void gamerTurn() {
	      Scanner sc = new Scanner(System.in);
	      
	      System.out.print("Hit(1) Stay(2)>> ");
	      String gamerChoice = sc.nextLine();
	      
	      if(gamerChoice.equals("1")) {   
	         while(!gamerChoice.equals("2")) {
	            
	            String drawCard = dealer.handOutCards(card);
	            gamer.setCardDeck(drawCard);
	            gamer.setCardScore();
	            showGamerDeck();
	            
	            gamer.setBust(gameRule.getIsBust(gamer.getCardScore(), "gamer"));
	            if(gamer.getBust()) {
	            	sc.close();
	            	return;
	            }
	            
	            System.out.print("Hit(1) Stay(2)>> ");
	            gamerChoice = sc.nextLine();
	         }
	      }
	      if(gamerChoice.equals("2")) {
	    	  sc.close();
	    	  return;
	      }
	   }
	   
	   public void dealerTurn() {
		   // 17이상인지 확인
		   dealer.setOverSevenTeen(gameRule.getIsOverSevenTeen(dealer.getCardScore()));
		   
		   // 17이하면 계속 카드뽑
		   while(!dealer.getOverSevenTeen()) {
			   String drawCard = dealer.handOutCards(card);
			   dealer.setCardDeck(drawCard);
			   dealer.setCardScore();
			   showDealerDeck();
			   dealer.setOverSevenTeen(gameRule.getIsOverSevenTeen(dealer.getCardScore()));
		   }
		   
		   // bust check
		   dealer.setIsBust(gameRule.getIsBust(dealer.getCardScore(), "dealer"));
		   
		   if(dealer.getIsBust()) {
			   return;
		   }
	   }
	   
	   public void gameResult() {
		   boolean result = gameRule.gameResult(gamer.getCardScore(), dealer.getCardScore());
		   
		   if(result && !gamer.getBust()) {
			   System.out.println("Gamer win !!");
		   } else {
			   System.out.println("Delaer win !!");
		   }
	   }
	}










