package com.ust.Employeeinfo.controller;


import com.ust.Employeeinfo.client.Fullresponse;
import com.ust.Employeeinfo.model.Employee;
import com.ust.Employeeinfo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ustemps")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }
    @GetMapping("withprojects/{ccode}")
    public ResponseEntity<Fullresponse> getEmployeesByCcode(@PathVariable("ccode") Long ccode) {
        return ResponseEntity.ok(service.getEmployeesByProjectCcode(ccode));

    }
}
