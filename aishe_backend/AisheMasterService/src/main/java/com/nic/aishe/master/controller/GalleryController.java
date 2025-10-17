/*
package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.Gallery;
import com.nic.aishe.master.repo.GalleryRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class GalleryController {

    @Autowired(required = false)
    private GalleryRepo galleryRepo;

*/
/*
    @PostMapping("/gallery")
    public Gallery addGallery(@RequestBody Gallery Gallery) {
        return galleryRepo.save(Gallery);
    }
*//*


    @GetMapping("/gallery")
    public List<Gallery> getAllGallery(@WithUser UserInfo userInfo) {
        return galleryRepo.findAll();
    }

    @GetMapping("/images")
    public Set<String> getImages(@RequestParam String path, @WithUser UserInfo userInfo) {
        return Stream.of(new File(path).listFiles()).filter(file -> !file.isDirectory()).map(File::getName)
                .collect(Collectors.toSet());
    }

    @GetMapping("/image")
    public ResponseEntity<?> getImage(@RequestParam String path, @RequestParam String key, @WithUser UserInfo userInfo) {
        final File img = new File(path + "/" + key);
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(Files.readAllBytes(img.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Data Not Found!");
    }

}
*/
