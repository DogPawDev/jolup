package project.eyack.jolup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class Config {

    private final int FILE_MAX_UPLOAD_SIZE = 10585760;

    @Bean
    public MultipartResolver multipartResolver(){
        org.springframework.web.multipart.commons.CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(FILE_MAX_UPLOAD_SIZE);
        return commonsMultipartResolver;
    }


}
