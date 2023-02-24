package easyb;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import easyb.service.PropertiesPagedService;
import easyb.executor.impl.ConcurrentNonBlockingExcerciseExecutor;

public class ConcurrentExecutorTest {

    private static int QTY_OF_PRINTS_EXPECTED = 0;
    private static PropertiesPagedService service;
    private static MockedPrinter printer;

    @BeforeAll static void init () {
        service = new MockedService();
        printer = new MockedPrinter();
        QTY_OF_PRINTS_EXPECTED = 5;
    }
    
    @Test void should_print5Titles_when_5TitlesInTwoPagesComeFromApi() {

        ConcurrentNonBlockingExcerciseExecutor executor = new ConcurrentNonBlockingExcerciseExecutor();

        assertDoesNotThrow(() -> executor.execute(service, printer),"should not throw any exception"); 
        assertTrue(printer.getPrintCounter() == QTY_OF_PRINTS_EXPECTED, "should print the number of titles of the input file");
    }
}
