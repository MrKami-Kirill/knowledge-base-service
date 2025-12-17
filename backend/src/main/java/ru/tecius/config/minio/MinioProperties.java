package ru.tecius.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("minio")
@Data
public class MinioProperties {

    private String endpoint;
    private String rootUser;
    private String rootPassword;
    private String region;
    private Bucket bucket;

    @Data
    public static class Bucket {

        private String knowledgeBaseService;

    }

}
