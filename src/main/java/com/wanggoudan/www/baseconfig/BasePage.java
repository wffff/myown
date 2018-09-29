package com.wanggoudan.www.baseconfig;

import com.wanggoudan.www.baseconfig.constant.WebConstants;
import com.wanggoudan.www.baseconfig.util.RegexUtils;
import com.wanggoudan.www.baseconfig.util.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by Adam Yao on 2017/12/14.
 */
public class BasePage implements Serializable {

    private Integer page;
    private Integer limit;
    private Sort sort;
    private Sort.Direction sortType;
    private String sortProperty;
    private PageRequest requestPage;

    public BasePage() {
        this.page = WebConstants.DEF_PAGE;
        this.limit = WebConstants.DEF_SIZE;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Sort.Direction getSortType() {
        return sortType;
    }

    public void setSortType(Sort.Direction sortType) {
        this.sortType = sortType;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public PageRequest getRequestPage() {
        if (!RegexUtils.notNull(this.sortProperty)) this.sortProperty = "id";
        if (this.sortType == null) this.sortType = Sort.Direction.DESC;
        this.sort = Sort.by(this.sortType, StringUtils.underscoreName(this.sortProperty).toLowerCase());
        return PageRequest.of(this.page - 1, this.limit, this.sort);
    }

    public void setRequestPage(PageRequest requestPage) {
        this.requestPage = requestPage;
    }

    @Override
    public String toString() {
        return "BasePageImpl{" +
                "page=" + page +
                ", limit=" + limit +
                ", sort=" + sort +
                ", requestPage=" + requestPage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasePage basePage = (BasePage) o;

        if (page != basePage.page) return false;
        if (limit != basePage.limit) return false;
        return sort != null ? sort.equals(basePage.sort) : basePage.sort == null;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + limit;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }
}