package online.quar.application.model;

public class CarControlInput {
    private long carId;
    private int carSetSpeed;
    private int carSetAngle;

    private int carActualSpeed;
    private int carActualAngle;

    private boolean carObstacleAvoidance = false;
    private boolean carCollisionAvoidance = false;
    private int frontDistance = 0;
    private int leftFrontDistance = 0;
    private int rightFrontDistance = 0;
    private int rearDistance = 0;


    public CarControlInput() {
    }

    public CarControlInput(long carId, int carSetSpeed, int carSetAngle, int carActualSpeed, int carActualAngle, boolean carObstacleAvoidance, boolean carCollisionAvoidance) {
        this.carId = carId;
        this.carSetSpeed = carSetSpeed;
        this.carSetAngle = carSetAngle;
        this.carActualSpeed = carActualSpeed;
        this.carActualAngle = carActualAngle;
        this.carObstacleAvoidance = carObstacleAvoidance;
        this.carCollisionAvoidance = carCollisionAvoidance;
    }

    public CarControlInput(long carId, int carActualSpeed, int carActualAngle, boolean carObstacleAvoidance, boolean carCollisionAvoidance, int frontDistance, int leftFrontDistance, int rightFrontDistance, int rearDistance) {
        this.carId = carId;
        this.carActualSpeed = carActualSpeed;
        this.carActualAngle = carActualAngle;
        this.carObstacleAvoidance = carObstacleAvoidance;
        this.carCollisionAvoidance = carCollisionAvoidance;
        this.frontDistance = frontDistance;
        this.leftFrontDistance = leftFrontDistance;
        this.rightFrontDistance = rightFrontDistance;
        this.rearDistance = rearDistance;
    }

    public CarControlInput(long carId, int carSetSpeed, int carSetAngle) {
        this.carId = carId;
        this.carSetSpeed = carSetSpeed;
        this.carSetAngle = carSetAngle;
    }

    public CarControlInput(long carId, int carSetSpeed, int carSetAngle, int carActualSpeed, int carActualAngle) {
        this.carId = carId;
        this.carSetSpeed = carSetSpeed;
        this.carSetAngle = carSetAngle;
        this.carActualSpeed = carActualSpeed;
        this.carActualAngle = carActualAngle;
    }

    public CarControlInput(long carId) {
        this.carId = carId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public int getCarSetSpeed() {
        return carSetSpeed;
    }

    public void setCarSetSpeed(int carSetSpeed) {
        this.carSetSpeed = carSetSpeed;
    }

    public int getCarSetAngle() {
        return carSetAngle;
    }

    public void setCarSetAngle(int carSetAngle) {
        this.carSetAngle = carSetAngle;
    }

    public int getCarActualSpeed() {
        return carActualSpeed;
    }

    public void setCarActualSpeed(int carActualSpeed) {
        this.carActualSpeed = carActualSpeed;
    }

    public int getCarActualAngle() {
        return carActualAngle;
    }

    public void setCarActualAngle(int carActualAngle) {
        this.carActualAngle = carActualAngle;
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
    public String toString() {
        return "CarControlInput{" +
                "carId=" + carId +
                ", carSetSpeed=" + carSetSpeed +
                ", carSetAngle=" + carSetAngle +
                ", carActualSpeed=" + carActualSpeed +
                ", carActualAngle=" + carActualAngle +
                '}';
    }
    public String toJSON() {
        return "{" +
                 "\"id\":" + carId +
                ",\"setSpeed\":" + carSetSpeed +
                ",\"setAngle\":" + carSetAngle +
                ",\"actualSpeed\":" + carActualSpeed +
                ",\"actualAngle\":" + carActualAngle +
                '}';
    }
}
