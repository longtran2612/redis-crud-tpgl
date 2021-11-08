package com.example.rediscrudtpgl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class EmployeeController  {

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        employeeRepo.saveEmployee(employee);
        return employee;
    }

    @GetMapping("/employees")
    public Set<Employee> findAll() {

        return employeeRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable("id") Integer id) {

        return employeeRepo.findById(id);
    }

    @PutMapping("/employee}")
    public void update(@RequestBody Employee employee) {

        employeeRepo.update(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable("id") Integer id) {
        employeeRepo.delete(id);
    }

}
