package art.timestart.spring.employees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import art.timestart.spring.employees.dao.EmployeeRepository;
import art.timestart.spring.employees.entities.Employee;
import art.timestart.spring.employees.requests.EmployeeRequest;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements  EmployeeService{

    private  EmployeeRepository employeeRepository;

    

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
       Optional<Employee> result = employeeRepository.findById(id);

       Employee employee = null;
       if(result.isPresent()){
        employee = result.get();
       }else{
        throw new RuntimeException("Didnt find employee id " + id);
       }

       return employee;
       
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee savedEmployee = convertToEmployee(0, employeeRequest);
        return employeeRepository.save(savedEmployee);
    }

    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee updatedEmployee = convertToEmployee(id, employeeRequest);
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id, 
                            employeeRequest.getFirstName(), 
                            employeeRequest.getLastName(), 
                            employeeRequest.getEmail());
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    
    
}
