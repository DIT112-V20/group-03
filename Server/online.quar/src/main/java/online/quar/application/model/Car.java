package online.quar.application.model;

import online.quar.application.Singleton;

public class Car {
    private long id;
    private int setSpeed;
    private int actualSpeed;
    private int setAngle;
    private int actualAngle;
    private String description;
    private boolean active = true;

    public Car() {
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
}
