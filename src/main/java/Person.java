package main.java;

public class Person {
    private String fullName;
    private float salary;

    public Person(String name, float salary) {
        this.fullName = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public float getSalary() {
        return salary;
    }
}
