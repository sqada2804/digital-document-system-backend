package com.example.notification_microservice.controller.Implementation;


import com.example.notification_microservice.controller.Interface.INotificationController;
import com.example.notification_microservice.services.Interface.IS3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class NotificationController implements INotificationController {

    private final IS3Service s3Service;

    public NotificationController(IS3Service s3Service) {
        this.s3Service = s3Service;
    }

    @Override
    public ResponseEntity<String> uploadTemplate(MultipartFile multipartFile) {
        return ResponseEntity.ok(s3Service.uploadTemplate(multipartFile));
    }
}
