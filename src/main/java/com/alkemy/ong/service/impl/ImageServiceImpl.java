package com.alkemy.ong.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.alkemy.ong.config.AmazonS3Config;
import com.alkemy.ong.service.ImageService;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    AmazonS3Config amazonS3Config;

    @Override
    public String uploadFile(File file) {
        String fileUrl = "";
        try {
//            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(file);
            fileUrl = amazonS3Config.getEndpointUrl() + "/" + amazonS3Config.getBucketName() + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

//    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(file.getOriginalFilename());
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }
    private String generateFileName(File file) {
        return new Date().getTime() + "-" + file.getName().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        amazonS3Config.initialize().putObject(new PutObjectRequest(amazonS3Config.getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

}
