public class BasicCoffee implements Coffee {
    @Override
    public void addTopping(Coffee coffee) {

    }

    @Override
    public String printCoffee() {
        return "Black coffee";
    }

    @Override
    public Double cost() {
        return 0.85;
    }
}
