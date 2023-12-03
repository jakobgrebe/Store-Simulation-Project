import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Player {
    static Logger logger = LogManager.getLogger(Player.class); 
    double money;
    boolean instore;
    ArrayList<Item> consumeInventory;
    ArrayList<Item> equipInventory;
    ArrayList<Item> wearInventory;
    ArrayList<Item> holdInventory;
    ArrayList<Item> eatInventory;
    ArrayList<Item> drinkInventory;
    ArrayList<Item> inventory;
    List<Item> bag;

    public Player(double money){
        
        this.money = money;
        instore = false;
        inventory = new ArrayList<Item>();
        bag = new ArrayList<Item>(); 
    }

    //Inventory Expose Methods
    public ArrayList<Item> exposeConsumeInventory(){
        return consumeInventory;
    }

    public ArrayList<Item> exposeEquipInventory(){
        return equipInventory;
    }

    public ArrayList<Item> exposeInventory(){
        return inventory;
    }

    public ArrayList<Item> exposeWearInventory(){
        return wearInventory;
    }

    public ArrayList<Item> exposeHoldInventory(){
        return holdInventory;
    }

    public ArrayList<Item> exposeEatInventory(){
        return eatInventory;
    }

    public ArrayList<Item> exposeDrinkInventory(){
        return drinkInventory;
    }

    //Common Expose Methods
    public void exposeCommonMethodConsume(Item item){
        consume(item);
    }

    public void exposeCommonMethodEquip(Item item){
        equip(item);
    }

    public void exposeCommonMethodUse(Item item){
        use(item);
    }
    //End of expose methods 

    public void eat(Item item){
        inventory.remove(item);
        System.out.println("Player ate the item!");
    }

    public void use(Item item){
        inventory.remove(item);
        System.out.println("Player used the item!");
    }

    public void drink(Item item){
        inventory.remove(item);
        System.out.println("Player drank the item!");
    }

    public void wear(Item item){
        inventory.remove(item);
        System.out.println("Player put on the item!");
    }

    public void hold(Item item){
        inventory.remove(item);
        System.out.println("Player is holding the item!");
    }

    public double getMoney(){
        return money;
    }
    
    public boolean inStore(){
        return instore;
    }

    public boolean enterStore(){
        return instore = true;
    }

    public boolean exitStore(){
        return instore = false;
    }

    public boolean removeMoney(double price) {
        if (money >= price){
            money -= price;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean inInventory(Item item){
        if (inventory.contains(item)){
            return true;
        }
        return false;
    }

    public void addMoney(double price) {
        money += price;
    }

    public void acquireItem(Item item){
        inventory.add(item);
        logger.info("Player acquired item " + item.getName());
    }

    public boolean relinquishItem(Item item){
        if(inventory.contains(item)){
            inventory.remove(item);
            logger.info("Player relinquished " + item.getName());
            return true;
        }
        else{
            logger.warn("Player did not relinquish " + item.getName());
            return false;
        }
    }

    public void addItem(Item item) {
        acquireItem(item);
    }

    public void removeItem(Item item) {
        relinquishItem(item);
    }

    public Item getItemByName(String itemName) {
        for (Item item: inventory){
            if (item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    public void displayInventory(){
        System.out.println("Player Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    public void consume(Item item){
        if(inInventory(item)){
            System.out.println("The item was consumed!");
            removeItem(item);
        }
        else{
            System.out.println("Item not in inventory");
        }
    }

    public void equip(Item item){
        if(inInventory(item)){
            System.out.println("The item was equiped!");
        }
        else{
            System.out.println("Item not in inventory");
        }
    }

    public void buyUsingEscrow(Item item, Store store) {
        if (instore) {
            if (getMoney() >= item.getPrice()) {
                double moneyHold = item.getPrice();
                removeMoney(moneyHold);
                Escrow.escrowMoney(moneyHold); 
                Escrow.requestItem(item);
                store.customerBuyUsingEscrow();
            } else {
                System.out.println("Insufficient funds to buy the item: " + item.getName());
            }
        } else {
            System.out.println("Player needs to be in the store to buy using escrow.");
        }
    }
    

    public void sellUsingEscrow(Item item,Store store){
        if (instore){
            if(inventory.contains(item)){
                Escrow.requestItem(item);
                Escrow.escrowMoney(item.getPrice());
                store.customerSellUsingEscrow();
            }
            else {
                System.out.println(item.getName() + "  not available in players inventory!");
            }
        } 

    }
}                       
