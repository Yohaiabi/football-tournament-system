package core;

import java.io.Serializable;
import java.util.Objects;
import utils.Country;

/**
 * Abstract base class representing a person in the system.
 */
public abstract class Person implements Serializable {
    protected String pId;
    protected String fullName;
    protected int age;
    protected Country nation;

    public Person(String pId, String fullName, int age, String nation) {
        this.pId = pId;
        this.fullName = fullName;
        this.age = age;
        setNation(nation);
    }

    public Person(String pId) {
        this.pId = pId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Country getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = Country.getCountryByName(nation);
    }

    @Override
    public String toString() {
        return String.format("Name: %s (%s), Age: %d, Country: %s",
                fullName, pId, age, nation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(pId, person.pId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pId);
    }
}