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
        for (Item currentItem : itemSet) {
            if (backpack.addFraction(currentItem)) {
                availableItems.remove(currentItem);
            }
        }
    }

    public void showTotalValueForEachItem() {
        Map<String, Integer> itemsByCount = new HashMap<>();
        Map<String, Double> itemsByValue = new HashMap<>();
        for (int index = 0; index < availableItems.size(); index++) {
            Item currentItem = availableItems.get(index);
            String currentItemName = currentItem.getName();
            Double currentItemValue = currentItem.getValue();
            Double beforeItemValue = itemsByValue.get(currentItemName);
            Integer beforeItemCount = itemsByCount.get(currentItemName);
            if (beforeItemValue == null) {
                beforeItemValue = 0.0;
                beforeItemCount = 0;
            }
            itemsByValue.put(currentItemName, beforeItemValue + currentItemValue);
            itemsByCount.put(currentItemName, beforeItemCount + 1);
        }

        for (Map.Entry<String, Double> entry : itemsByValue.entrySet()) {
            Integer countForEach = itemsByCount.get(entry.getKey());
            System.out.println(
                    "key: " + entry.getKey() + " value: " + entry.getValue() + " in total: " + countForEach
                            + " average: " + (entry.getValue() / countForEach));


        }
    }

}


