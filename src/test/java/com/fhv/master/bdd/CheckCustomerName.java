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
public class CheckCustomerName {

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

    @Given("The customer {string} {string} gives his firstname and lastname.")
    public void theCustomerMaxHeermannGivesHisFirstnameAndLastname(String firstName, String lastName) {
        this.instance = this.runtimeService.startProcessInstanceByKey("Bank-credit-process-process",
                withVariables("loanAmount", 1000, "customerFirstName", firstName, "customerLastName", lastName,
                        "loanPurpose", "New Car",
                        "decisionMaker", "Manfred Mueller",
                        "approved", "True"));
        assertThat(this.instance).isNotNull();
        assertThat(this.instance).isStarted();
    }


    @And("The firstname and the lastname of {string} {string} is registered")
    public void theFirstnameAndTheLastnameOfMaxHeermannIsRegistered(String firstName, String lastName) {
        complete(task(this.instance), withVariables("customerFirstName", firstName,
                "customerLastName", lastName));
    }

    @When("Customer Data Collection is done")
    public void customerDataCollectionIsDone() {
        assertThat(this.instance).hasPassed("Activity_1o07twh");
        complete(task(this.instance), withVariables("customerFirstName", "Max",
                "customerLastName", "Heermann", "approved", true));
    }

    @Then("The customers character is evaluated")
    public void theCustomersCharacterIsEvaluated() {
        assertThat(this.instance).hasPassed("Activity_1o07twh");
    }

}
