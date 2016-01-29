package com.emrekoca;

import java.util.Arrays;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootCamelApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootCamelApplication.class, args);
		/*
		String[] beanNames = context.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
		*/
		CamelSpringBootApplicationController applicationController =
				context.getBean(CamelSpringBootApplicationController.class);
	    applicationController.blockMainThread();
		// Solution 1
		// CamelContext camelContext = context
		// .getBean("camelContext", CamelContext.class);
		// CamelSpringBootApplicationController
		// camelSpringBootApplicationController =
		// new CamelSpringBootApplicationController(context, camelContext);
		// camelSpringBootApplicationController.blockMainThread();
		// Solution 2
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
	}
}
