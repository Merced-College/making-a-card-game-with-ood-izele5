//Izel Escoto
//9/16/2024
//Black Jack card game modified
import java.util.Random;
import java.util.Scanner;

public class BlackJack {


    private static Card[] cards = new Card[52];
    private static int currentCardIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean turn = true;  // Asks you if the game should continue or not
        String playerDecision;

        while(turn) {
            initializeDeck();    // load the deck with 52 cards
            shuffleDeck();       // Shuffle the deck randomly
            int playerTotal = dealInitialPlayerCards();
            int dealerTotal = dealInitialDealerCards();

            playerTotal = playerTurn(scanner, playerTotal);   // Player's turn
            if (playerTotal > 21) {
                System.out.println("Your out! Better luck nest time.");
                return;   // End the game if player busts
            }

            dealerTotal = dealerTurn(dealerTotal);   // Dealer's turn

            determineWinner(playerTotal, dealerTotal);   // Determine who wins the game

            // Ask the player if they want to keep playing
            System.out.println("Would you like to play again? (yes/no)");
            playerDecision = scanner.nextLine().toLowerCase();

            // Validate input for whether to continue or stop the game
            while(!(playerDecision.equals("no") || playerDecision.equals("yes"))) {
                System.out.println("Invalid action. Please type 'yes' or 'no'.");
                playerDecision = scanner.nextLine().toLowerCase();
            }
            if (playerDecision.equals("no"))
                turn = false;  // Exit the loop to stop the game if player picks "no"
        }
        System.out.println("Thanks for playing!");
    }

    // load deck with 52 cards
    private static void initializeDeck() {
        String[] SUITS = { "Hearts", "Diamonds", "Clubs","Spades" };
        String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9","10", "Jack", "Queen", "King","Ace" };
        int rankIndex = 0;

        for (int i = 0; i < cards.length; i++) {
            int value = rankIndex < 9 ? Integer.parseInt(RANKS[rankIndex]) : 10; // Set value for face cards and numbers
            cards[i] = new Card(value, SUITS[i / 13], RANKS[rankIndex]);  // Create a new Card object
            if (++rankIndex == 13) rankIndex = 0;
        }
    }

    // Shuffle the deck
    private static void shuffleDeck() {
        Random random = new Random();
        for (int i = 0; i < cards.length; i++) {
            int index = random.nextInt(cards.length);
            Card temp = cards[i];
            cards[i] = cards[index];  // Swap cards around
            cards[index] = temp;
        }
    }


    private static int dealInitialPlayerCards() {
        Card card1 = dealCard();  // Deal 1st card
        Card card2 = dealCard();  // Deal 2nd card

        System.out.println("Your cards: " + card1 + " and " + card2);
        return card1.getValue() + card2.getValue();   // Return total value of the cards
    }

    // Deal initial dealer card
    private static int dealInitialDealerCards() {
        Card card1 = dealCard();
        System.out.println("Dealer's card: " + card1);  // Dealer shows one card
        return card1.getValue();
    }

    // Player's turn
    private static int playerTurn(Scanner scanner, int playerTotal) {
        while (true) {
            System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");
            String action = scanner.nextLine().toLowerCase();

            if (action.equals("hit")) {
                Card newCard = dealCard();   // Deal new card
                playerTotal += newCard.getValue();  // Add new card value to total
                System.out.println("You drew a " + newCard);
                if (playerTotal > 21) {
                    System.out.println("You busted! Dealer wins.");  // Player busts if total > 21
                    return playerTotal;
                }
            } else if (action.equals("stand")) {
                break;   // Player stands, end turn
            } else {
                System.out.println("Invalid action. Please type 'hit' or 'stand'.");
            }
        }
        return playerTotal;
    }

    // Dealer's turn
    private static int dealerTurn(int dealerTotal) {
        while (dealerTotal < 17) {  // Dealer hits if total < 17
            Card newCard = dealCard();
            dealerTotal += newCard.getValue();   // Add new card value to total
            System.out.println("Dealer drew a " + newCard);
        }
        System.out.println("Dealer's total is " + dealerTotal);
        return dealerTotal;
    }

    // Discover the winner
    private static void determineWinner(int playerTotal, int dealerTotal) {
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (dealerTotal == playerTotal) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
        }
    }

    // load a card from the deck
    private static Card dealCard() {
        return cards[currentCardIndex++];
    }
}


