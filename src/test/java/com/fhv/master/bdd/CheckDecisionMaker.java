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
public class CheckDecisionMaker {

    String _firstName ="";
    String _lastName= "";

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


    @Given("The customer {string} {string} wants a loan of {float} €.")
    public void theCustomerMaxHeermannWantsALoanOf(String firstName, String lastName, Float amount) {
        this.instance = this.runtimeService.startProcessInstanceByKey("Bank-credit-process-process");
        _firstName = firstName;
        _lastName = lastName;
        assertThat(this.instance).isNotNull();
        assertThat(this.instance).isStarted();
    }

    @When("The customers loan purpose of buying a {string} with the {float} € is collected.")
    public void theCustomersLoanPurposeOfBuyingACarIsCollected(String purpose, Float amount) {
        complete(task(this.instance), withVariables("customerFirstName", _firstName,
                "customerLastName", _lastName));
        assertThat(this.instance).hasPassed("Activity_1o07twh");

        complete(task(this.instance), withVariables("customerFirstName", _firstName,
                "customerLastName", _lastName, "approved", true));
        assertThat(this.instance).hasPassed("Activity_01iardk");

        complete(task(this.instance), withVariables("customerFirstName", _firstName,
                "customerLastName", _lastName, "loanAmount", amount,
                "loanPurpose", purpose, "approved", true));
        assertThat(this.instance).hasPassed("Activity_1ftc2sp");

    }

    @Then("The decision maker has to be {string}.")
    public void theDecisionMakerHasToBeManfredMueller(String decisionMaker) {
        complete(task(this.instance), withVariables("customerFirstName", _firstName,
                "customerLastName", _lastName, "approved", true, "decisionMaker", decisionMaker));
        assertThat(this.instance).hasPassed("Activity_1dn6i8y");
    }
}

