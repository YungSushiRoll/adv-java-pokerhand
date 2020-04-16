package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Josh Gossett, Taylor Joseph, Christopher Guerrero
 */

public class Poker {

    public static void main(String[] args) {

        List<PokerCard> hand = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            int cardNum = (rand.nextInt(13) + 2);
            int cardSuit = (rand.nextInt(4));
            PokerCard card = new PokerCard(cardSuit,cardNum);
            hand.add(card);
        }

        for (int i = 0; i < hand.size() - 1; i++)
        {
            for (int j = 0; j < hand.size() - 1; j++)
            {
                if (hand.get(j).getNumber() > hand.get(j + 1).getNumber()) {
                    PokerCard temp = hand.get(j);
                    hand.set(j, hand.get(j+1));
                    hand.set(j + 1,temp);
                }
            }
        }

        for (PokerCard p : hand)
        {
            System.out.println(p.getSuit() + " " + p.getNumber());
        }

        testHand(hand);


    }

    /**
     * This takes in a list of cards and does various tests to see what
     * kind of poker hand you have
     *
     * @author Josh Gossett, Taylor Joseph, Christopher Guerrero
     * @param handList
     */

    public static void testHand(List<PokerCard> handList){
        boolean isRoyalFlush = false;
        boolean isFourOfAKind = false;
        boolean isFlush = false;
        boolean isTwoPair = false;
        boolean isAPair = false;

        if ((handList.get(0).getNumber() == 10
                && handList.get(1).getNumber() == 11
                && handList.get(2).getNumber() == 12
                && handList.get(3).getNumber() == 13
                && handList.get(4).getNumber() == 14) &&
            handList.get(0).getSuit() == handList.get(1).getSuit()
                && handList.get(1).getSuit() == handList.get(2).getSuit()
                && handList.get(2).getSuit() == handList.get(3).getSuit()
                && handList.get(3).getSuit() == handList.get(4).getSuit())
        {
            isRoyalFlush = true;
            if (isRoyalFlush)
            {
                System.out.println("Wow! A Royal Flush!");
            }
        } else if ((handList.get(0).getNumber() == handList.get(1).getNumber()
                && handList.get(1).getNumber() == handList.get(2).getNumber()
                && handList.get(2).getNumber() == handList.get(3).getNumber())
                || (handList.get(1).getNumber() == handList.get(2).getNumber()
                && handList.get(2).getNumber() == handList.get(3).getNumber()
                && handList.get(3).getNumber() == handList.get(4).getNumber()))
            {
            isFourOfAKind = true;
            if (isFourOfAKind)
            {
                System.out.println("Four of a kind! Not bad!");
            }
        } else if (handList.get(0).getSuit() == handList.get(1).getSuit()
            && handList.get(1).getSuit() == handList.get(2).getSuit()
            && handList.get(2).getSuit() == handList.get(3).getSuit()
            && handList.get(3).getSuit() == handList.get(4).getSuit())
        {
            isFlush = true;
            if (isFlush)
            {
                System.out.println("That's a Flush if I've ever seen one!");
            }
        } else if ((handList.get(0).getNumber() == handList.get(1).getNumber()
            && handList.get(2).getNumber() == handList.get(3).getNumber())
            || (handList.get(0).getNumber() == handList.get(1).getNumber()
            && handList.get(3).getNumber() == handList.get(4).getNumber())
            || (handList.get(2).getNumber() == handList.get(1).getNumber()
            && handList.get(4).getNumber() == handList.get(3).getNumber()))
        {
            isTwoPair = true;
            if (isTwoPair)
            {
                System.out.println("Look at those pairs!");
            }
        } else if (handList.get(0).getNumber() == handList.get(1).getNumber()
                || handList.get(1).getNumber() == handList.get(2).getNumber()
                || handList.get(2).getNumber() == handList.get(3).getNumber()
                || handList.get(3).getNumber() == handList.get(4).getNumber())
        {
            isAPair = true;
            if (isAPair)
            {
                System.out.println("Look! A Pair!");
            }
        } else {
            if (handList.get(4).getNumber() < 11) {
                System.out.println("Your high card is " + handList.get(4).getNumber());
            }

            if (handList.get(4).getNumber() == 11){
                System.out.println("Your high card is a Jack!");
            }

            if (handList.get(4).getNumber() == 12){
                System.out.println("Your high card is a Queen!");
            }

            if (handList.get(4).getNumber() == 13){
                System.out.println("Your high card is a King!");
            }

            if (handList.get(4).getNumber() == 14){
                System.out.println("Your high card is an Ace!");
            }

        }

    }
}
