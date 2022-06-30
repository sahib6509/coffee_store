/* CS160L - 02 - SUMMER 2022
* Lab 6
* 6/29/2022
* @author Sahib Singh
 */

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;


public class Main {
    private static List<Integer> inventory = new ArrayList<>();
    private static boolean discount = false;

    public static void main(String[] args) {
        ArrayList<String> Item = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> Temp2 = new ArrayList<>();

        Scanner scnr = new Scanner(System.in);
        TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");
        inventory = readInventory();
        int a = 0;
        Stack<String> order = new Stack<>();
        boolean q = false;
        while(a != 1){
            System.out.println("Cafe Application Running...");
            System.out.println("Press 1 : Read Inventory");
            System.out.println("Press 2 : Create Coffee Order");
            System.out.println("Press 3 : Update Inventory");
            System.out.println("Press 4 : Update log file");
            System.out.println("Press 5 : Exit the Application");

            String input = scnr.nextLine();
            switch(input){
                case "1":
                    System.out.println("Current Items in inventory: ");
                    System.out.println("Black Coffee = " + inventory.get(0));
                    System.out.println("Milk = " + inventory.get(1));
                    System.out.println("HotWater = " + inventory.get(2));
                    System.out.println("Espresso = " + inventory.get(3));
                    System.out.println("Sugar = " + inventory.get(4));
                    System.out.println("WhippedCream = " + inventory.get(5));
                    break;

                case "2":
                    if(inventory.get(0) != 0){
                        Scanner userOrders = new Scanner(System.in);
                        System.out.println("Coffee order created. Select toppings for the first coffee:");
                        String line = "yes" ;
                        do
                        {
                            inventory.set(0, inventory.get(0)-1);

                            Temp2 = CreateOrder();
                            Item.add(Temp2.get(0));
                            price.add(Double.valueOf(Temp2.get(1)));

                            if(inventory.get(0) == 0){
                                System.out.println("Order Completed. No more coffees.");
                                break;
                            }
                            else{
                                System.out.println("Do you want to add another coffee to this order? - yes or no");
                            }
                        }while (!(line = userOrders.nextLine()).equals("no"));
                        order.push(PrintOrder(Item, price));
                        System.out.println("Discount for Order.(yes/no)");
                        String ans = userOrders.nextLine();
                        if(ans.equalsIgnoreCase("yes")){
                            discount = true;
                        }
                        else{
                            discount = false;
                        }
                    }
                    else{
                        System.out.println("Out of coffee. Visit us later.");
                    }

                    break;

                case "3":
                    inventoryWriter(inventory);
                    System.out.println("Successfully updated the inventory");
                    q =true;
                    break;

                case "4":
                    if(order.isEmpty()){
                        System.out.println("Nothing to log. Stack is empty");
                    }
                    else{
                        logWriter(order);
                        System.out.println("Successfully updated the log file");
                    }
                    break;

                case "5":
                    if(!(order.isEmpty())){
                        logWriter(order);
                    }
                    if(!q){
                        inventoryWriter(inventory);
                    }
                    a = 1;
                    break;

                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    /* uses string builder to return a complete string containing order information */

    public static String PrintOrder(ArrayList<String> Item, ArrayList<Double> price){
        StringBuilder str = new StringBuilder();
        double sum = 0;
        for(int x = 0; x < Item.size(); x++ ){
            str.append("Item " + (x+1) + ": " + Item.get(x) + " | " + "Cost: " + price.get(x));
            str.append("\n");
            sum = sum + price.get(x);
        }
        if(discount){
            double discountAmt = (sum*10.0)/100.0;
            sum = sum - discountAmt;
        }
        str.append("TOTAL COST OF ORDER: " + sum);
        str.append("\n");

        return str.toString();
    }

    /* Create orders with all specific toppings chosen by user*/

    public static ArrayList<String> CreateOrder(){
        Scanner userFeedback = new Scanner(System.in);
        ArrayList<String> coffeeOrder = new ArrayList<String> ();
        Coffee basicCoffee = new BasicCoffee();
        int in = 0;

        while (in != 1) {
            System.out.println("Enter the following values to add toppings: 1.) milk, 2.) hot water, 3.) espresso, 4.) sugar, 5) whipped cream, e - to complete order");
            String choice = userFeedback.nextLine();
            switch(choice){
                case "1":
                    if(inventory.get(1) != 0){
                        basicCoffee = new Milk(new BasicCoffee());
                        inventory.set(1, inventory.get(1)-1);
                    }
                    else{
                        System.out.println("Out of milk. Try a different topping.");
                    }
                    break;
                case "2":
                    if(inventory.get(2) != 0){
                        basicCoffee = new HotWater(new BasicCoffee());
                        inventory.set(2, inventory.get(2)-1);
                    }
                    else{
                        System.out.println("Out of hot water. Try a different topping.");
                    }
                    break;
                case "3":
                    if(inventory.get(3) != 0){
                        basicCoffee = new Espresso(new BasicCoffee());
                        inventory.set(3, inventory.get(3)-1);
                    }
                    else{
                        System.out.println("Out of Espresso. Try a different topping.");
                    }
                    break;
                case "4":
                    if(inventory.get(4) != 0){
                        basicCoffee = new Sugar(new BasicCoffee());
                        inventory.set(4, inventory.get(4)-1);
                    }
                    else{
                        System.out.println("Out of Sugar. Try a different topping.");
                    }
                    break;
                case "5":
                    if(inventory.get(5) != 0){
                        basicCoffee = new WhippedCream(new BasicCoffee());
                        inventory.set(5, inventory.get(5)-1);
                    }
                    else{
                        System.out.println("Out of Whipped Cream. Try a different topping.");
                    }
                    break;

                case "e":
                    in = 1;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
        coffeeOrder.add(basicCoffee.printCoffee());
        coffeeOrder.add(Double.toString(basicCoffee.cost()));
        return coffeeOrder;
    }

    /* reads respective item inventory of the coffee store */
    public static List<Integer> readInventory(){
        List<Integer> inventory = new ArrayList<>();
        String line;

        File f= new File("Inventory.txt");
        try
        {
            BufferedReader in= new BufferedReader(new FileReader(f));

            line= in.readLine();
            while(line != null)
            {
                char[] arr = new char[line.length()];
                //int number = Integer.parseInt(String.valueOf(arr[arr.length-1]));
                int number = Integer.valueOf(line.substring(line.indexOf("=")+2));
                inventory.add(number);
                line= in.readLine();
            }
        }
        catch(Exception e){
            System.out.println("error");
        }
        return inventory;
    }

    /* outputs and writes all order receipts with the date and time to the log file*/

    public static void logWriter(Stack<String> order){
        try{
            SimpleDateFormat c = new SimpleDateFormat("YYYY-MM-dd 'at' HH:mm:ss zzz");
            Date thisDate = new Date();
            File file = new File("LogFile.txt");
            FileWriter writer = new FileWriter(file,true);

            for(int i =0; i < order.size(); i++){
                writer.write("Writing orders from stack ");
                writer.write(c.format(thisDate));
                writer.write("\n");
                writer.write("RECEIPT");
                writer.write("\n");
                writer.write(order.pop());
                writer.write("\n");
            }

            writer.flush();
            writer.close();

        }catch(IOException exception){
            System.out.println("This is an IO Exception");

        }

    }

    /* writes updated inventory item quantities to the inventory file */

    public static void inventoryWriter(List<Integer> inventory){
        try{
            File file = new File("Inventory.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("Black Coffee = " + inventory.get(0));
            writer.write("\n");
            writer.write("Milk = " + inventory.get(1));
            writer.write("\n");
            writer.write("HotWater = " + inventory.get(2));
            writer.write("\n");
            writer.write("Espresso = " + inventory.get(3));
            writer.write("\n");
            writer.write("Sugar = " + inventory.get(4));
            writer.write("\n");
            writer.write("WhippedCream = " + inventory.get(5));
            writer.write("\n");
            writer.flush();
            writer.close();

        }catch(IOException exception){
            System.out.println("This is an IO Exception");

        }

    }

}
