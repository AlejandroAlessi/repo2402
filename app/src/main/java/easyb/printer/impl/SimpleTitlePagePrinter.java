package easyb.printer.impl;

import easyb.printer.TitlePrinter;
import easyb.service.vo.TitlePage;

public class SimpleTitlePagePrinter implements TitlePrinter{

    @Override
    public void printTitlePage(TitlePage titlePage) {
        if (titlePage == null){
            // do nothing
            return;
        }

        for (String title:titlePage.getTitles()){
            printSingleTitle(title);
        }
    }

    protected void printSingleTitle(String title){
        System.out.println(title);
    }
}
