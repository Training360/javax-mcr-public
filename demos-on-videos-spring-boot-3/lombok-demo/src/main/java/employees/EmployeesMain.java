package employees;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class EmployeesMain {

    public static void main(String[] args) {
        Employee employee = new Employee("John", 1980);
//        employee.setName(null);
//        employee.updateName(null, "Doe");
//        employee = new Employee();
//        employee = new Employee("John");
        log.info(employee.toString());
    }
}
