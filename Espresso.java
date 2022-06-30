
public class Espresso extends CoffeeDecorator {
    public Espresso(Coffee coffee) {

        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
        //coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + "-Espresso Shot";
    }

    @Override
    public Double cost() {
        return this.coffee.cost()+0.35;
    }
}
