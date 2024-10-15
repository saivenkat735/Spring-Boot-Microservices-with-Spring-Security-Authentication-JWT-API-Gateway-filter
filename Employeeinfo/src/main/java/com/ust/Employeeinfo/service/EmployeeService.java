package com.ust.Employeeinfo.service;

import com.ust.Employeeinfo.client.Fullresponse;
import com.ust.Employeeinfo.client.Project;
import com.ust.Employeeinfo.feign.Projectclient;
import com.ust.Employeeinfo.model.Employee;
import com.ust.Employeeinfo.repository.Employeerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private Employeerepository repo;
    @Autowired
    private Projectclient client;
    public Employee saveEmployee(Employee employee) {

        return repo.save(employee);
    }

    public Fullresponse getEmployeesByProjectCcode(Long ccode) {
        Employee employee =repo.findByCcode(ccode).orElse(null);
        List<Project> list=client.findProjectsByCcode(ccode);
        Fullresponse response = new Fullresponse();
        response.setName(employee.getName());
        response.setDepartment(employee.getDepartment());
        response.setDesignation(employee.getDesignation());
        response.setCcode(employee.getCcode());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setAddress(employee.getAddress());
        response.setCity(employee.getCity());
        response.setState(employee.getState());
        response.setExp(employee.getExp());
        response.setDoj(employee.getDoj());
        response.setSkills(employee.getSkills());
        response.setProjects(list);
        return response;
    }
}
