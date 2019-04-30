package com.lzx.hsapp;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

@Import(FdfsClientConfig.class)
@MapperScan({"com.lzx.hsapp.dao"})
@EnableScheduling
@SpringBootApplication
public class HsappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsappApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //文件最大
        factory.setMaxFileSize("20MB");  //KB,MB
        //设置总上传数据大小
        factory.setMaxRequestSize("100MB");

        return factory.createMultipartConfig();
    }

}
