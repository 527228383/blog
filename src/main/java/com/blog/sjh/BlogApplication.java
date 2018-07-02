package com.blog.sjh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching                 // 缓存开启注解
@EnableTransactionManagement   // 事务开启注解
@EnableAutoConfiguration       // 自动加载bean注解
@ComponentScan(basePackages = {"com"}) // 扫描包下的所有组件，包括启动类，servlet 等
@ServletComponentScan(basePackages = {"com"}) // 扫描包下的所有组件，包括 servlet、 filter等
@MapperScan(basePackages = {"com.blog.sjh.blog.login.dao"})
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
