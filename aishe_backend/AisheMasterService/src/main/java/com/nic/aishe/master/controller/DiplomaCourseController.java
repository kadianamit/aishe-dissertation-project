package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.DiplomaType;
import com.nic.aishe.master.impl.DiplomaCourseService;
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
public class DiplomaCourseController {


    @Autowired(required = false)
    private DiplomaCourseService diplomaCourse;

/*
    @PostMapping("/diploma-type")
    public ResponseEntity<?> addType(@RequestBody DiplomaType diploma) {
        DiplomaType course = diplomaCourse.adddiplomaCourse(diploma);
        return ResponseEntity.ok(course);
    }
*/

    @GetMapping("/diploma-type")
    public ResponseEntity<?> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<DiplomaType> courses = diplomaCourse.getAlldiplomaCourse();
        if (courses != null && courses.size() > 0) {

            return ResponseEntity.ok(courses);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/diploma-type/{id}")
    public ResponseEntity<?> getOneType(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        DiplomaType course = diplomaCourse.getOnediplomaCourse(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();

    }

  /*  @DeleteMapping("/diploma-type/{id}")
    public ResponseEntity<?> deleteOneType(@PathVariable Integer id) {
        Boolean isdel = diplomaCourse.deleteOnediplomaCourse(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/diploma-type")
    public ResponseEntity<?> deleteAllType() {
        diplomaCourse.deleteAlldiplomaCourse();

        return ResponseEntity.ok("All deleted Success !");
    }
*/

}
