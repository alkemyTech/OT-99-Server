package com.alkemy.ong.service;

import com.alkemy.ong.model.BASE64DecodedMultipartFile;


public interface ImageService {

    public String uploadFile(BASE64DecodedMultipartFile file);

}
