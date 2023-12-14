package test.CoffeeDataBaseTest;

import com.coffee.Coffee;
import com.coffee.CoffeeDataBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static test.forTests.initializeCoffee;

public class CoffeeDataBaseTest {
    private CoffeeDataBase coffeeDataBase;
    private Coffee coffeeObj;
    @BeforeEach
    public void setUp() {
        coffeeDataBase = new CoffeeDataBase();
        coffeeObj = new Coffee();
        initializeCoffee(coffeeObj);
    }
    @Test
    public void testAddCoffee() throws IOException {
        coffeeDataBase.addCoffee(coffeeObj);
        int initialSize = coffeeDataBase.getCoffeeListSize();
        assertEquals(initialSize, coffeeDataBase.getCoffeeListSize());
    }
    @Test
    public void testGetCoffee() throws IOException {
        coffeeDataBase.addCoffee(coffeeObj);
        Coffee coffee = coffeeDataBase.getCoffee(310, "Test");
        assertNotNull(coffee);
        assertEquals("Test", coffee.getCoffeeName());
        assertEquals(310, coffee.getTotalWeight());
    }
    @Test
    public void testDeleteCoffee() throws IOException {
        coffeeDataBase.addCoffee(coffeeObj);
        int initialSize = coffeeDataBase.getCoffeeListSize();
        coffeeDataBase.deleteCoffee(250, "Test");
        assertEquals(initialSize, coffeeDataBase.getCoffeeListSize());
    }
    @Test
    public void testShowAllCoffee() throws IOException {
        coffeeDataBase.addCoffee(coffeeObj);
        coffeeObj.setCoffeeName("Test2");
        coffeeObj.setManufacturer("Country");
        coffeeObj.setSort("Arabica");
        coffeeObj.setType("Grains");
        coffeeObj.setPriceFor100G(100);
        coffeeObj.setCoffeeWeight(500);
        coffeeObj.setPackageType("Can");
        coffeeObj.setTerm(36);
        coffeeObj.calcTotalWeight();
        coffeeObj.calcTotalPrice();
        coffeeObj.calcGrade(2, 3, 4, 5);
        coffeeDataBase.addCoffee(coffeeObj);
        coffeeDataBase.showAllCoffee();
    }
    @Test
    public void testShowAllCoffeeSorted() {
        coffeeDataBase.showAllCoffeeSorted();
    }
    @Test
    public void testShowCoffeeByQuality() throws IOException {
        coffeeObj.setCoffeeName("Test");
        coffeeObj.setManufacturer("Country");
        coffeeObj.setSort("Arabica");
        coffeeObj.setType("Grains");
        coffeeObj.setPriceFor100G(50);
        coffeeObj.setCoffeeWeight(250);
        coffeeObj.setPackageType("Jar");
        coffeeObj.setTerm(36);
        coffeeObj.calcTotalWeight();
        coffeeObj.calcTotalPrice();
        coffeeObj.calcGrade(4, 7, 9, 5);
        coffeeDataBase.addCoffee(coffeeObj);
        coffeeObj.setCoffeeName("Test2");
        coffeeObj.setManufacturer("Country");
        coffeeObj.setSort("Arabica");
        coffeeObj.setType("Grains");
        coffeeObj.setPriceFor100G(100);
        coffeeObj.setCoffeeWeight(500);
        coffeeObj.setPackageType("Can");
        coffeeObj.setTerm(36);
        coffeeObj.calcTotalWeight();
        coffeeObj.calcTotalPrice();
        coffeeObj.calcGrade(2, 3, 4, 5);
        coffeeDataBase.showCoffeeByQuality(5.0, 8.0);
    }
}
