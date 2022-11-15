package com.example.crudapk.Controller;

import com.example.crudapk.Model.Employee;
import com.example.crudapk.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/addEmployees")
    public Employee createEmployee(@Validated @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id) {
       Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee with Id " +id+ " not found"));
       return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/UpdateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable (value = "id") long id,@RequestBody Employee employeeDetails) {
        Employee UpdateEmployee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee with Id " +id+ " not found"));
        UpdateEmployee.setFirstname(employeeDetails.getFirstname());
        UpdateEmployee.setLastname(employeeDetails.getLastname());
        UpdateEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.save(UpdateEmployee);
        return new ResponseEntity<>(UpdateEmployee,HttpStatus.OK);

    }

    @DeleteMapping("/DeleteEmployee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") long Id) {
        employeeRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Employee with Id " +Id+ " not found"));
        employeeRepository.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
