import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        /*added logic for RestaurantOpen()*/
        LocalTime currentTime = getCurrentTime();
        if( currentTime.isAfter(openingTime)  && currentTime.isBefore(closingTime))
            return true;
        else
            return false;
    }

    public LocalTime getCurrentTime() {
        return  LocalTime.now();
    }

    public List<Item> getMenu() {
        /*added logic for returning list of items*/
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws ItemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new ItemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }
    /* Added a new method to get total Price for given item*/
    public int getTotalAmount(String...items) throws ItemNotFoundException{
        Item item;
        int amount = 0 ;
        for (String itemName : items){
            item = findItemByName(itemName);
            if(item !=null)
                amount += item.getPrice();
            else
                throw new ItemNotFoundException("Item not Found Exception");
        }
        return amount;
    }

}
