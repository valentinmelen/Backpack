import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Problem problem = new Problem();
        problem.setBackpack(new Backpack(12));
        List<Item> generatedItems = Util.generateItems(100);
        problem.setAvailableItems(generatedItems);
        problem.showTotalValueForEachItem();

        System.out.println("\nSORTATED BY PROFIT");
        problem.moveProfitableItemsToBackpack();
        displayToConsole(problem);

        System.out.println("NOT SORTED");
        problem.setBackpack(new Backpack(12));//RESETAM RUCSACUL
        problem.moveItemsToBackpack();
        displayToConsole(problem);

        System.out.println("SORTED BY LIGHT OBJECTS");
        problem.setBackpack(new Backpack(12));//RESETAM RUCSACUL
        problem.moveLightItemsToBackpack();
        displayToConsole(problem);

        System.out.println("FRACTION OF OBJECTS");
        problem.setBackpack(new Backpack(12));//RESETAM RUCSACUL
        problem.moveFractionItemsToBackpack();
        displayToConsole(problem);
    }

    private static void displayToConsole(Problem problem) {
        double totalWeightOfItemsInBackpack;
        double backpackCapacity;
        double totalValue;
        totalWeightOfItemsInBackpack = problem.getTotalWeightOfItemsInBackpack();
        backpackCapacity = problem.getBackpackCapacity();
        totalValue = problem.getBackpackValue();
        System.out.println("Capacity: " + backpackCapacity + ", total weight in backpack: "
                + totalWeightOfItemsInBackpack + ", number of items in backpack: " + problem
                .getNumberOfItemsInBackpack());
        System.out.println("Total value: " + totalValue + "\n\n");

    }
}
