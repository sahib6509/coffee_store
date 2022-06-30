import java.util.Objects;

public class WhippedCream extends CoffeeDecorator {
    public WhippedCream(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

    @Override
    public String printCoffee() {
        //WhippedCream whippedCream;
        if (this.coffee instanceof WhippedCream) {
            return "1";
        } else {
            return this.coffee.printCoffee() + "-whipped cream";
        }
    }

    @Override
    public Double cost() {
        if (this.coffee instanceof WhippedCream) {
            return 1.0;
        }
        else{
                return this.coffee.cost() + 0.10;
            }
        }
    }
