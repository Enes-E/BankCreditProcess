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
@CucumberContextConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
public class CheckCharacter {

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



    @Given("I have the firstname and the lastname")
    public void iHaveTheFirstnameAndTheLastname() {
    }

    @And("I check the yes checkbox")
    public void iCheckTheYesCheckbox() {
    }

    @When("I confirm my entries")
    public void iConfirmMyEntries() {
    }

    @Then("It should be checked if the customers character is ok")
    public void itShouldBeCheckedIfTheCustomersCharacterIsOk() {
    }
}
