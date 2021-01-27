package com.fhv.master.service;

import com.fhv.master.BusinessLogic.SetupLoanAgreement;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SetupLoanAgreementTest {
    SetupLoanAgreement setupLoanAgreement = new SetupLoanAgreement();

    @Test
    void execute() throws Exception {
        boolean execute = setupLoanAgreement.execute();
        assertTrue(execute);
    }

    @Test
    void setupLoanAgreementTask() throws FileNotFoundException {
        boolean setup = setupLoanAgreement.SetupLoanAgreementTask();
        assertTrue(setup);
    }

    @Test
    void setupFile() {
        setupLoanAgreement.SetupFile();
        File file = new File("LoanAgreement.txt");
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }

    @Test
    void insertData() throws FileNotFoundException {
        setupLoanAgreement.CreateAgreement("Max","Mustermann",10000,
                "Auto", "Renate");
        Path path = Paths.get("LoanAgreement.txt");
        StringBuilder stb = new StringBuilder();
        try(Stream<String> stream = Files.lines(path)) {
            stream.forEach(s -> stb.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(stb.toString().contains("Max"));
        assertTrue(stb.toString().contains("Mustermann"));
        assertTrue(stb.toString().contains("Max"));
        assertTrue(stb.toString().contains("Renate"));

    }

    @Test
    void SendAgreementToCustomer() {
        boolean sending = setupLoanAgreement.SendAgreementToCustomer();
        assertTrue(sending);
    }

    @Test
    void getLoanData() {
        String loanData = setupLoanAgreement.GetLoanData();
        assertNotSame("", loanData);
    }

    @Test
    void getCustomerData() {
        String cusomerData = setupLoanAgreement.GetCustomerData();
        assertNotSame("", cusomerData);
    }

    @Test
    void getCashier() {
        String cashier = setupLoanAgreement.GetCashier();
        assertNotSame("",cashier);
    }
}