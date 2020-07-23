package main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Distribution {

    public static void distributionByDepartment(String allLine) {
        String[] arrayData = allLine.split(" ");
        Person person = new Person(arrayData[0] + " " + arrayData[1] + " " + arrayData[2],
                Float.parseFloat(arrayData[4]));
        //Заполняем списки департаментов, а так же распрделяем людей по отделам.
        distribute(arrayData[3], person);

    }

    public static void distribute(String distribution, Person person) {
        if (Department.getAllDepartmentName().contains(distribution)) {
            Department department = Department.getInstance(distribution);
            department.getAllPersonInDepartment().add(person);
        } else {
            Department department = new Department(distribution);
            Department.getAllDepartmentName().add(distribution);
            Department.getAllDepartment().add(department);
            department.getAllPersonInDepartment().add(person);

        }
    }

    public static void redistribution(Department department) {
        ArrayList<Person> allPersonInDepartment = department.getAllPersonInDepartment();
        //средняя зп департамента
        float averageSalaryInDepartment = department.getAverageSalary();
        for (Department checkedDepartment : Department.getAllDepartment()) {
            if (!department.getDepartmentName().equals(checkedDepartment.getDepartmentName())) {
                //средняя зп департамента С КОТОРЫМ сраниваем
                float averageSalaryInCheckedDepartment = checkedDepartment.getAverageSalary();
                for (Person person : allPersonInDepartment) {
                    float postTransitionSalary = checkedDepartment.averageSalaryOnTransition(person);
                    if (averageSalaryInDepartment < department.averageSalaryUponLeaving(person) &&
                            averageSalaryInCheckedDepartment < postTransitionSalary) {
                        newData(person.getFullName(), department.getDepartmentName(), checkedDepartment.getDepartmentName(), checkedDepartment.getAverageSalary()
                                , postTransitionSalary);
                        System.out.println(person.getFullName() + ". Из " + department.getDepartmentName() + " .В " + checkedDepartment.getDepartmentName());
                        System.out.println("Откуда ушёл. Было " + department.getAverageSalary() + " .Стало " + department.averageSalaryUponLeaving(person));
                        System.out.println("Куда пришёл. Было " + checkedDepartment.getAverageSalary() + " .Стало " + checkedDepartment.averageSalaryOnTransition(person));
                        System.out.println("___________________________________");
                    }

                }
            }
        }
    }

    public static void newData(String personName, String oldNameDepartment, String newNameDepartment,
                               float oldAverageSalary, float newAverageSalary) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/PossibleTranslations", true))) {
            String allLine = String.format("%s перешёл из %s в %s. Старая зп в %s была - %.2f, новая %.2f\n", personName,
                    oldNameDepartment, newNameDepartment, newNameDepartment,  oldAverageSalary, newAverageSalary);
            bufferedWriter.write(allLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
