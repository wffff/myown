package com.snkj;

import com.snkj.www.baseconfig.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.snkj.www"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SNKJApplication {

    public static void main(String[] args) {
        SpringApplication.run(SNKJApplication.class, args);
    }
}
