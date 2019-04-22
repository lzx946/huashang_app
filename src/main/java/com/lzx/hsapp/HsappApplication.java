package com.lzx.hsapp;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(FdfsClientConfig.class)
@MapperScan({"com.lzx.hsapp.dao"})
@SpringBootApplication
public class HsappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsappApplication.class, args);
    }

}
