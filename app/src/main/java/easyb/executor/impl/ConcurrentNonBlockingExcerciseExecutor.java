/* 
*    Executor that takes a Service and a Printer. Iterates over pages, one after another. Each time it gets a page runs async
*    a Printer to print the titles of a page.
*/

package easyb.executor.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import easyb.executor.ExcerciseAppExecutor;
import easyb.printer.TitlePrinter;
import easyb.service.PropertiesPagedService;
import easyb.service.vo.TitlePage;

public class ConcurrentNonBlockingExcerciseExecutor implements ExcerciseAppExecutor{

    private static final long MAX_SECONDS_FOR_PRINTER_TERMINATION = 120;
    private static final int NO_OF_PRINTER_THREADS = 3;
    private static final int BEFORE_FIRST_PAGE = 0;

    @Override
    public void execute(PropertiesPagedService ps, TitlePrinter printer) throws InterruptedException, ExecutionException {

        // async executor of printers
        ExecutorService printerExecutor = Executors.newFixedThreadPool(NO_OF_PRINTER_THREADS);

        // initial Page number
        int pageNumber = BEFORE_FIRST_PAGE;
        
        TitlePage titlePage;
        
        do{
            // next page
            pageNumber += 1;

            // future for paged service
            CompletableFuture<TitlePage> titlePageFuture = CompletableFuture.supplyAsync(new ServiceSupplier(ps,pageNumber));
            
            // blocks until future returns
            titlePage = titlePageFuture.get();
            
            // if there's any kind of error , stop processing pages
            if (titlePage.getPageNotProcessed())
                break;

            // print without blocking the loop
            printerExecutor.execute(new PrinterRunnable(printer, titlePage));
        
        } while(titlePage.thereArePendingPages());

        // wait for all printers to finish.
        waitForPrintersTermination(printerExecutor);

    }


    private void waitForPrintersTermination(ExecutorService printerExecutor) {
        printerExecutor.shutdown();
        try {
            if (!printerExecutor.awaitTermination(MAX_SECONDS_FOR_PRINTER_TERMINATION, TimeUnit.SECONDS)) {
                printerExecutor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            printerExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


    private static class ServiceSupplier implements Supplier<TitlePage>{

        private PropertiesPagedService pagedService;
        private int page;

        ServiceSupplier(PropertiesPagedService ps, int page){
            this.pagedService = ps;
            this.page = page;
        }

        @Override
        public TitlePage get() {
            TitlePage tp;

            try {
                tp = pagedService.getProperties(page);
            } catch (Exception e) {
                tp = new TitlePage();
                tp.setPageNonProcessed(true);             
            }
            return tp;
        }

    }

    private static class PrinterRunnable implements Runnable{

        private TitlePrinter printer;
        private TitlePage page;
    
        PrinterRunnable(TitlePrinter printer, TitlePage page){
            this.printer = printer;
            this.page = page;
        }
    
        @Override
        public void run() {
            printer.printTitlePage(page);
        }
    
    }
}

