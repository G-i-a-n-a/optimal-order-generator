package main.dev.giana.optimalordergenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to generate most expensive potential Order
 *
 * @author Giana Guarascio
 */
public class OrderGenerator
{
    private List<Order> possibleOrders;
    private Order optimalOrder;
    private final int minCaloriesConstraint;
    private final int maxCaloriesConstraint;
    private final double budgetConstraint;

    /**
     * Parameterized constructor for OrderGenerator
     * @param menu                  the Menu to reference
     * @param minCaloriesConstraint the Customer's minimum calories constraint
     * @param maxCaloriesConstraint the Customer's maximum calories constraint
     * @param budgetConstraint      the Customer's budget constraint
     */
    public OrderGenerator(Menu menu, int minCaloriesConstraint, int maxCaloriesConstraint, double budgetConstraint)
    {
        possibleOrders = new ArrayList<>();
        optimalOrder = new Order();

        this.minCaloriesConstraint = minCaloriesConstraint;
        this.maxCaloriesConstraint = maxCaloriesConstraint;
        this.budgetConstraint = budgetConstraint;

        possibleOrders = generateOrders(menu);
        possibleOrders = getSortedOrders();

        try
        {
            /* Optimal order is last Order in possibleOrders, as it's sorted from least to most
               expensive and the owner of the restaurant wants the most expensive Order suggested
               to the customer
            */
            optimalOrder = possibleOrders.get(possibleOrders.size() - 1);
        }
        catch(Exception e)
        {
            System.out.println("Error finding optimal order:");
        }

    }

    /**
     * @return this OrderGenerator's optimal Order
     */
    public Order getOptimalOrder()
    {
        return optimalOrder;
    }

    /**
     * Returns if adding MenuItem to Order meets maximum calorie
     * and budget constrains
     * @param order    the Order to add to
     * @param menuItem the MenuItem to add
     * @return if upper constraints are met after adding MenuItem
     */
    public boolean constraintsMetWithAddition(Order order, MenuItem menuItem)
    {
        return order.getTotalCalories() + menuItem.getCalories() <= maxCaloriesConstraint
                && order.getTotalCost() + menuItem.getCost() <= budgetConstraint;
    }

    /**
     * Returns if any MenuItem exists in Menu which would meet maximum
     * calorie and budget constraints if added to Order
     * @param order the Order to add to
     * @param menu  the Menu to reference
     * @return if any MenuItem exists which can be added within upper constraints
     */
    public boolean existsAdditionalItemWithinConstraints(Order order, Menu menu)
    {
        for(int i = 0; i < menu.getItems().size(); i++)
        {
            MenuItem currItem = menu.getItems().get(i);

            if(order.getTotalCalories() + currItem.getCalories() <= maxCaloriesConstraint
                && order.getTotalCost() + currItem.getCost() <= budgetConstraint)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Recursively creates a result list of all possible Orders which meet
     * minimum calorie, maximum calorie, and budget constraints
     * @param result the resulting list of Orders
     * @param order  the Order to reference
     * @param menu   the Menu to reference
     * @return resulting list of Orders
     */
    public List<Order> recurse(List<Order> result, Order order, Menu menu)
    {
        /* End-of-path: no additional MenuItem can be added to this Order
           less than or equal to maximum calorie and budget constraints
         */
        if(!existsAdditionalItemWithinConstraints(order, menu))
        {
            // Success: Order meets minimum calorie requirement
            if(order.getTotalCalories() >= minCaloriesConstraint)
            {
                result.add(new Order(order));
                return result;
            }
        }

        for(int i = 0; i < menu.getItems().size(); i++)
        {
            MenuItem currItem = menu.getItems().get(i);

            /* Potential solution: adding this MenuItem to the current
               Order is less than or equal to maximum calorie and budget
               constraints
             */
            if(constraintsMetWithAddition(order, currItem))
            {
                order.addItem(currItem);
                recurse(result, order, menu);
                order.removeItem(order.getItems().get(order.getItems().size() - 1));
            }
        }

        return result;
    }

    /**
     * Generates list of Orders from a Menu which meets minimum calorie,
     * maximum calorie, and budget constraints using the recurse()
     * method
     * @param menu the Menu to reference
     * @return resulting list of Orders
     */
    public List<Order> generateOrders(Menu menu)
    {
        List<Order> result = new ArrayList<>();
        Order order = new Order();

        return recurse(result, order, menu);
    }

    /**
     * Sorts this OrderGenerator's list of possible Orders by Order
     * cost
     * @return the sorted list of Orders
     */
    public List<Order> getSortedOrders()
    {
        return possibleOrders.stream().sorted(Comparator.comparingDouble(Order::getTotalCost)).collect(Collectors.toList());
    }
}
