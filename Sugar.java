public class Sugar extends CoffeeDecorator {
    public Sugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + "-sugar";
    }

    @Override
    public Double cost() {
        return this.coffee.cost()+0.05;
    }
}
