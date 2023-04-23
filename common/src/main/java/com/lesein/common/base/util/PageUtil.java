package com.lesein.common.base.util;

import com.lesein.common.base.request.BaseRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public class PageUtil<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Integer pageSizeInit = 20;

    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int currPage;
    /**
     * 列表数据
     */
    private List<T> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtil(List<T> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }


    /**
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param request    BaseRequest
     */
    public PageUtil(List<T> list, int totalCount, BaseRequest request) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = request.getPageSize();
        this.currPage = request.getPage();
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }


    /**
     * 默认构造器
     */
    public PageUtil() {
        this.pageSize = pageSizeInit;
        this.currPage = 1;
    }

    /**
     * 自定义构造器
     */
    public PageUtil(int pageSize, int currPage) {
        this.pageSize = pageSize;
        this.currPage = currPage;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
