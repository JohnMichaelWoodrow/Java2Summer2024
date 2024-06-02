package question3;

import java.io.Serializable;

public class Settings implements Serializable {
    private String name;
    private double height;
    private double weight;
    private String birthday;
    private int functionalThresholdPower;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getFunctionalThresholdPower() {
        return functionalThresholdPower;
    }

    public void setFunctionalThresholdPower(int functionalThresholdPower) {
        this.functionalThresholdPower = functionalThresholdPower;
    }
}
