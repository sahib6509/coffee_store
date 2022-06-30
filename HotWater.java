public class HotWater extends CoffeeDecorator {
    public HotWater(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

    @Override
    public String printCoffee() {
        return "Hot Water";
    }

    @Override
    public Double cost() {
        return 0.0;
    }
}
