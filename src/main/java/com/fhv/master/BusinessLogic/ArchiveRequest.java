package com.fhv.master.BusinessLogic;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

import java.util.logging.Logger;

public class ArchiveRequest implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(ArchiveRequest.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Boolean shouldFail = (Boolean) delegateExecution.getVariable("shouldFail");
        FileValue invoiceDocumentVar  = delegateExecution.getVariableTyped("invoiceDocument");
        String invoiceNumber = (String) delegateExecution.getVariable("invoiceNumber");


        archiveInvoice(invoiceDocumentVar, invoiceNumber, shouldFail);
    }

    public void archiveInvoice(FileValue invoice, String invoiceNumber, Boolean shouldFail){
        if(shouldFail != null && shouldFail) {
            throw new ProcessEngineException("Could not archive invoice...");
        } else {
            LOGGER.info("\n\n  ... Now archiving invoice "+ invoiceNumber
                    +", filename: "+invoice.getFilename()+" \n\n");
        }
    }

}
