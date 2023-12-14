package test;

import com.coffee.Coffee;

public abstract class forTests {
    public static void initializeCoffee(Coffee coffee) {
        coffee.setCoffeeName("Test");
        coffee.setManufacturer("Country");
        coffee.setSort("Arabica");
        coffee.setType("Grains");
        coffee.setPriceFor100G(50);
        coffee.setCoffeeWeight(250);
        coffee.setPackageType("Jar");
        coffee.setTerm(36);
        coffee.calcTotalWeight();
        coffee.calcTotalPrice();
        coffee.calcGrade(4, 7, 9, 5);
    }
}