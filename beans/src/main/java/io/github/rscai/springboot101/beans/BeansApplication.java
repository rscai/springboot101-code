package io.github.rscai.springboot101.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BeansApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(BeansApplication.class, args);

    for (final String beanName : context.getBeanDefinitionNames()) {
      System.out.println(String.format("Bean: %s", beanName));
    }
  }
}
