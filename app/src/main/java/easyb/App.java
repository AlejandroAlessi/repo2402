package easyb;

import java.util.concurrent.ExecutionException;

import easyb.executor.ExcerciseAppExecutor;
import easyb.executor.ExecutorFactory;
import easyb.printer.PrinterFactory;
import easyb.printer.TitlePrinter;
import easyb.service.PropertiesPagedService;
import easyb.service.ServiceFactory;

public class App {

    public static void main(String[] args) {
        System.out.println("Starting app...");
        try {
            new App().execute();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Terminating with error...");
            e.printStackTrace();
        }         
    }
    
    public void execute() throws InterruptedException, ExecutionException{     
        // It'd be better to inject those dependencies with DI (ie Spring)
        PropertiesPagedService service = ServiceFactory.getPropertiesPagedService();
        TitlePrinter printer = PrinterFactory.getPrinter();
        ExcerciseAppExecutor executor = ExecutorFactory.getExecutor();
        executor.execute(service, printer);
    } 
}