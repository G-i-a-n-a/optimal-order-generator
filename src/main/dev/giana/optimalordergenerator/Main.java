package main.dev.giana.optimalordergenerator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class to interact with the program from the command line
 *
 * @author Giana Guarascio
 */
public class Main
{
    public static final String INPUT_MISMATCH_EXCEPTION_MESSAGE = "\nPlease enter valid input.";
    public static final String INVALID_INPUT_MESSAGE = "\nPlease choose a valid menu option.";

    public Customer customer;
    public static Menu menu1;

    /**
     * Default constructor for Main
     */
    public Main()
    {
        customer = new Customer();
        menu1 = new Menu();
    }

    /**
     * Runs the main menu for the user to interact with from the command line
     */
    public void runMenu()
    {
        String choice = "-1";

        System.out.println("\nHello! Welcome to our restaurant.");
        System.out.println("Type a number and hit \"Enter\" to select a menu option.");

        while(!choice.equals("3"))
        {
            System.out.println("\nWhat can I help you with?");
            System.out.println("1. Place an order");
            System.out.println("2. View order");
            System.out.println("3. Exit");

            Scanner choiceScanner = new Scanner(System.in);
            choice = choiceScanner.nextLine();

            switch(choice)
            {
                case "1":
                    int minCalories, maxCalories;
                    double budget;

                    System.out.println("\nHow many calories do you want to eat at a minimum?\nExample: 350");

                    try
                    {
                        minCalories = choiceScanner.nextInt();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println(INPUT_MISMATCH_EXCEPTION_MESSAGE);
                        continue;
                    }

                    System.out.println("\nHow many calories do you want to eat at a maximum?\nExample: 1300");

                    try
                    {
                        maxCalories = choiceScanner.nextInt();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println(INPUT_MISMATCH_EXCEPTION_MESSAGE);
                        continue;
                    }

                    System.out.println("\nWhat is your budget?\nExample: 9.50");

                    try
                    {
                        budget = choiceScanner.nextDouble();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println(INPUT_MISMATCH_EXCEPTION_MESSAGE);
                        continue;
                    }

                    customer.placeOrder(menu1, minCalories, maxCalories, budget);

                    if(!customer.getOrder().getItems().isEmpty())
                    {
                        System.out.println("\nHere's an option!");
                        customer.getOrder().printOrder();
                    }
                    else
                    {
                        System.out.println("\nThere are no possible orders given your needs.");
                    }

                    break;

                case "2":
                    if(!customer.getOrder().getItems().isEmpty())
                    {
                        System.out.println("\nSure, let me read that back to you.");
                        customer.getOrder().printOrder();
                    }
                    else
                    {
                        System.out.println("\nYou haven't ordered anything yet!");
                    }

                    break;

                case "3":
                    System.out.println("\nThank you for stopping by!");
                    break;

                default:
                    System.out.println(INVALID_INPUT_MESSAGE);
                    break;
            }
        }
    }

    public static void main(String[] args)
    {
        Main main = new Main();

        // Fill Menu given problem prompt
        menu1.addItem(new MenuItem("Cheese Pizza", 700, 4.00));
        menu1.addItem(new MenuItem("House Salad", 100, 8.50));
        menu1.addItem(new MenuItem("Grilled Shrimp", 400, 15.00));
        menu1.addItem(new MenuItem("Bottled Water", 0, 1.00));
        menu1.addItem(new MenuItem("Soda", 100, 1.00));

        main.runMenu();
    }
}
