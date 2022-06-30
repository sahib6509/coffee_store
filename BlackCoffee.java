public class BlackCoffee extends CoffeeDecorator {
    public BlackCoffee(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
       // instructions();
    }

    @Override
    public String printCoffee() {
        return this.coffee.printCoffee();
    }

    @Override
    public Double cost() {
        return this.coffee.cost();
    }

    public void instructions() {
        System.out.println("Pour coffee from pot into cup");
    }
}
