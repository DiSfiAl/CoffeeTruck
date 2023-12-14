package test.CoffeePackingTest;

import com.coffee.Coffee;
import com.coffee.CoffeePackaging;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static test.forTests.initializeCoffee;

public class CoffeePackingTest {
    private CoffeePackaging coffeePackaging;
    private Coffee coffee;

    @BeforeEach
    public void setUp() {
        this.coffee = new Coffee();
        initializeCoffee(coffee);

        coffeePackaging = new CoffeePackaging(coffee, 5);
    }

    @Test
    public void testGetCoffee() {
        Coffee resultCoffee = coffeePackaging.getCoffee();
        assertNotNull(resultCoffee);
        assertEquals("Test", resultCoffee.getCoffeeName());
        assertEquals(250, resultCoffee.getCoffeeWeight());
    }

    @Test
    public void testGetNumberOfPackages() {
        int numberOfPackages = coffeePackaging.getNumberOfPackages();
        assertEquals(5, numberOfPackages);
    }

    @Test
    public void testSetNumberOfPackages() {
        coffeePackaging.setNumberOfPackages(10);
        int updatedNumberOfPackages = coffeePackaging.getNumberOfPackages();
        assertEquals(10, updatedNumberOfPackages);
    }

    @Test
    public void testShowInfo() {
        coffeePackaging.showInfo();
    }

    @Test
    public void testToWrite() {
        String expectedString = "Coffee name: Test\nManufacturer: Country\nSort: Arabica\nType: Grains\nPrice: 173.0 UAH\n" +
                "Package type: Jar\nWeight: 310.0 grams\nCoffee Weight: 250.0 grams\nPackage Weight: 60.0 grams\n" +
                "Term: 36 month\nGrade: 6.25\nNumber of packages: 5\n";
        String resultString = coffeePackaging.toWrite();
        assertEquals(expectedString, resultString);
    }
}
