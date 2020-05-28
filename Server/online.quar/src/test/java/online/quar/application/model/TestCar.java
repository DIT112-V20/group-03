package online.quar.application.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class TestCar {

    Car car = new Car();

    @BeforeEach
    public void setUp() {
        car.setDescription("Something");
    }

    @AfterEach
    public void tearDown() {
    }

    @org.junit.jupiter.api.Test
    public void getId() {
        System.out.println("Inside getId");
        assertEquals(1,1);
    }

    @org.junit.jupiter.api.Test
    public void setId() {
        assertEquals(2,2);
    }
}