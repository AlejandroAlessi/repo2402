package easyb.service.vo;

import java.util.List;

public class TitlePage {

    private List<String> titles;
    private int currPage;
    private boolean thereArePendingPages;
    private boolean pageNotProcessed = false;

    public TitlePage(List<String> titles, int currPage, boolean thereArePendingPages) {
        this.currPage = currPage;
        this.thereArePendingPages = thereArePendingPages;

        if (titles != null){
            this.titles = List.copyOf(titles);
        }
    }
    
    public TitlePage() {
    }

    public List<String> getTitles() {
        return titles;
    }

    public int getCurrPage() {
        return currPage;
    }

    public boolean thereArePendingPages() {
        return thereArePendingPages;
    }

    public void setPageNonProcessed(boolean b) {
        this.pageNotProcessed = b;
    }

    public boolean getPageNotProcessed(){
        return this.pageNotProcessed;
    }

 }
