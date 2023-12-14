package test.CoffeeTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.coffee.Coffee;

import static org.junit.Assert.assertEquals;

public class CoffeePackageTest {
    private Coffee coffeeObj;
    @BeforeEach
    public void setUp() {
        coffeeObj = new Coffee();
    }
    @Test
    public void testSetInfo() {
        coffeeObj.setCoffeeName("Test");
        coffeeObj.setManufacturer("Country");
        coffeeObj.setSort("Arabica");
        coffeeObj.setType("Grains");
        coffeeObj.setPriceFor100G(50);
        coffeeObj.setCoffeeWeight(250);
        coffeeObj.setPackageType("Jar");
        coffeeObj.setTerm(36);

        Assertions.assertEquals("Test", coffeeObj.getCoffeeName());
        Assertions.assertEquals("Country", coffeeObj.getManufacturer());
        Assertions.assertEquals("Arabica", coffeeObj.getSort());
        Assertions.assertEquals("Grains", coffeeObj.getType());
        Assertions.assertEquals(50, coffeeObj.getPriceFor100G());
        Assertions.assertEquals(250, coffeeObj.getCoffeeWeight());
        Assertions.assertEquals("Jar", coffeeObj.getPackageType());
        Assertions.assertEquals(36, coffeeObj.getTerm());
    }

    @Test
    public void testCalcGrade() {
        coffeeObj.calcGrade(4, 7, 9, 5);

        Assertions.assertEquals(6.25, coffeeObj.getGrade());
    }

    @Test
    public void testCalcTotalPrice() {
        coffeeObj.setPriceFor100G(50.00);
        coffeeObj.setCoffeeWeight(200);
        coffeeObj.setPackageType("Can");

        coffeeObj.calcTotalWeight();
        coffeeObj.calcTotalPrice();

        Assertions.assertEquals(100, coffeeObj.getCoffeePrice());
        Assertions.assertEquals(200, coffeeObj.getCoffeeWeight());
        Assertions.assertEquals("Can", coffeeObj.getPackageType());
        Assertions.assertEquals(60, coffeeObj.getPackageWeight());
        Assertions.assertEquals(90, coffeeObj.getPackagePrice());
        Assertions.assertEquals(190, coffeeObj.getTotalPrice());
    }
}
