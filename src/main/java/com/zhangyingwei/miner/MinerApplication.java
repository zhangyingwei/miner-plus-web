package com.zhangyingwei.miner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@MapperScan(basePackages = { "com.zhangyingwei.miner.mapper"})
public class MinerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MinerApplication.class, args);
	}
}
