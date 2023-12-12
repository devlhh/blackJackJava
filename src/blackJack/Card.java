package blackJack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Card {
	private List<String> card = null;
	private String[] shape = {"Spade", "Diamond", "Clover", "Heart"};
	private int cardRowSize = 13;
	
	public Card() {
		card = new LinkedList<>();
		cardSet();
	}
	
	private void cardSet() {
		for(int i = 0 ; i < 4; i++) {
			createCard(i);
		}
	}
	
	private void createCard(int shapeIndex) {
		String shapeName;
		
		for(int i = 1; i <= cardRowSize; i++) {
			shapeName = this.shape[shapeIndex];

			if(i == 1) {
				shapeName = shapeName + " A";
			} else if(i == 11) {
				shapeName = shapeName + " J";
			} else if (i == 12) {
				shapeName = shapeName + " Q";
			} else if (i == 13) {
				shapeName = shapeName + " K";
			} else {
				shapeName = shapeName + " " + i;
			}
			card.add(shapeName);
		}
		Collections.shuffle(card);
	}
	
	public List<String> getAllCard() {
		return card;
	}
	
	public String drawCard() {
		// 카드뽑고 맨앞장은 제거
		String draw = card.get(0);
		card.remove(0);
		return draw;
	}
}
