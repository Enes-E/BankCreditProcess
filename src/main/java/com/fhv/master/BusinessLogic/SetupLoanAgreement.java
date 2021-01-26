package com.fhv.master.BusinessLogic;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public class SetupLoanAgreement implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(SetupLoanAgreement.class.getName());
    private int loanAmount = 0;
    private String customerFirstName = "";
    private String customerLastName = "";
    private String loanPurpose = "";
    private String cashier = "";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        loanAmount = (Integer) delegateExecution.getVariable("loanAmount");
        customerFirstName = (String) delegateExecution.getVariable("customerFirstName");
        customerLastName = (String) delegateExecution.getVariable("customerFirstName");
        loanPurpose = (String) delegateExecution.getVariable("customerFirstName");
        cashier = (String) delegateExecution.getVariable("customerFirstName");
        execute();
    }

    public boolean execute() {
        try {
            SetupLoanAgreementTask();
            SendAgreementToCustomer();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean SetupLoanAgreementTask() {
        try{
            SetupFile();
            CreateAgreement(customerFirstName,customerLastName, loanAmount, loanPurpose, cashier);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

    public void CreateAgreement(String customerFirstName, String customerLastName, int loanAmount, String loanPurpose,
                                String cashier) {

        StringBuilder stb = new StringBuilder();
        stb.append("Loan aggrement for: ");
        stb.append(customerFirstName);
        stb.append(" ");
        stb.append(customerLastName);
        stb.append("\n With the following data: ");
        stb.append("\n Purpose: ");
        stb.append(loanPurpose);
        stb.append("\n Amount:");
        stb.append(loanAmount);
        stb.append("\n Signed by: ");
        stb.append(cashier);

        try {
            Files.write(Paths.get("LoanAgreement.txt"), stb.toString().getBytes(), StandardOpenOption.WRITE);
        }catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    public String getAgreementContent() throws IOException {
        try(FileInputStream inputStream = new FileInputStream("/LoanAgreement.txt")) {
            return IOUtils.toString(inputStream);
        }
    }

    public boolean SendAgreementToCustomer() {
        try {
            StringBuilder stb = new StringBuilder();
            stb.append("Sehr geehrter Kunde,");
            stb.append(customerFirstName);
            stb.append(customerLastName);
            stb.append("\n Es folgen die Vertragsdaten für die Kreditaufnahme: ");
            stb.append(getAgreementContent());
            stb.append("mit freundlichen Grüßen, ");
            stb.append(cashier);

            LOGGER.info("Agreement Sent");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String GetLoanData() {
        return "Building a house for 1000000";
    }

    public String GetCustomerData() {
        return "Hans Meier";
    }

    public String GetCashier(){
        return "kami";
    }
}