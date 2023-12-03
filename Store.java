import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

class Store {
    static Logger logger = LogManager.getLogger(Store.class); 
    private List<Item> inventory;
    private List<Player> players_in_store; 

    public Store() {
        inventory = new ArrayList<>();
        players_in_store = new ArrayList<>();
    }

    public void enter(Player player) {
        if (!check_player_in_store(player)) {
            players_in_store.add(player);
            player.enterStore();
        } else {
            System.out.println("Player is already in the store.");
        }
    }

    public void exit(Player player){
         if (check_player_in_store(player) == true){
            players_in_store.remove(player);
            player.exitStore();
        } else {
            System.out.println("Player never entered the store.");
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void displayInventory() {
        System.out.println("Store Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    public boolean check_player_in_store(Player player){
        int index =  players_in_store.indexOf(player);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; 
    }

    public boolean buyItem(Item item, Player player) {
        if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to buy anything");
            return false;
        }
        if (player.getMoney() >= item.getPrice()){
            if (inventory.contains(item)) {
            inventory.remove(item);
            player.acquireItem(item);
                return true;
            }
        else {
            System.out.println("Item not available in the store.");
            }
        }
        return false;
    }

    public boolean sellItem(Item item, Player player) {
         if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to sell anything");
            return false;
        }
        player.relinquishItem(item);
        player.addMoney(item.getPrice());
        inventory.add(item);
        return true;
    }

     public void customerBuyUsingEscrow(){
        buyUsingEscrow();

    }

    public void customerSellUsingEscrow(){
        sellUsingEscrow();
    }

    private void buyUsingEscrow(){
        finalizeEscrowBuy();
    }

    private void sellUsingEscrow(){
       finalizeEscrowSell();
    }

    public void finalizeEscrowBuy() {
        Player player = players_in_store.get(0); 
        Item item = Escrow.receiveItem();
        double money = Escrow.receiveMoney(); 
        if (item != null && money > 0) {
            player.acquireItem(item);
            inventory.remove(item);
            Escrow.clearEscrow(); 
            logger.info("Player finalized the buy");
        } else {
            logger.error("Escrow transaction failed.");
        }
    }

    public void finalizeEscrowSell(){
        Item item = Escrow.receiveItem();
        double money = Escrow.returnMoney();
        Player player = new Player(0);
        if (!players_in_store.isEmpty()) {
            player = players_in_store.get(0);
        }
        player.relinquishItem(item);
        player.addMoney(money);
        inventory.add(item);
        Escrow.clearEscrow();
        logger.info("Player finalized the sell");
    }
}
