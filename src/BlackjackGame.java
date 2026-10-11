
import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {
    private final Scanner scanner = new Scanner(System.in);

    public void play() {
        System.out.println("Welcome to Blackjack!");

        String again;
        do {
            playRound();
            System.out.print("Play again? (y/n): ");
            again = scanner.nextLine().trim().toLowerCase();
        } while (again.equals("y") || again.equals("yes"));

        System.out.println("Thanks for playing!");
    }

    private void playRound() {
        Deck deck = new Deck();
        ArrayList<Card> player = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();

        player.add(deck.deal());
        dealer.add(deck.deal());
        player.add(deck.deal());
        dealer.add(deck.deal());

        System.out.println("\n--- New Round ---");
        System.out.println("Dealer shows: " + dealer.get(0) + " and [hidden]");
        showHand("Your hand", player);

        // Checks first two cards
        if (score(player) == 21 || score(dealer) == 21) {
            showHand("Dealer hand", dealer);

            if (score(player) == score(dealer)) {
                System.out.println("Push! Both have blackjack.");
            } else if (score(player) == 21) {
                System.out.println("Blackjack! You win!");
            } else {
                System.out.println("Dealer has blackjack. You lose.");
            }
            return;
        }

        // Player turn
        while (score(player) < 21) {
            System.out.print("Hit (h) or stand (s)? ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("h") || choice.equals("hit")) {
                Card card = deck.deal();
                player.add(card);
                System.out.println("You drew: " + card);
                showHand("Your hand", player);
            } else if (choice.equals("s") || choice.equals("stand")) {
                break;
            } else {
                System.out.println("Please enter h or s.");
            }
        }

        if (score(player) > 21) {
            System.out.println("Bust! You lose.");
            return;
        }

        // Dealer's turn
        showHand("Dealer hand", dealer);

        while (score(dealer) < 17) {
            Card card = deck.deal();
            dealer.add(card);
            System.out.println("Dealer draws: " + card);
        }

        showHand("Dealer final hand", dealer);

        // Determines winner
        if (score(dealer) > 21) {
            System.out.println("Dealer busts! You win!");
        } else if (score(player) > score(dealer)) {
            System.out.println("You win!");
        } else if (score(player) < score(dealer)) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("Push! It's a tie.");
        }
    }

    // Calculate hand value and adjusting Aces to 1 if needed
    private int score(ArrayList<Card> hand) {
        int total = 0;
        int aces = 0;

        for (Card card : hand) {
            total += card.getValue();

            if (card.isAce()) {
                aces++;
            }
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    private void showHand(String name, ArrayList<Card> hand) {
        System.out.println(name + ": " + hand + " (" + score(hand) + ")");
    }
}

