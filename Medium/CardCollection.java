import java.util.*;

public class CardCollection {
    private Map<String, List<String>> cardCollection;

    public CardCollection() {
        cardCollection = new HashMap<>();
    }

    // Method to add a card to the collection
    public void addCard(String symbol, String card) {
        cardCollection.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    // Method to retrieve all cards of a given symbol
    public List<String> getCardsBySymbol(String symbol) {
        return cardCollection.getOrDefault(symbol, new ArrayList<>());
    }

    // Display all cards
    public void displayCollection() {
        if (cardCollection.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : cardCollection.entrySet()) {
            System.out.println("Symbol: " + entry.getKey() + " -> Cards: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        CardCollection collection = new CardCollection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display Collection");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter card name: ");
                    String card = scanner.nextLine();
                    collection.addCard(symbol, card);
                    System.out.println("Card added successfully!");
                    break;

                case 2:
                    System.out.print("Enter symbol to search: ");
                    String searchSymbol = scanner.nextLine();
                    List<String> result = collection.getCardsBySymbol(searchSymbol);
                    if (result.isEmpty()) {
                        System.out.println("No cards found for this symbol.");
                    } else {
                        System.out.println("Cards for symbol '" + searchSymbol + "': " + result);
                    }
                    break;

                case 3:
                    collection.displayCollection();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
