package com.fhv.master;

import javax.annotation.PostConstruct;

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@EnableProcessApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Autowired
  protected ProcessEngine processEngine;

  @PostConstruct
  public void deployInvoice() {
    ClassLoader classLoader = Application.class.getClassLoader();

    if(processEngine.getIdentityService().createUserQuery().list().isEmpty()) {
      processEngine.getRepositoryService()
              .createDeployment()
              .addInputStream("CustomerCreditRecord.bpmn", classLoader.getResourceAsStream("CustomerCreditRecord.bpmn"))
              .addInputStream("process.bpmn", classLoader.getResourceAsStream("process.bpmn"))
              .deploy();
    }


  }
}