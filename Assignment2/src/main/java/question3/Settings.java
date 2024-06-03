package question3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Settings")
public class Settings {
    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Height")
    private double height;

    @XmlElement(name = "Weight")
    private double weight;

    @XmlElement(name = "Birthday")
    private String birthday;

    @XmlElement(name = "FunctionalThresholdPower")
    private double functionalThresholdPower;

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

    public double getFunctionalThresholdPower() {
        return functionalThresholdPower;
    }

    public void setFunctionalThresholdPower(double functionalThresholdPower) {
        this.functionalThresholdPower = functionalThresholdPower;
    }
}


