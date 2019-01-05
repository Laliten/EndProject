package com.aigrow.model.dto;

/**
 * 用于分页控制
 * @author YangDeJian
 */
public class Page {
    private int pageSize = 10;
    private int currentPage = 1;
    private long totalPages = 1;
    private int nextPage = 1;
    private long totalRecordSize = 10;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public long getTotalRecordSize() {
        return totalRecordSize;
    }

    public void setTotalRecordSize(long totalRecordSize) {
        this.totalRecordSize = totalRecordSize;
    }
}
