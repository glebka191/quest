package main.java;

import java.util.ArrayList;

public class Department {
    private static ArrayList<String> allDepartmentName = new ArrayList<>();
    private static ArrayList<Department> allDepartment = new ArrayList<>();
    private ArrayList<Person> allPersonInDepartment;
    private String departmentName;
    private float averageSalary;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        allPersonInDepartment = new ArrayList<>();

    }

    public static ArrayList<String> getAllDepartmentName() {
        return allDepartmentName;
    }

    public static ArrayList<Department> getAllDepartment() {
        return allDepartment;
    }

    public static Department getInstance(String departmentName){
        for (Department department : allDepartment){
            if (department.departmentName.equals(departmentName)){
                return department;
            }
        }
        return null;
    }

    public ArrayList<Person> getAllPersonInDepartment() {
        return allPersonInDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public float getAverageSalary() {
        return averageSalary;
    }

    public void averageSalaryCalculator(){
        float amount = 0;
        for (Person person : getAllPersonInDepartment()){
            amount+=person.getSalary();
        }
        averageSalary = amount/ getAllPersonInDepartment().size();

    }

    public float averageSalaryUponLeaving(Person person){
        float totalSalary = averageSalary * this.allPersonInDepartment.size();
        float newAverageSalary = (totalSalary - person.getSalary() )/(this.allPersonInDepartment.size()-1);
        return newAverageSalary;
    }

    public  float averageSalaryOnTransition(Person person){
        float totalSalary = averageSalary * this.allPersonInDepartment.size();
        float newAverageSalary = (totalSalary + person.getSalary() )/(this.allPersonInDepartment.size()+1);
        return newAverageSalary;

    }
}
