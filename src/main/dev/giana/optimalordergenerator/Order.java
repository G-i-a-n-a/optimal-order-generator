package main.dev.giana.optimalordergenerator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to create an Order
 *
 * @author Giana Guarascio
 */
public class Order
{
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");

    private List<MenuItem> items;
    private int totalCalories;
    private double totalCost;

    /**
     * Default constructor for Order
     */
    public Order()
    {
        items = new ArrayList<>();
        totalCalories = 0;
        totalCost = 0;
    }

    /**
     * Copy constructor for Order
     * @param order the Order to copy from
     */
    public Order(Order order)
    {
        this.items = new ArrayList<>(order.getItems());
        this.totalCalories = order.getTotalCalories();
        this.totalCost = order.getTotalCost();
    }

    /**
     * @return this Order's list of MenuItem
     */
    public List<MenuItem> getItems()
    {
        return items;
    }

    /**
     * @return this Order's total calories
     */
    public int getTotalCalories()
    {
        return totalCalories;
    }

    /**
     * @return this Order's total cost
     */
    public double getTotalCost()
    {
        return totalCost;
    }

    /**
     * Adds a MenuItem to this Order's list of MenuItems
     * Updates the total calories and total cost
     * @param item the MenuItem to add
     */
    public void addItem(MenuItem item)
    {
        items.add(item);
        totalCalories += item.getCalories();
        totalCost += item.getCost();
    }

    /**
     * Removes a MenuItem from this Order's list of MenuItems
     * Updates the total calories and total cost
     * @param item the MenuItem to remove
     */
    public void removeItem(MenuItem item)
    {
        items.remove(item);
        totalCalories -= item.getCalories();
        totalCost -= item.getCost();
    }

    /**
     * Sorts this Order's list of MenuItems alphabetically
     * by MenuItem name
     * @return the sorted list of MenuItems
     */
    public List<MenuItem> getSortedOrderItems()
    {
        return items.stream().sorted(Comparator.comparing(MenuItem::getName)).collect(Collectors.toList());
    }

    /**
     * Prints name of each MenuItem in this Order's list of MenuItems
     * Prints total calories and formatted total cost of this Order
     */
    public void printOrder()
    {
        // Sort list of MenuItems (alphabetically by MenuItem name)
        items = getSortedOrderItems();

        System.out.println("\nItems:");

        for(MenuItem item : items)
        {
            System.out.println(item.getName());
        }

        System.out.println("\nCalories: " + totalCalories);
        System.out.println("Cost: $" + DECIMAL_FORMAT.format(totalCost));
    }
}
