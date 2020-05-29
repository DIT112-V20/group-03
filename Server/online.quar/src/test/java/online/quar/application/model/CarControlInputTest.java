package online.quar.application.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarControlInputTest {
    CarControlInput carControlInput;
    @BeforeEach
    void setUp() {
        carControlInput = new CarControlInput();
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void getCarId() {
        carControlInput.setCarId(1);
        assertEquals(1, carControlInput.getCarId());
    }

    @Test
    void setCarId() {
        carControlInput.setCarId(1);
        assertEquals(1, carControlInput.getCarId());
    }

    @Test
    void getCarSetSpeed() {
        carControlInput.setCarSetSpeed(1);
        assertEquals(1, carControlInput.getCarSetSpeed());
    }

    @Test
    void setCarSetSpeed() {
        carControlInput.setCarSetSpeed(1);
        assertEquals(1, carControlInput.getCarSetSpeed());
    }

    @Test
    void getCarSetAngle() {
        carControlInput.setCarSetAngle(1);
        assertEquals(1, carControlInput.getCarSetAngle());
    }

    @Test
    void setCarSetAngle() {
        carControlInput.setCarSetAngle(1);
        assertEquals(1, carControlInput.getCarSetAngle());
    }

    @Test
    void getCarActualSpeed() {
        carControlInput.setCarActualSpeed(1);
        assertEquals(1, carControlInput.getCarActualSpeed());
    }

    @Test
    void setCarActualSpeed() {
        carControlInput.setCarActualSpeed(1);
        assertEquals(1, carControlInput.getCarActualSpeed());
    }

    @Test
    void getCarActualAngle() {
        carControlInput.setCarActualAngle(1);
        assertEquals(1, carControlInput.getCarActualAngle());
    }

    @Test
    void setCarActualAngle() {
        carControlInput.setCarActualAngle(1);
        assertEquals(1, carControlInput.getCarActualAngle());
    }

    @Test
    void isCarObstacleAvoidance() {
        carControlInput.setCarObstacleAvoidance(true);
        assertTrue(carControlInput.isCarObstacleAvoidance());
    }

    @Test
    void setCarObstacleAvoidance() {
        carControlInput.setCarObstacleAvoidance(true);
        assertTrue(carControlInput.isCarObstacleAvoidance());
    }

    @Test
    void isCarCollisionAvoidance() {
        carControlInput.setCarCollisionAvoidance(true);
        assertTrue(carControlInput.isCarCollisionAvoidance());
    }

    @Test
    void setCarCollisionAvoidance() {
        carControlInput.setCarCollisionAvoidance(true);
        assertTrue(carControlInput.isCarCollisionAvoidance());
    }

    @Test
    void getFrontDistance() {
        carControlInput.setFrontDistance(1);
        assertEquals(1, carControlInput.getFrontDistance());
    }

    @Test
    void setFrontDistance() {
        carControlInput.setFrontDistance(1);
        assertEquals(1, carControlInput.getFrontDistance());
    }

    @Test
    void getLeftFrontDistance() {
        carControlInput.setLeftFrontDistance(1);
        assertEquals(1, carControlInput.getLeftFrontDistance());
    }

    @Test
    void setLeftFrontDistance() {
        carControlInput.setLeftFrontDistance(1);
        assertEquals(1, carControlInput.getLeftFrontDistance());
    }

    @Test
    void getRightFrontDistance() {
        carControlInput.setRightFrontDistance(1);
        assertEquals(1, carControlInput.getRightFrontDistance());
    }

    @Test
    void setRightFrontDistance() {
        carControlInput.setRightFrontDistance(1);
        assertEquals(1, carControlInput.getRightFrontDistance());
    }

    @Test
    void getRearDistance() {
        carControlInput.setRearDistance(1);
        assertEquals(1, carControlInput.getRearDistance());
    }

    @Test
    void setRearDistance() {
        carControlInput.setRearDistance(1);
        assertEquals(1, carControlInput.getRearDistance());
    }

    @Test
    void testToString() {
        String expectedString = "CarControlInput{" +
                "carId=" + carControlInput.getCarId() +
                ", carSetSpeed=" + carControlInput.getCarSetSpeed() +
                ", carSetAngle=" + carControlInput.getCarSetAngle() +
                ", carActualSpeed=" + carControlInput.getCarActualSpeed() +
                ", carActualAngle=" + carControlInput.getCarActualAngle() +
                '}';
        assertEquals(expectedString, carControlInput.toString());
    }

    @Test
    void testToJSON() {
        String expectedString = "{" +
                "\"id\":" + carControlInput.getCarId() +
                ",\"setSpeed\":" + carControlInput.getCarSetSpeed() +
                ",\"setAngle\":" + carControlInput.getCarSetAngle() +
                ",\"actualSpeed\":" + carControlInput.getCarActualSpeed() +
                ",\"actualAngle\":" + carControlInput.getCarActualAngle() +
                '}';
        assertEquals(expectedString, carControlInput.toJSON());
    }
}