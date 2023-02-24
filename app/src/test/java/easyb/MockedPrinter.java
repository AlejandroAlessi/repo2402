/*
* Mocked printer that counts how many times it prints.  
* Better use Mockito or another mocking lib, but regarding this excercise 
* is just fine to code a simple mock.
**/

package easyb;

import easyb.printer.impl.SimpleTitlePagePrinter;

public class MockedPrinter extends SimpleTitlePagePrinter {

    private int printCounter = 0;

    public int getPrintCounter() {
        return printCounter;
    }

    public void setPrintCounter(int printCounter) {
        this.printCounter = printCounter;
    }

    public MockedPrinter(){
        super();
    }

    protected void printSingleTitle(String title){
        super.printSingleTitle(title);
        incrPrintCounter();
    }

    // synchronized because the printer may be called by several threads.
    private synchronized void incrPrintCounter() {
        printCounter += 1;
    }

}
