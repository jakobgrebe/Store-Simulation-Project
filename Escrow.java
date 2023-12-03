public class Escrow{
    private static double escrowMoney = 0;
    private static Item escrowItem;

    public static void escrowItem(Item item){
       escrowItem = item;
    }

    public static void escrowMoney(double money){
        escrowMoney = money;

    }

    public static Item receiveItem(){
        return escrowItem;
    }

    public static void requestItem(Item item){
        escrowItem(item);
    }

    public static double receiveMoney(){
        double amount = escrowMoney;
        escrowMoney = 0;
        return amount;  
    }

    public static double returnMoney(){
        double amount = escrowMoney;
        escrowMoney = 0;
        return amount;
    }

    public static void clearEscrow() {
        escrowMoney = 0;
        escrowItem = null;
    }


    

}

