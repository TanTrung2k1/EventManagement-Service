package com.example.em.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {
    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {
        // Get the path to the project's image directory
        String projectDir = System.getProperty("user.dir");
        Path imageDir = Paths.get(projectDir, "images");

        // Get the file path for the requested image
        Path imagePath = imageDir.resolve(fileName);

        // Check if the image file exists
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        // Load the image file as a Resource
        Resource resource = new UrlResource(imagePath.toUri());

        // Return the image file as a response
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
