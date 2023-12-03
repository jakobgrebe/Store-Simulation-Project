import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

public class Main {

    public void exposeGameSetup(){}
    public void exposeGamePlay(){}
    public void exposeGameStop(){}

    public static void storeMenu(Scanner scanner, Store store, Player player) {
        while (true) {
            System.out.println("\nStore Menu:");
            System.out.println("1. Buy an item");
            System.out.println("2. Sell an item");
            System.out.println("3. Display Store inventory");
            System.out.println("4. Display Player inventory");
            System.out.println("5. Exit store");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.displayInventory();
                System.out.println("Enter the name of the item you want to buy:");
                String itemName = scanner.nextLine();
                Item item = store.getItemByName(itemName);
                if (item !=null) {
                    try {player.buyUsingEscrow(item, store);
                        System.out.println("Item purchased successfully!");
                    } catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("Item not available in the store.");
                }
            } else if (input.equals("2")) {
                System.out.println("Enter the name of the item you want to sell:");
                String itemName = scanner.nextLine();
                Item itemToSell = null;
                for (Item item : player.exposeInventory()) {
                    if (item.getName().toLowerCase().equals(itemName)) {
                        itemToSell = item;
                        break;
                    }
                }
                if (itemToSell != null) {
                    try {
                        player.sellUsingEscrow(itemToSell, store);
                        System.out.println("Item sold successfully!");
                    } catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("Item not available in player's inventory.");
                }
            } else if (input.equals("3")) {
                store.displayInventory();
            } else if (input.equals("4")) {
                player.displayInventory();
            } else if (input.equals("5")) {
                System.out.println("Exiting the store...");
                store.exit(player);
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    public static void main(String[] args) {
        Item sword = new Item("Sword", 10.0);
        Item potion = new Item("Health Potion", 5.0);
        Item hat = new Item("Hat", 1);

        Store store = new Store();
        store.addItem(sword);
        store.addItem(potion);
        store.addItem(hat);
        store.displayInventory();

        Player player = new Player(100.0);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a command (1 to enter the store, 4 to exit):");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                System.out.println("\nWelcome to the Store!!");
                store.enter(player);
                storeMenu(scanner, store, player);
            } else if (input.equals("4")) {
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
        scanner.close();
    }
}