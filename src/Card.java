public class Card {
    private final String rank;
    private final String suit;
    private final int value;

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return rank.equals("Ace");
    }

    public String toString() {
        return rank + " of " + suit;
}
}
