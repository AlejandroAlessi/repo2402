package easyb.executor;

import java.util.concurrent.ExecutionException;
import easyb.printer.TitlePrinter;
import easyb.service.PropertiesPagedService;

public interface ExcerciseAppExecutor {
    public void execute(PropertiesPagedService ps, TitlePrinter tp) throws InterruptedException, ExecutionException;
}    

