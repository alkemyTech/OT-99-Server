package com.alkemy.ong.service;

import com.alkemy.ong.model.BASE64DecodedMultipartFile;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String uploadFile(BASE64DecodedMultipartFile file);

}
