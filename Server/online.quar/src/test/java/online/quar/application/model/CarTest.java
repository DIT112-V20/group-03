package online.quar.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// @org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
    }

    //TODO: Finish this test
   /* @Test
    void save() {
        assertEquals(car, Singleton.getApplicationManager().getDatabaseManager().save(car));
    }*/

    @Test
    void getId() {
        car.setId(1);
        assertEquals(1, car.getId());
    }

    @Test
    void setId() {
        car.setId(3);
        assertEquals(3, car.getId());
    }

    @Test
    void getSetSpeed() {
        assertEquals(0, car.getSetSpeed());
        car.setSetSpeed(10);
        assertEquals(10, car.getSetSpeed());
    }

    @Test
    void setSetSpeed() {
        car.setSetSpeed(42);
        assertEquals(42, car.getSetSpeed());
    }

    @Test
    void getActualSpeed() {
        assertEquals(0, car.getActualSpeed());
        car.setActualSpeed(33);
        assertEquals(33, car.getActualSpeed());
    }

    @Test
    void setActualSpeed() {
        car.setActualSpeed(66);
        assertEquals(66, car.getActualSpeed());
    }

    @Test
    void getSetAngle() {
        assertEquals(0, car.getSetAngle());
        car.setSetAngle(340);
        assertEquals(340, car.getSetAngle());
    }

    @Test
    void setSetAngle() {
        car.setSetAngle(180);
        assertEquals(180, car.getSetAngle());
    }

    @Test
    void getActualAngle() {
        assertEquals(0, car.getActualAngle());
        car.setActualAngle(140);
        assertEquals(140, car.getActualAngle());
    }

    @Test
    void setActualAngle() {
        car.setActualAngle(40);
        assertEquals(40, car.getActualAngle());
    }

    @Test
    void getDescription() {
        car.setDescription("This is a description test");
        assertEquals("This is a description test", car.getDescription());
    }

    @Test
    void setDescription() {
        car.setDescription("And this is a description test for setting this text");
        assertEquals("And this is a description test for setting this text", car.getDescription());
    }

    @Test
    void isActive() {
        assertEquals(true, car.isActive());
        assertNotEquals(false, car.isActive());
    }

    @Test
    void setActive() {
        car.setActive(false);
        assertEquals(false, car.isActive());
        assertNotEquals(true, car.isActive());
    }

    @Test
    void isOnline() {
        assertEquals(true, car.isOnline());
        assertNotEquals(false, car.isOnline());
    }

    @Test
    void setOnline() {
        car.setOnline(false);
        assertEquals(false, car.isOnline());
        assertNotEquals(true, car.isOnline());
    }

    @Test
    void isCarObstacleAvoidance() {
        assertEquals(false, car.isCarObstacleAvoidance());
        assertNotEquals(true, car.isCarObstacleAvoidance());
    }

    @Test
    void setCarObstacleAvoidance() {
        car.setCarObstacleAvoidance(true);
        assertEquals(true, car.isCarObstacleAvoidance());
        assertNotEquals(false, car.isCarObstacleAvoidance());
    }

    @Test
    void isCarCollisionAvoidance() {
        assertEquals(false, car.isCarCollisionAvoidance());
        assertNotEquals(true, car.isCarCollisionAvoidance());
    }

    @Test
    void setCarCollisionAvoidance() {
        car.setCarCollisionAvoidance(true);
        assertEquals(true, car.isCarCollisionAvoidance());
        assertNotEquals(false, car.isCarCollisionAvoidance());
    }

    @Test
    void getFrontDistance() {
        assertEquals(0, car.getFrontDistance());
        car.setFrontDistance(22);
        assertEquals(22, car.getFrontDistance());
    }

    @Test
    void setFrontDistance() {
        car.setFrontDistance(12);
        assertEquals(12, car.getFrontDistance());
    }

    @Test
    void getLeftFrontDistance() {
        assertEquals(0, car.getLeftFrontDistance());
        car.setLeftFrontDistance(44);
        assertEquals(44, car.getLeftFrontDistance());
    }

    @Test
    void setLeftFrontDistance() {
        car.setLeftFrontDistance(14);
        assertEquals(14, car.getLeftFrontDistance());
    }

    @Test
    void getRightFrontDistance() {
        assertEquals(0, car.getRightFrontDistance());
        car.setRightFrontDistance(65);
        assertEquals(65, car.getRightFrontDistance());
    }

    @Test
    void setRightFrontDistance() {
        car.setRightFrontDistance(86);
        assertEquals(86, car.getRightFrontDistance());
    }

    @Test
    void getRearDistance() {
        assertEquals(0, car.getRearDistance());
        car.setRearDistance(98);
        assertEquals(98, car.getRearDistance());
    }

    @Test
    void setRearDistance() {
        car.setRearDistance(4);
        assertEquals(4, car.getRearDistance());
    }

    @Test
    void testEquals() {
        User user = new User();
        assertEquals(false, car.equals(user));
        assertNotEquals(true, car.equals(user));
    }

    @Test
    void testHashCode() {
        Car car1 = new Car();
        Car car2 = new Car();
        car1.setId(13);
        car2.setId(37);

        assertEquals(car1.hashCode(), car1.hashCode());
        assertEquals(car2.hashCode(), car2.hashCode());
        assertNotEquals(car1.hashCode(), car2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Car{id=0, setSpeed=0, actualSpeed=0, setAngle=0, " +
                "actualAngle=0, description='null', active=true, online=true}", car.toString());
    }
}