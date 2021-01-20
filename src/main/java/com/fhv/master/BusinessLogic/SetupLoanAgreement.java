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
            String customerData = GetCustomerData();
            String loanData = GetLoanData();
            String cashier = GetCashier();
            SetupFile();
            InsertData(customerData,loanData, cashier);
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

    public void InsertData(String customerData, String loanData, String cashier) {
        StringBuilder stb = new StringBuilder();
        stb.append("Loan aggrement for: ");
        stb .append(customerData);
        stb.append("\nWith the following data: ");
        stb.append(loanData);
        stb.append("\nSigned by: ");
        stb.append(cashier);
        try {
            Files.write(Paths.get("LoanAgreement.txt"), stb.toString().getBytes(), StandardOpenOption.WRITE);
        }catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    public boolean SendAgreementToCustomer() {
        try {
            String customer = GetCustomerData();
            String loanAgreement = GetLoanData();
            String cashier = GetCashier();
            StringBuilder stb = new StringBuilder();
            stb.append("Sehr geehrter Kunde,");
            stb.append(customer);
            stb.append("\nWir senden Ihnen ihren Vertrag von ");
            stb.append(loanAgreement);
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