public class Card {
    private int value;     // Shows the value of the card (2-10 for numbers, 10 for face cards)
    private String suit;   // Shows the suit (Hearts, Diamonds, Clubs, Spades)
    private String rank;   // Shows the rank (2-10, Jack, Queen, King, Ace)

    // Constructor
    public Card(int value, String suit, String rank) {
        this.value = value;
        this.suit = suit;
        this.rank = rank;
    }

    // Methods
    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    // Mutator methods may be necessary
    public void setValue(int value) {
        this.value = value;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // toString method
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
