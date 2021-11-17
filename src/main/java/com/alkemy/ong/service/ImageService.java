package com.alkemy.ong.service;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String uploadFile(File file);

}
