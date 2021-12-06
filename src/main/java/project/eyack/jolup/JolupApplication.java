package project.eyack.jolup;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.nio.file.PathMatcher;

@SpringBootApplication
public class JolupApplication {

    public static void main(String[] args) {
        SpringApplication.run(JolupApplication.class, args);
    }

}


