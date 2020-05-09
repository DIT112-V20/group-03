package online.quar.application.model;

public class CarControlInput {
    private long carId;
    private int carSetSpeed;
    private int carSetAngle;

    private int carActualSpeed;
    private int carActualAngle;

    public CarControlInput() {
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
}
