package easyb.service.impl;

import java.util.List;

public class EasyProperties {
    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Page> getContent() {
        return content;
    }

    public void setContent(List<Page> content) {
        this.content = content;
    }

    private Pagination pagination;
    private List<Page> content;

    static class Pagination {
        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getNext_page() {
            return next_page;
        }

        public void setNext_page(String next_page) {
            this.next_page = next_page;
        }

        int limit;
        int page;
        int total;
        String next_page;

        public boolean thereAreMorePages() {
            // assuming next_page comes with string "true" or "false"
            return Boolean.parseBoolean(next_page);
        }
    }

    static class Page {

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getPublic_id() {
            return public_id;
        }

        public void setPublic_id(String public_id) {
            this.public_id = public_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_image_full() {
            return title_image_full;
        }

        public void setTitle_image_full(String title_image_full) {
            this.title_image_full = title_image_full;
        }

        public String getTitle_image_thumb() {
            return title_image_thumb;
        }

        public void setTitle_image_thumb(String title_image_thumb) {
            this.title_image_thumb = title_image_thumb;
        }

        private String agent;
        private String public_id;
        private String title;
        private String title_image_full;
        private String title_image_thumb;

    }
}