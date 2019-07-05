import java.util.*;

public class Problem {

    private List<Item> availableItems;//lucrurile de pe masa
    private Backpack backpack;

    public Problem() {
        availableItems = new ArrayList<Item>();
    }

    public List<Item> getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(List<Item> availableItems) {
        this.availableItems = availableItems;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public void addAvailableItem(Item item) {
        this.availableItems.add(item);
    }

    public double getTotalWeightOfItemsInBackpack() {
        return backpack.getTotalWeightOfItems();
    }

    public double getBackpackCapacity() {
        return backpack.getCapacity();
    }

    public double getBackpackValue() {
        return backpack.getTotalValueOfItems();
    }

    public int getNumberOfItemsInBackpack() {
        return backpack.getNumberOfItems();
    }

    public void moveItemsToBackpack() {
        //lista de item-uri nesortate
        for (int index = 0; index < availableItems.size(); index++) {
            Item currentItem = availableItems.get(index);
            backpack.add(currentItem);
        }
    }

    public void moveProfitableItemsToBackpack() {
        //introduc lista availableItems in TreeSet
        //Set<Item> apeleaza metoda compareTo() din Item
        Set<Item> itemSet = new TreeSet<>(availableItems);// TreeSet-obiecte unice, sortate si ordonate
        for (Item item : itemSet) {
            backpack.add(item);
        }
    }

    public void moveLightItemsToBackpack() {
        //lista de item-uri cu greutatea cea mai mica
        //folosim un comparator
        ItemComparatorByWeight comparator = new ItemComparatorByWeight();
        Set<Item> itemSet = new TreeSet<>(comparator);
        itemSet.addAll(availableItems);

        for (Item item : itemSet) {
            backpack.add(item);
        }
        //sau
        /*Iterator<Item> iterator = itemSet.iterator();
        while (iterator.hasNext()) {
            backpack.add(iterator.next());
        }*/
    }

    public void moveFractionItemsToBackpack() {
        Set<Item> itemSet = new TreeSet<>(availableItems);
        for (Item item : itemSet) {
            backpack.addFraction(item);
            availableItems.remove(item);
        }
    }

    public Map getNameAndNumberOfEachAvailableItem() {

        Map<String, Integer> map = new HashMap<>();

        for(Item item : availableItems){
            Integer counter=0;
            counter = map.get(item.getName());
            if(counter ==null){
                map.put(item.getName(),1);
            }else {
                counter++;
                map.put(item.getName(),counter);
            }
        }
        return map;
    }

    public Map getAveragePriceForEachTypeOfItem(){

        Map<String, Double> map = new HashMap<>();
        Map<String, Integer> mapNameAndNumbers = getNameAndNumberOfEachAvailableItem();
        for(Item item : availableItems){
            Double avg=0.0;
            avg = map.get(item.getName());
            if(avg ==null){
                avg = item.getValue();
            }else {
                avg *=mapNameAndNumbers.get(item.getName());
                avg+=item.getValue();

            }
            map.put(item.getName(),avg/mapNameAndNumbers.get(item.getName()));
        }
        return map;
    }
    public void display(){
        Map<String,Double> map = getNameAndNumberOfEachAvailableItem();

        for(String itemOfName: map.keySet()){
            System.out.println(itemOfName +":"+map.get(itemOfName));
        }

        Map<String,Integer> map2 = getAveragePriceForEachTypeOfItem();
        System.out.println();
        for(String itemOfName: map2.keySet()){
            System.out.println(itemOfName +":"+map2.get(itemOfName));
        }
    }
}


