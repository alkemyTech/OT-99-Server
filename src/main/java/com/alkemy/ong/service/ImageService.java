package com.alkemy.ong.service;

import com.alkemy.ong.model.Base64MultipartFile;


public interface ImageService {

    public String uploadFile(Base64MultipartFile file);
    public Base64MultipartFile convert(String image);

}
