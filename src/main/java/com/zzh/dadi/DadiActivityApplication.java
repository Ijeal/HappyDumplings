package com.zzh.dadi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages={ "com.zzh.dadi.dao" })
@ComponentScan(basePackages = { "com.zzh.dadi" })
public class DadiActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DadiActivityApplication.class, args);
	}
}
