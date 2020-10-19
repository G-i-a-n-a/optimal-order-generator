package main.dev.giana.optimalordergenerator;

/**
 * Class to create a MenuItem
 *
 * @author Giana Guarascio
 */
public class MenuItem
{
    private final String name;
    private final int calories;
    private final double cost;

    /**
     * Parameterized constructor for MenuItem
     * @param name     the name of this MenuItem
     * @param calories the calories of this MenuItem
     * @param cost     the cost of this MenuItem
     */
    public MenuItem(String name, int calories, double cost)
    {
        this.name = name;
        this.calories = calories;
        this.cost = cost;
    }

    /**
     * @return this MenuItem's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return this MenuItem's calories
     */
    public int getCalories()
    {
        return calories;
    }

    /**
     * @return this MenuItem's cost
     */
    public double getCost()
    {
        return cost;
    }
}
