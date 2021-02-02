package com.fhv.master.bdd;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.ProcessEngine;

@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {
                "camunda.bpm.generate-unique-process-engine-name=true",
                "camunda.bpm.generate-unique-process-application-name=true",
                "spring.datasource.generate-unique-name=true",
        }
)

public class EvaluatingCustomerCharacter {

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    RuntimeService runtimeService;
    ProcessInstance instance;

    @Before
    public void setUp() {
        init(this.processEngine);
    }

    @After
    public void tearDown() {
        System.out.println("Stopping a InvoiceProcess scenario");
    }

    @Given("The customer {string} {string} gives his firstname and lastname as registration.")
    public void theCustomerManfredSteinGivesHisFirstnameAndLastnameAsRegistration(String firstName, String lastName) {
    }

    @When("Customer gets character evaluated")
    public void customerGetsCharacterEvaluated() {
    }

    @Then("The customers character is getting rejected")
    public void theCustomersCharacterIsGettingRejected() {
    }

    



}
