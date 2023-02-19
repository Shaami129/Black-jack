import java.util.*;

public class Blackjack {
    
    enum Suit {
        HEARTS, CLUBS, DIAMONDS, SPADES;
    }
    
    enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
    }
    
    class Card {
        private final Suit suit;
        private final Rank rank;
        
        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }
        
        public int getValue() {
            switch (rank) {
                case ACE:
                    return 1;
                case TWO:
                    return 2;
                case THREE:
                    return 3;
                case FOUR:
                    return 4;
                case FIVE:
                    return 5;
                case SIX:
                    return 6;
                case SEVEN:
                    return 7;
                case EIGHT:
                    return 8;
                case NINE:
                    return 9;
                default:
                    return 10;
            }
        }
        
        public String toString() {
            return rank + " of " + suit;
        }
    }
    
    List<Card> deck = new ArrayList<>();
    List<Card> playerHand = new ArrayList<>();
    List<Card> dealerHand = new ArrayList<>();
    
    public void play() {
        initializeDeck();
        shuffleCards();
        dealInitialCards();
        while (true) {
            System.out.println("Your hand: " + playerHand);
            System.out.println("Your score: " + getScore(playerHand));
            if (getScore(playerHand) > 21) {
                System.out.println("You bust!");
                return;
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Hit or stand? ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("hit")) {
                playerHand.add(drawCard());
            } else if (choice.equalsIgnoreCase("stand")) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        while (getScore(dealerHand) < 17) {
            dealerHand.add(drawCard());
        }
        System.out.println("Dealer's hand: " + dealerHand);
        System.out.println("Dealer's score: " + getScore(dealerHand));
        if (getScore(dealerHand) > 21) {
            System.out.println("Dealer busts! You win!");
            return;
        }
        if (getScore(playerHand) > getScore(dealerHand)) {
            System.out.println("You win!");
        } else if (getScore(dealerHand) > getScore(playerHand)) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
    
    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }
    
    private void shuffleCards() {
        Collections.shuffle(deck);
    }
    
    private void dealInitialCards() {
        playerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        dealerHand.add(drawCard());
    }
    
    private Card drawCard() {
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }
    
    private int getScore(List<Card> hand) {
    int score = 0;
    int aceCount = 0;
    for (Card card : hand) {
        if (card.rank == Rank.ACE) {
            aceCount++;
        }
        score += card.getValue();
    }
    while (score <= 11 && aceCount > 0) {
        score += 10;
        aceCount--;
    }
    return score;
}
    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.play();
    }
}

