package Main;

import java.util.*;

/**
 * @author Josh Gossett, Taylor Joseph, Christopher Guerrero
 */

public class Poker {

    private static int cheatCount = 0;
    private static int gameCount = 0;

    // Track how many times each hand was generated
    private static Map<String, Integer> handCount = new HashMap<>();

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        String quit = null;

        do {
            play();
            System.out.println("Enter C to continue, Q to quit");
            quit = keyboard.nextLine().toUpperCase();
        } while (!quit.equals("Q"));

        System.out.println("Total games: " + gameCount);
        System.out.println("Total duplicate cards detected: " + cheatCount);
        System.out.println();
        for (String handType : handCount.keySet()) {
            System.out.println(handType + ": " + handCount.get(handType));
        }
    }

    public static void play() {
        gameCount++;

        // Contains Strings in the form of suit + number, such as "014".
        // When a new card is randomly generated, check to make
        // sure that its combination of suit and number doesn't already
        // exist.
        Set<String> cardsInHand = new HashSet<>();

        List<PokerCard> hand = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            PokerCard card = null;

            // Create a random number/suit combo
            // until one is created that doesn't
            // already exist
            do {
                int cardNum = (rand.nextInt(13) + 2);
                int cardSuit = (rand.nextInt(4));
                card = new PokerCard(cardSuit, cardNum);
            } while (isDuplicateCard(cardsInHand, card));

            hand.add(card);
        }

        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = 0; j < hand.size() - 1; j++) {
                if (hand.get(j).getNumber() > hand.get(j + 1).getNumber()) {
                    PokerCard temp = hand.get(j);
                    hand.set(j, hand.get(j + 1));
                    hand.set(j + 1, temp);
                }
            }
        }

        for (PokerCard p : hand) {
            System.out.println(p.getSuit() + " " + p.getNumber());
        }

        testHand(hand);
    }

    private static void logHand(String handType) {
        // If this is the first time this hand came up,
        // initialize counter to zero
        if (!handCount.containsKey(handType)) {
            handCount.put(handType, 0);
        }
        // Add one to counter
        int newCount = handCount.get(handType) + 1;

        // Overwrite old counter with increased counter
        handCount.put(handType, newCount);
    }

    public static boolean isDuplicateCard(Set<String> cardsInHand, PokerCard card) {
        // Concat suit and number as a string (don't sum as numbers)
        String cardAsString = Integer.toString(card.getSuit()) + Integer.toString(card.getNumber());
        // Does the set already contain this card?
        boolean exists = cardsInHand.contains(cardAsString);
        // New card, add to set
        if (!exists) {
            cardsInHand.add(cardAsString);
        } else {
            System.out.println("Cheating detected! " + cardAsString);
            cheatCount++;
        }
        // Return whether this was a duplicate card
        return exists;
    }

    /**
     * This takes in a list of cards and does various tests to see what
     * kind of poker hand you have
     *
     * @param handList
     * @author Josh Gossett, Taylor Joseph, Christopher Guerrero
     */

    public static void testHand(List<PokerCard> handList) {
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
                && handList.get(3).getSuit() == handList.get(4).getSuit()) {
            isRoyalFlush = true;
            if (isRoyalFlush) {
                System.out.println("Wow! A Royal Flush!");
                logHand("Royal Flush");
            }
        } else if ((handList.get(0).getNumber() == handList.get(1).getNumber()
                && handList.get(1).getNumber() == handList.get(2).getNumber()
                && handList.get(2).getNumber() == handList.get(3).getNumber())
                || (handList.get(1).getNumber() == handList.get(2).getNumber()
                && handList.get(2).getNumber() == handList.get(3).getNumber()
                && handList.get(3).getNumber() == handList.get(4).getNumber())) {
            isFourOfAKind = true;
            if (isFourOfAKind) {
                System.out.println("Four of a kind! Not bad!");
                logHand("Four of a kind");
            }
        } else if (handList.get(0).getSuit() == handList.get(1).getSuit()
                && handList.get(1).getSuit() == handList.get(2).getSuit()
                && handList.get(2).getSuit() == handList.get(3).getSuit()
                && handList.get(3).getSuit() == handList.get(4).getSuit()) {
            isFlush = true;
            if (isFlush) {
                System.out.println("That's a Flush if I've ever seen one!");
                logHand("Flush");
            }
        } else if ((handList.get(0).getNumber() == handList.get(1).getNumber()
                && handList.get(2).getNumber() == handList.get(3).getNumber())
                || (handList.get(0).getNumber() == handList.get(1).getNumber()
                && handList.get(3).getNumber() == handList.get(4).getNumber())
                || (handList.get(2).getNumber() == handList.get(1).getNumber()
                && handList.get(4).getNumber() == handList.get(3).getNumber())) {
            isTwoPair = true;
            if (isTwoPair) {
                System.out.println("Look at those pairs!");
                logHand("Two Pair");
            }
        } else if (handList.get(0).getNumber() == handList.get(1).getNumber()
                || handList.get(1).getNumber() == handList.get(2).getNumber()
                || handList.get(2).getNumber() == handList.get(3).getNumber()
                || handList.get(3).getNumber() == handList.get(4).getNumber()) {
            isAPair = true;
            if (isAPair) {
                System.out.println("Look! A Pair!");
                logHand("Pair");
            }
        } else {
            if (handList.get(4).getNumber() < 11) {
                System.out.println("Your high card is " + handList.get(4).getNumber());
            }

            if (handList.get(4).getNumber() == 11) {
                System.out.println("Your high card is a Jack!");
            }

            if (handList.get(4).getNumber() == 12) {
                System.out.println("Your high card is a Queen!");
            }

            if (handList.get(4).getNumber() == 13) {
                System.out.println("Your high card is a King!");
            }

            if (handList.get(4).getNumber() == 14) {
                System.out.println("Your high card is an Ace!");
            }

            logHand("High Card");

        }

    }
}
