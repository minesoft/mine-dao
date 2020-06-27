package cn.sixlab.mine.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.sixlab.mine.dao.dao")
@SpringBootApplication
public class MineDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MineDaoApplication.class, args);
    }

}
