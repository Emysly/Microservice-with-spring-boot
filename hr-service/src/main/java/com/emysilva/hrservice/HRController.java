package com.emysilva.hrservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/hr")
public class HRController {

    private final RestTemplate restTemplate;

    @Autowired
    public HRController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    List<Employee> employees = Arrays.asList(
            new Employee(1, "Emeka", "Okafor", "Dentist"),
            new Employee(2, "Victor", "Echendu", "Lab Technician"),
            new Employee(3, "Ugochukwu", "Nwike", "Doctor"));

    @GetMapping("/patients")
    public PatientList getPatients() {
        return restTemplate.getForObject("http://admissions-service/admissions/patients", PatientList.class);
    }

    @GetMapping("/diseases")
    public PathologyList getPathologies() {
        return restTemplate.getForObject("http://pathology-service/pathology/diseases", PathologyList.class);
    }

    @GetMapping("/employees")
    public EmployeeList getEmployees() {
        EmployeeList employeeList = new EmployeeList();
        employeeList.setEmployeeList(employees);
        return employeeList;
    }

    @GetMapping("/employees/{Id}")
    public Employee getEmployee(@PathVariable("Id") Integer id) {
        return employees.stream().filter(employee -> id.equals(employee.getId())).findAny().orElse(null);
    }
}