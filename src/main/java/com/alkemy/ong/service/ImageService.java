package com.alkemy.ong.service;

import com.alkemy.ong.model.Base64DecodedMultipartFile;


public interface ImageService {

    public String uploadFile(Base64DecodedMultipartFile file);
    public Base64DecodedMultipartFile convert(String image);

}
