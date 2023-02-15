package me.geekkid.studentinformationmanager.student;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty sex;
    private IntegerProperty age;
    private StringProperty phoneNumber;
    private StringProperty address;

    public Student(Integer id, String name, String sex, Integer age, String phoneNumber, String address) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.sex = new SimpleStringProperty(sex);
        this.age = new SimpleIntegerProperty(age);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public StringProperty getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = new SimpleStringProperty(sex);
    }

    public IntegerProperty getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = new SimpleIntegerProperty(age);
    }

    public StringProperty getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public StringProperty getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }
}
