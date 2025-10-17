/*
package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.Department;
import com.nic.aishe.master.repo.DepartmentRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class DepartmentController {


    @Autowired(required = false)
    private DepartmentRepo departmentRepo;

    @PostMapping("/department")
    public Department addDepartment(@RequestBody Department Department) {
        return departmentRepo.save(Department);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Integer id, @RequestBody Department Department) {
        Department dept = departmentRepo.findById(id).get();
        if (dept != null) {
            Department.setId(dept.getId());
            departmentRepo.save(Department);
            return ResponseEntity.ok(Department);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/department")
    public ResponseEntity<?> getAllDepartment(@WithUser UserInfo userInfo) {
        List<Department> Departments = departmentRepo.findAll();
        if (Departments != null && Departments.size() > 0) {
            return ResponseEntity.ok(Departments);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<?> getOneDepartment(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        Department Department = departmentRepo.findById(id).get();
        if (Department != null) {
            return ResponseEntity.ok(Department);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<?> deleteOneDepartment(@PathVariable Integer id) {
        Department Department = departmentRepo.findById(id).get();
        if (Department != null) {
            departmentRepo.deleteById(id);
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/department")
    public ResponseEntity<?> deleteAllDepartment() {
        departmentRepo.deleteAll();
        return ResponseEntity.ok("All deleted Success !");
    }


}
*/
