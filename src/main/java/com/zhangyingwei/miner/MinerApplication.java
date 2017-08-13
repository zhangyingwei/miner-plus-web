package com.zhangyingwei.miner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MinerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MinerApplication.class, args);
	}
}
