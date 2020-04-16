package Main;

/**
 *
 * @author Joshua Gossett, Taylor Joseph, Christopher Joseph
 */

public class PokerCard {

    private int suit, number;

    public PokerCard(int suit, int number){
        this.suit = suit;
        this.number = number;
    }

    /**
     * gets card suit
     * @return
     */
    public int getSuit() {
        return suit;
    }

    /**
     * gets card number
     * @return
     */
    public int getNumber() {
        return number;
    }
}
