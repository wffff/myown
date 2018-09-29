package com.wanggoudan;

import com.wanggoudan.www.baseconfig.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.wanggoudan.www"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WGDApplication {

    public static void main(String[] args) {
        SpringApplication.run(WGDApplication.class, args);
    }
}
