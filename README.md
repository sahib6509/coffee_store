# coffee_store
A coffee store program which records orders with receipts and updates the inventory with availability of multiple ingredients required to make the coffee
The main file contains the driver code and all relevant methods to run and write data to the respective files.
The coffee class is an interface which is implemented by the coffeeDecorator class which is further implemented by the BlackCoffee, Espresso, milk and other classes.
These classes are used to insantiate different coffee objects with respective prices and toppings stored in the object data which can be then later on pushed and stored in a stack
We then use the stack to pop values into the log file with convenient formatting to generate business standard receipts of the order.
