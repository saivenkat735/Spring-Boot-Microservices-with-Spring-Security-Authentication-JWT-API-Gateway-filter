package com.ust.Projectinfo.controller;


import com.ust.Projectinfo.dto.Projectdto;
import com.ust.Projectinfo.model.Project;
import com.ust.Projectinfo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/project")
public class Projectcontroller {

    @Autowired
    private ProjectService projectService;
    @PostMapping("/addproject")
    public ResponseEntity<Project> addproject(@RequestBody Projectdto project){
        return new ResponseEntity<>(projectService.addproject(project), HttpStatus.CREATED);
    }
    @GetMapping("/project/{ccode}")
    public ResponseEntity<List<Project>> getproject(@PathVariable Long ccode){
        return  ResponseEntity.ok(projectService.getProjectByCcode(ccode));
    }

}
