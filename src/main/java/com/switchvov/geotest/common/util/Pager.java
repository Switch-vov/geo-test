package com.switchvov.geotest.common.util;

import java.util.List;

public class Pager<E> {
    private int currentPage = DEFAULT_CURRENT_PAGE;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int count;
    private int pageCount;
    private int actualPageSize;

    private List<E> data;
    public static final int DEFAULT_CURRENT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int DEFAULT_PAGE_SIZE_MIN = 10;

    public Pager() {
    }

    public Pager(int currrent, int size) {
        this.setCurrentPage(currrent);
        this.setPageSize(size);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage > 0 ? currentPage : DEFAULT_CURRENT_PAGE;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count > 0) {
            this.count = count;
            onCountChanged();
        }
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean getHasPrev() {
        return currentPage > 1;
    }

    public boolean getHasNext() {
        return currentPage < pageCount;
    }

    public int getOffset() {
        return (currentPage - 1) * pageSize;
    }

    public int getActualPageSize() {
        return actualPageSize;
    }

    protected void onCountChanged() {
        pageCount = count / pageSize;
        int m = count % pageSize;
        if (m > 0) {
            pageCount += 1;
        }
        // pageCount = (count + pageSize - 1) / pageSize;
        currentPage = (currentPage > pageCount ? pageCount : currentPage);
        int actual = count - ((currentPage - 1) * pageSize);
        actualPageSize = (actual > pageSize ? pageSize : actual);
    }

    public int getTopCount() {
        int topCount = currentPage * pageSize;
        if (currentPage > pageCount) {
            topCount = 0;
        }
        return topCount;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", count=" + count +
                ", pageCount=" + pageCount +
                ", actualPageSize=" + actualPageSize +
                ", data=" + data +
                '}';
    }
}
