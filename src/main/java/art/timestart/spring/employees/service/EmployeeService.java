package art.timestart.spring.employees.service;

import java.util.List;

import art.timestart.spring.employees.entities.Employee;
import art.timestart.spring.employees.requests.EmployeeRequest;

public interface  EmployeeService {
        
    List<Employee> findAll();

    Employee findById(long id);

    Employee save(EmployeeRequest employeeRequest);

    Employee update(long id, EmployeeRequest employeeRequest);
    
    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);

    void deleteById(long id);

}
