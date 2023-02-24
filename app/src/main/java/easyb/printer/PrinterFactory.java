package easyb.printer;

import easyb.printer.impl.SimpleTitlePagePrinter;

public class PrinterFactory {

    public static TitlePrinter getPrinter() {
        return new SimpleTitlePagePrinter();
    }

}
