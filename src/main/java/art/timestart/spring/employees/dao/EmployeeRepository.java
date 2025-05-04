package art.timestart.spring.employees.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import art.timestart.spring.employees.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
