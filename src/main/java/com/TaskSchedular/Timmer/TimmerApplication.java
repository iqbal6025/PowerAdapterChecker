package com.TaskSchedular.Timmer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
@ComponentScan("com.TaskSchedular.ChargerConnected")
public class TimmerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TimmerApplication.class, args);
		
		
	}

}
