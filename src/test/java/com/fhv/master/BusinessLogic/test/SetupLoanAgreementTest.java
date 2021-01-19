package com.fhv.master.BusinessLogic.test;

import com.fhv.master.BusinessLogic.SetupLoanAgreement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SetupLoanAgreementTest {
    SetupLoanAgreement setupLoanAgreement = new SetupLoanAgreement();

    @Test
    void execute() {
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
        setupLoanAgreement.InsertData("Hans Haus","Auto");
        Path path = Paths.get("LoanAgreement.txt");
        StringBuilder stb = new StringBuilder();
        try(Stream<String> stream = Files.lines(path)) {
            stream.forEach(s -> stb.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(stb.toString().contains("Hans Haus"));
        assertTrue(stb.toString().contains("Auto"));

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
}