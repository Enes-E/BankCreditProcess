package com.fhv.master.BusinessLogic;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public class SetupLoanAgreement implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(SetupLoanAgreement.class.getName());


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        SetupLoanAgreementTask();
    }
    public void execute() {
        SetupLoanAgreementTask();
    }

    public void SetupLoanAgreementTask() {
        String customerData = GetCustomerData();
        String loanData = GetLoanData();
        SetupFile();
        InsertData(customerData,loanData);

    }

    public void InsertData(String customerData, String loanData) {
        StringBuilder stb = new StringBuilder();
        stb.append("Loan aggrement for: ");
        stb .append(customerData);
        stb.append("\nWith the following data: ");
        stb.append(loanData);
        stb.append("\nSigned by: kami");
        try {
            Files.write(Paths.get("LoanAgreement.txt"), stb.toString().getBytes(), StandardOpenOption.WRITE);
        }catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }

    }

    public void SetupFile() {
        try {
            File myObj = new File("LoanAgreement.txt");
            if (myObj.createNewFile()) {
               LOGGER.info("File created: " + myObj.getName());
            } else {
                LOGGER.warning("File already exists.");
            }
        } catch (IOException e) {
            LOGGER.warning("An error occurred.");
            e.printStackTrace();
        }
    }

    public String GetLoanData() {
        return "Building a house for 1000000";
    }

    public String GetCustomerData() {
        return "Hans Meier";
    }


}
