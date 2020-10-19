package main.dev.giana.optimalordergenerator;

/**
 * Class to create a Customer
 *
 * @author Giana Guarascio
 */
public class Customer
{
    private Order order;

    /**
     * Default constructor for Customer
     */
    public Customer()
    {
        order = new Order();
    }

    /**
     * @return this Customer's order
     */
    public Order getOrder()
    {
        return order;
    }

    /**
     * Sets Order for Customer referencing given Menu, taking
     * into account Customer's minimum calories, maximum calories,
     * and budget constraints
     * @param menu        the Menu to reference
     * @param minCalories the minimum calories constraint
     * @param maxCalories the maximum calories constraint
     * @param budget      the budget constraint
     */
    public void placeOrder(Menu menu, int minCalories, int maxCalories, double budget)
    {
        OrderGenerator orderGenerator = new OrderGenerator(menu, minCalories, maxCalories, budget);
        order = orderGenerator.getOptimalOrder();
    }
}
