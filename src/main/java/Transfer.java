import main.java.Department;
import main.java.Distribution;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Transfer {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\Data"));
        while (reader.ready()) {
            String allLine = reader.readLine();
            if (!allLine.equals("")) {
                //через этот метод создаём Person и отделы
                Distribution.distributionByDepartment(allLine);
            }


        }
        for (Department department : Department.getAllDepartment()) {
            //высчитываем среднюю ЗП по отделу
            department.averageSalaryCalculator();
        }

        for (Department department : Department.getAllDepartment()) {
            Distribution.redistribution(department);
        }

    }

}
