package com.emysilva.pathologyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/pathology")
public class PathologyController {

    private final RestTemplate restTemplate;

    List<Pathology> pathologies = Arrays.asList(
            new Pathology(1, "Malaria"),
            new Pathology(2, "Hepatitis"),
            new Pathology(3, "corona-virus"));

    @Autowired
    public PathologyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/patients")
    public PatientList getPatients() {
        return restTemplate.getForObject("http://admissions-service/admissions/patients", PatientList.class);
    }

    @GetMapping("/employees")
    public EmployeeList getEmployees() {
        return restTemplate.getForObject("http://hr-service/hr/employees", EmployeeList.class);
    }

    @GetMapping("/diseases")
    public PathologyList getPathologies() {
        PathologyList pathologyList = new PathologyList();
        pathologyList.setPathologyList(pathologies);
        return pathologyList;
    }

    @GetMapping("/diseases/{Id}")
    public Pathology getPathology(@PathVariable("Id") Integer id) {
        return pathologies.stream().filter(pathology -> id.equals(pathology.getId())).findAny().orElse(null);
    }
}