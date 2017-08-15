package minor.bean;

public class CardInfoBean {
	private String  CardColor;

	private int cardImage;

	public String getCardColor() {
		return CardColor;
	}

	public int getCardImage() {
		return cardImage;
	}

	public void setCardImage(int cardImage) {
		this.cardImage = cardImage;
	}

	public void setCardColor(String cardColor) {
		CardColor = cardColor;
	}

	public int getCardValue() {
		return cardValue;
	}

	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}

	private int cardValue;

}
