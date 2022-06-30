public abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public void addTopping(Coffee coffee) {
        this.coffee.addTopping(coffee);
    }

    public String printCoffee() {
        return this.coffee.printCoffee();
    }

    public abstract Double cost();
}
