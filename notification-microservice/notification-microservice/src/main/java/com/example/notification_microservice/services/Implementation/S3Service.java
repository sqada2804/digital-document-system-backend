package com.example.notification_microservice.services.Implementation;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.example.notification_microservice.common.properties.S3Properties;
import com.example.notification_microservice.services.Interface.IS3Service;
import com.example.notification_microservice.utils.FileUtilTools;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class S3Service implements IS3Service {
    
    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public S3Service(AmazonS3 amazonS3, S3Properties s3Properties) {
        this.amazonS3 = amazonS3;
        this.s3Properties = s3Properties;
    }


    @Override
    public String uploadTemplate(MultipartFile multipartFile) {
        return Optional.of(multipartFile)
                .filter(given -> !given.isEmpty())
                .map(this::getObjectMetadata)
                .map(given -> this.uploadFileS3(given, multipartFile))
                .orElseThrow(() -> new RuntimeException("Error uploading file"));
    }

    private ObjectMetadata getObjectMetadata(MultipartFile metadata) {
        var objectMetadata = new ObjectMetadata();

        objectMetadata.setContentType(metadata.getContentType());
        objectMetadata.setContentLength(metadata.getSize());

        return objectMetadata;
    }

    private String uploadFileS3(ObjectMetadata objectMetadata, MultipartFile file) {
        String fileKey = UUID.randomUUID().toString().concat("." + FilenameUtils.getExtension(file.getOriginalFilename()));

        try {
            amazonS3.putObject(putObjectRequest(objectMetadata, fileKey, file));
            return fileKey;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PutObjectRequest putObjectRequest(ObjectMetadata objectMetadata, String fileKey, MultipartFile file) throws IOException{
        return new PutObjectRequest(
                s3Properties.getBucket(), fileKey, file.getInputStream(), objectMetadata
        );
    }



    @Override
    public File getFileByFilename(String filename) {
        return Optional.of(filename)
                .map(this::getObjectS3)
                .map(s3object -> getFileFromInputStream(filename, s3object))
                .orElseThrow(() -> new RuntimeException("Error getting object s3"));
    }

    private File getFileFromInputStream(String filename, S3Object s3object) {
        try {
            return FileUtilTools.downloadS3ObjectToFile(s3object.getObjectContent(), filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private S3Object getObjectS3(String fileKey) {
        return amazonS3.getObject(s3Properties.getBucket(), fileKey);
    }
}
