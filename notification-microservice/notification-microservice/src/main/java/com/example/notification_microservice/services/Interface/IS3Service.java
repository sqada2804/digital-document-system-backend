package com.example.notification_microservice.services.Interface;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IS3Service {
    String uploadTemplate(MultipartFile multipartFile);
    File getFileByFilename(String filename);
}
