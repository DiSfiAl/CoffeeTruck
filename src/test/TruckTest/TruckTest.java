package test.TruckTest;

import com.coffee.Coffee;
import com.truck.Truck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static test.forTests.initializeCoffee;

public class TruckTest {
    private Truck truck;
    private Coffee coffee;

    @BeforeEach
    public void setUp() {
        truck = new Truck();
        truck.setTruckName("TestTruck");
        truck.setMaxWeight(50000);

        coffee = new Coffee();
        initializeCoffee(coffee);
    }

    @Test
    public void testGetTruckName() {
        assertEquals("TestTruck", truck.getTruckName());
    }

    @Test
    public void testGetMaxWeight() {
        assertEquals(50000, truck.getMaxWeight());
    }

    @Test
    public void testSetCurrentWeight() {
        assertTrue(truck.setCurrentWeight(1000, 3));
        assertEquals(3000, truck.getCurrentWeight());
    }

    @Test
    public void testSetWrongCurrentWeight() {
        assertFalse(truck.setCurrentWeight(100000, 1));
    }

    @Test
    public void testGetCurrentWeight() {
        truck.setCurrentWeight(2000, 2);
        assertEquals(4000, truck.getCurrentWeight());
    }

    @Test
    public void testSetCurrentWeightExceedsMaxWeight() {
        assertFalse(truck.setCurrentWeight(20000, 5));
    }

    @Test
    public void testGetInfo() {
        truck.getInfo();
    }

    @Test
    public void testShowAllPackedCoffee() {
        truck.showAllPackedCoffee();
    }

    @Test
    public void testReadTruckInfoFromFile() {
        try {
            truck.readTruckInfoFromFile("TestTruckInfo.txt");
            assertEquals("TestTruck", truck.getTruckName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testShowCoffeeByQualityInTruck() throws IOException {
        truck.addCoffee(coffee);
        truck.showCoffeeByQualityInTruck(4, 7);
    }
}
