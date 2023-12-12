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
	      dealer = new Dealer("����", initDealerMoney);
	      gamer = new Gamer(name, initPlayerMoney);
	      gameRule = new GameRule();
	   }
		   
	   public void play() {
	      System.out.println("===============Black Jack==================");
	      System.out.print("�����ϱ�(1) �����ϱ�(2) >> ");
	      Scanner sc = new Scanner(System.in);
	      String gameSelect = sc.nextLine();
	      
	      // ��������
	      if(gameSelect.equals("2")) {
	    	  sc.close();
	    	  return;
	      }
	      
	      // ���ӽ���
	      if(gameSelect.equals("1")) {
	         System.out.println("Game Start");
	         System.out.print("�÷��̾� �̸��� �Է�>> ");
	         name = sc.nextLine();
	         
	         initCreator();
	         
	         // Gamer, Dealer �� ���徿 2�� ������
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
	      //�ʱ⿡ ��¯�� �ι� �޾ƾ���.
	      for(int i = 0; i < 2; i++) {
	         // ������ ī�� ����
	         String drawCard = dealer.handOutCards(this.card);
	         gamer.setCardDeck(drawCard);
	         gamer.setCardScore();
	         
	         drawCard = dealer.handOutCards(this.card);
	         dealer.setCardDeck(drawCard);
	         dealer.setCardScore();
	      }
	      // ī�� ���� �� �� �����ֱ�
	      showGamerDeck();
	      showDealerDeck();
	   }
	   
	   public void showGamerDeck() {
	      System.out.println(gamer.getName() + "���� ��");
	      List<String> gamerCard = gamer.getCardDeck();
	      System.out.print("[ ");
	      for(int i = 0; i < gamerCard.size(); i++) {
	         System.out.print(gamerCard.get(i) + " ");
	      }
	      System.out.println(" ]");
	      System.out.println();
	   }
	   
	   public void showDealerDeck() {      
	      System.out.println("���� ��");
	      List<String> dealerCard = dealer.getCardDeck();
	      System.out.print("[ ");
	      
	      for(int i = 0; i < dealerCard.size(); i++) {
	         System.out.print(dealerCard.get(i) + " ");
	      }
	      
	      // �÷��̾� Hit and Stay ������������ ù�常 open
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
		   // 17�̻����� Ȯ��
		   dealer.setOverSevenTeen(gameRule.getIsOverSevenTeen(dealer.getCardScore()));
		   
		   // 17���ϸ� ��� ī���
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










