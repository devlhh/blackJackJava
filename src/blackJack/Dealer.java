package blackJack;

import java.util.LinkedList;
import java.util.List;

public class Dealer implements Player{

	   private String name;
	   private int money = 0;
	   private List<String> cardDeck = null;
	   private int totalScore = 0;
	   private int cardIndex = 0;
	   private boolean overSevenTeen = false;
	   private boolean isBust = false;

	   public Dealer(String name, int initMoney) {
	      this.name = name;
	      this.money = initMoney;
	      cardDeck = new LinkedList<>();
	   }

	   @Override
	   public void setCardDeck(String drawCard) {
	      cardDeck.add(drawCard);
	   }

	   @Override
	   public int getMoney() {
	      return money;
	   }

	   // µô·¯°¡ Ä«µå »Ì±â
	   public String handOutCards(Card card) {
	      String drawCard = card.drawCard();
	      return drawCard;
	   }

	   @Override
	   public List<String> getCardDeck() {
	      return cardDeck;
	   }

	   @Override
	   public void setCardScore() {
		   int score = 0;
	         if(cardDeck.get(cardIndex).contains("A")) {
	            score = 1;
	         } else if (cardDeck.get(cardIndex).contains("J") || cardDeck.get(cardIndex).contains("Q") || cardDeck.get(cardIndex).contains("K")) {
	            score = 10;
	         } else {
	        	 String deck = cardDeck.get(cardIndex);
	        	 deck = deck.replaceAll("[^0-9]", "");
	        	 score = Integer.valueOf(deck);
	         }
	         this.totalScore += score;
	         cardIndex++; 
	   }

	   @Override
	   public int getCardScore() {
	      return totalScore;
	   }
	   
	   public void setOverSevenTeen(boolean over) {
		   this.overSevenTeen = over;
	   }
	   
	   public boolean getOverSevenTeen() {
		   return this.overSevenTeen;
	   }
	   
	   public void setIsBust(boolean bust) {
		   this.isBust = bust;
	   }
	   
	   public boolean getIsBust() {
		   return this.isBust;
	   }
	}