package com.alkemy.ong.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String uploadFile(MultipartFile file);

}
