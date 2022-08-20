package com.example.shoppe_food.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadImageRequest {
    MultipartFile file;
}
