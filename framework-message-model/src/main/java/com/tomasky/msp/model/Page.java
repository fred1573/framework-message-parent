package com.tomasky.msp.model;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {


    private static final long serialVersionUID = 1L;

    private int pageIndex = 1;

    private int pageSize = 15;


    private long totalCount = 0;

    private int totalPage = 1;

    private List<T> datas;

    public Page() {
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public void calcTotalPage() {
        if (totalCount <= pageSize) {
            this.totalPage = 1;
        }else {
            if ((totalCount % pageSize == 0)) {
                this.totalPage = (int) (totalCount / pageSize);
            }else {
                this.totalPage = (int) (totalCount / pageSize + 1);
            }
        }
    }

/*
    @org.springframework.data.annotation.Transient
    public Integer start() {
        if (getPageIndex() <= 0) {
            this.pageIndex = 1;
        } else if (getPageIndex() > getTotals()) {
            this.pageIndex = Integer.parseInt(getTotals().toString());
        }
        if (pageIndex == 0) return 0;
        return (getPageIndex() - 1) * getPageSize();
    }
*/

}
