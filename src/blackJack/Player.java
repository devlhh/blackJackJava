package blackJack;

import java.util.List;

public interface Player {
	   public int getMoney();
	   public void setCardDeck(String drawCard);
	   public List<String> getCardDeck();
	   public void setCardScore();
	   public int getCardScore();
	}
