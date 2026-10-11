import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                int value = i + 1;

                if (i == 0) {
                    value = 11;
                } else if (value > 10) {
                    value = 10;
                }

                cards.add(new Card(ranks[i], suit, value));
            }
        }

        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.remove(cards.size() - 1);
    }
}