package art.timestart.spring.employees.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import art.timestart.spring.employees.entities.Employee;
import art.timestart.spring.employees.requests.EmployeeRequest;
import art.timestart.spring.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;





@RestController
@RequestMapping("/api/employees")
@Tag(name="Employee Rest API endpoints", description="Operations related to employees")
public class EmployeeRestController {
    private EmployeeService employeeService;

   

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Operation(summary="Get all employees", description="Retrieve a list of all employees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @Operation(summary="Fetch employee", description="Get a single employee by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable @Min(value=1) long id){
        Employee employee = employeeService.findById(id);
        return employee;
    }

    @Operation(summary="Create a new employee", description="Add a new employee to database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        Employee savedEmployee = employeeService.save(employeeRequest);
        return savedEmployee;
    }

    @Operation(summary="Update an employee", description="Update the details of a current employee")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updatEmployee(@PathVariable @Min(value=1) long id, @Valid @RequestBody EmployeeRequest employeeRequest){
        Employee updatedEmployee = employeeService.update(id, employeeRequest);
        return updatedEmployee;
    }

    @Operation(summary="Delete an employee", description="Delete the current employee")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatEmployee(@PathVariable @Min(value=1) long id){
        employeeService.deleteById(id);
    }
}
