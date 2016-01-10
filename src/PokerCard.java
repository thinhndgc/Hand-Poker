
public class PokerCard {
	private int cardNumber;
    private int cardSuit;

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(int cardSuit) {
        this.cardSuit = cardSuit;
    }

    public PokerCard(int cardNumber, int cardSuit) {
        this.cardNumber = cardNumber;
        this.cardSuit = cardSuit;
    }

    public PokerCard() {
    }
}
