package com.zhangyingwei.miner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@Configuration
@MapperScan(basePackages = { "com.zhangyingwei.miner.mapper"})
@PropertySource("classpath:miner.properties")
public class MinerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MinerApplication.class, args);
	}
}
