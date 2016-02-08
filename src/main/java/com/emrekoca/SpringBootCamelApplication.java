package com.emrekoca;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootCamelApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootCamelApplication.class, args);
		/*
		 * String[] beanNames = context.getBeanDefinitionNames();
		 * Arrays.sort(beanNames); for (String beanName : beanNames) {
		 * System.out.println(beanName); }
		 */
		CamelSpringBootApplicationController applicationController = context
				.getBean(CamelSpringBootApplicationController.class);
		applicationController.blockMainThread();
	}
}
