package online.quar.application.model;

import online.quar.application.Singleton;

public class Car {
    private long id;
    private int setSpeed;
    private int actualSpeed;
    private int setAngle;
    private int actualAngle;

    public Car() {
    }

    public Car(long id, int setSpeed, int actualSpeed, int setAngle, int actualAngle) {
        this.id = id;
        this.setSpeed = setSpeed;
        this.actualSpeed = actualSpeed;
        this.setAngle = setAngle;
        this.actualAngle = actualAngle;
    }

    public void save(){
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
}
