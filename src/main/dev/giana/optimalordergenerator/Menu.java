package main.dev.giana.optimalordergenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to create a Menu
 *
 * @author Giana Guarascio
 */
public class Menu
{
    private final List<MenuItem> items;

    /**
     * Default constructor for Menu
     */
    public Menu()
    {
        items = new ArrayList<>();
    }

    /**
     * @return this Menu's list of MenuItem
     */
    public List<MenuItem> getItems()
    {
        return items;
    }

    /**
     * Adds a MenuItem to this Menu's list of MenuItem
     * @param item the MenuItem to add
     */
    public void addItem(MenuItem item)
    {
        items.add(item);
    }
}
