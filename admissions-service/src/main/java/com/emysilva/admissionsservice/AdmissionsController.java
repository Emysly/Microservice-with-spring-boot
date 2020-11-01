package com.emysilva.admissionsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admissions")
public class AdmissionsController {

    private final RestTemplate restTemplate;

    @Autowired
    public AdmissionsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    List<Patient> patients = Arrays.asList(
            new Patient(1, "Emeka", "Okafor"),
            new Patient(2, "Victor", "Echendu"),
            new Patient(3, "Ugochukwu", "Nwike"));


    @GetMapping("/employees")
    public EmployeeList getEmployees() {
        return restTemplate.getForObject("http://hr-service/hr/employees", EmployeeList.class);
    }

    @GetMapping("/diseases")
    public PathologyList getPathologies() {
        return restTemplate.getForObject("http://pathology-service/pathology/diseases", PathologyList.class);
    }

    @GetMapping("/patients")
    public PatientList getPatients() {
        PatientList patientList = new PatientList();
        patientList.setPatients(patients);
        return patientList;
    }

    @GetMapping("/patients/{Id}")
    public Patient getPatient(@PathVariable("Id") Integer id) {
        return patients.stream().filter(product -> id.equals(product.getId())).findAny().orElse(null);
    }
}
