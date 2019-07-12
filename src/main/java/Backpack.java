import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private double capacity;
    private List<Item> itemList;

    public Backpack(double capacity) {
        itemList = new ArrayList<>();
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public double getTotalWeightOfItems() {
        double sumOfWeights = 0;
        for (int index = 0; index < itemList.size(); index++) {
            Item currentItem = itemList.get(index);
            sumOfWeights += currentItem.getWeight();
        }
        return sumOfWeights;
    }

    public void add(Item item) {
        if (getAvailableCapacity() >= item.getWeight()) {
            itemList.add(item);
        }
    }

    //capacitatea ramasa libera din backpack
    public double getAvailableCapacity() {
        return capacity - getTotalWeightOfItems();
    }

    public double getTotalValueOfItems() {
        double sumOfValue = 0;
        for (int index = 0; index < itemList.size(); index++) {
            Item currentItem = itemList.get(index);
            sumOfValue += currentItem.getValue();
        }
        return sumOfValue;

    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public boolean addFraction(Item item) {
        double availableCapacity = getAvailableCapacity();
        if (availableCapacity == 0) {
            return false;
        }
        if (availableCapacity >= item.getWeight()) {
            itemList.add(item);
        } else {
            Item fractionItem = new Item(item.getName(), availableCapacity,
                    availableCapacity * item.getValue() / item.getWeight());
            itemList.add(fractionItem);
        }
        return true;
    }
}
