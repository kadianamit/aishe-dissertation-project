package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.Faculty;
import com.nic.aishe.master.repo.FacultyRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class FacultyController {


    @Autowired(required = false)
    private FacultyRepo facultytRepo;

/*
    @PostMapping("/faculty")
    public Faculty addFaculty(@RequestBody Faculty Faculty) {
        return facultytRepo.save(Faculty);
    }

    @PutMapping("/faculty/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable Integer id, @RequestBody Faculty Faculty) {
        Faculty faculty = facultytRepo.findById(id).get();
        if (faculty != null) {
            Faculty.setId(faculty.getId());
            facultytRepo.save(Faculty);
            return ResponseEntity.ok(Faculty);
        }
        return ResponseEntity.notFound().build();
    }
*/


    @GetMapping("/faculty")
    public ResponseEntity<?> getAllFaculty(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<Faculty> Facultys = facultytRepo.findAll();
        if (Facultys != null && Facultys.size() > 0) {
            return ResponseEntity.ok(Facultys);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<?> getOneFaculty(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        Faculty Faculty = facultytRepo.findById(id).get();
        if (Faculty != null) {
            return ResponseEntity.ok(Faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/faculty-department/{id}")
    public ResponseEntity<?> getFacultyDepartment(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        Faculty Faculty = facultytRepo.findById(id).get();
        if (Faculty != null) {
            return ResponseEntity.ok(Faculty.listFacultyDepartments());
        }
        return ResponseEntity.notFound().build();
    }

/*

    @DeleteMapping("/faculty/{id}")
    public ResponseEntity<?> deleteOneFaculty(@PathVariable Integer id) {
        Faculty Faculty = facultytRepo.findById(id).get();
        if (Faculty != null) {
            facultytRepo.deleteById(id);
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/faculty")
    public ResponseEntity<?> deleteAllFaculty() {
        facultytRepo.deleteAll();
        return ResponseEntity.ok("All deleted Success !");
    }
*/


}
