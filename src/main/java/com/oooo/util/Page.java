package com.oooo.util;

import java.util.List;

/**
 * Created by chenpan on 17-1-3.
 */
public class Page<T>{
    private int startNum;
    private int endNum;
    private int pageSize;//页面大小
    private int pageNum; //当前页数
    private int prePageNum;//前一页
    private int nextPageNum;//后一页
    private int pageCount;//总页数
    private int count;//总记录数
    private List<T> result;

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum > count ? count:endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrePageNum() {
        return pageNum == 0?pageNum : pageNum-1;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getNextPageNum() {
        return pageNum+1 >= pageCount ? pageNum : pageNum+1;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
