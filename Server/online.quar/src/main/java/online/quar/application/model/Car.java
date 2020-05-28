package online.quar.application.model;

import online.quar.application.Singleton;

public class Car {
    private long id;
    private int setSpeed = 0;
    private int actualSpeed = 0;
    private int setAngle = 0;
    private int actualAngle = 0;
    private String description;
    private boolean active = true;
    //TODO: online should be set correctly, for now, assume car always online.
    private boolean online = true;
    private boolean carObstacleAvoidance = false;
    private boolean carCollisionAvoidance = false;
    private int frontDistance = 0;
    private int leftFrontDistance = 0;
    private int rightFrontDistance = 0;
    private int rearDistance = 0;

    public Car() {
    }

    public Car(long id, String description, boolean active) {
        this.id = id;
        this.description = description;
        this.active = active;
    }

    public Car(long id, int setSpeed, int actualSpeed, int setAngle, int actualAngle, String description, boolean active) {
        this.id = id;
        this.setSpeed = setSpeed;
        this.actualSpeed = actualSpeed;
        this.setAngle = setAngle;
        this.actualAngle = actualAngle;
        this.description = description;
        this.active = active;
    }

    public void save() {
        Singleton.getApplicationManager().getDatabaseManager().save(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSetSpeed() {
        return setSpeed;
    }

    public void setSetSpeed(int setSpeed) {
        this.setSpeed = setSpeed;
    }

    public int getActualSpeed() {
        return actualSpeed;
    }

    public void setActualSpeed(int actualSpeed) {
        this.actualSpeed = actualSpeed;
    }

    public int getSetAngle() {
        return setAngle;
    }

    public void setSetAngle(int setAngle) {
        this.setAngle = setAngle;
    }

    public int getActualAngle() {
        return actualAngle;
    }

    public void setActualAngle(int actualAngle) {
        this.actualAngle = actualAngle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isCarObstacleAvoidance() {
        return carObstacleAvoidance;
    }

    public void setCarObstacleAvoidance(boolean carObstacleAvoidance) {
        this.carObstacleAvoidance = carObstacleAvoidance;
    }

    public boolean isCarCollisionAvoidance() {
        return carCollisionAvoidance;
    }

    public void setCarCollisionAvoidance(boolean carCollisionAvoidance) {
        this.carCollisionAvoidance = carCollisionAvoidance;
    }

    public int getFrontDistance() {
        return frontDistance;
    }

    public void setFrontDistance(int frontDistance) {
        this.frontDistance = frontDistance;
    }

    public int getLeftFrontDistance() {
        return leftFrontDistance;
    }

    public void setLeftFrontDistance(int leftFrontDistance) {
        this.leftFrontDistance = leftFrontDistance;
    }

    public int getRightFrontDistance() {
        return rightFrontDistance;
    }

    public void setRightFrontDistance(int rightFrontDistance) {
        this.rightFrontDistance = rightFrontDistance;
    }

    public int getRearDistance() {
        return rearDistance;
    }

    public void setRearDistance(int rearDistance) {
        this.rearDistance = rearDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        return getId() == car.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", setSpeed=" + setSpeed +
                ", actualSpeed=" + actualSpeed +
                ", setAngle=" + setAngle +
                ", actualAngle=" + actualAngle +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", online=" + online +
                '}';
    }
}
