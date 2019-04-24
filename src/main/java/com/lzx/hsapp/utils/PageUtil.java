package com.lzx.hsapp.utils;

import java.io.Serializable;

public class PageUtil implements Serializable {
    private static final long serialVersionUID = -7224320603198937727L;

    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页数
     */
    private Long totalPages;
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 页记录数
     */
    private Integer pageSize;

    public PageUtil(Long total) {
        this.total = total;
    }

    public PageUtil(Long total, Integer pageNum, Integer pageSize) {
        this.total = total;
        this.totalPages = (total - 1) / pageSize + 1;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static PageUtil page(Long total) {
        return new PageUtil(total);
    }

    public static PageUtil page( Long total, Integer pageNo, Integer pageSize) {
        return new PageUtil(total, pageNo, pageSize);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static PageUtil defaultPage(Long total,Integer pageNum,Integer pageSize){
        total = total == null ? (long)0 : total;
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        return new PageUtil(total,pageNum,pageSize);
    }

    public static PageUtil defaultPage(Integer pageNum,Integer pageSize){
        return defaultPage((long)0,pageNum,pageSize);
    }
}
