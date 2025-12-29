//package ru.tecius.configuration.minio;
//
//import io.minio.MinioClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MinioConfig {
//
//    @Bean
//    public MinioClient minioClient(MinioProperties minioProperties) {
//        return MinioClient.builder()
//                .endpoint(minioProperties.getEndpoint())
//                .credentials(minioProperties.getRootUser(), minioProperties.getRootPassword())
//                .region(minioProperties.getRegion())
//                .build();
//    }
//
//}
