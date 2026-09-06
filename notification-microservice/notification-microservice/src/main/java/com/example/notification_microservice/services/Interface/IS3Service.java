package com.example.notification_microservice.services.Interface;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface IS3Service {
    String uploadTemplate(MultipartFile multipartFile);
    File getFileByFilename(String filename);
    String uploadHtml(String filename, String html);
}
