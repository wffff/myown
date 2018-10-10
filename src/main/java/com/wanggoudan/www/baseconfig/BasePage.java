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

    private Integer offset;
    private Integer limit;
    private Sort sorts;
    private Sort.Direction sortType;
    private String sort;
    private String sortOrder;
    private PageRequest requestPage;

    public BasePage() {
        this.offset = WebConstants.DEF_OFFSET;
        this.limit = WebConstants.DEF_SIZE;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Sort getSorts() {
        return sorts;
    }

    public void setSorts(Sort sorts) {
        this.sorts = sorts;
    }

    public Sort.Direction getSortType() {
        return sortType;
    }

    public void setSortType(Sort.Direction sortType) {
        this.sortType = sortType;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public PageRequest getRequestPage() {
        if (!RegexUtils.notNull(this.sort)) this.sort = "id";
        if (this.sortOrder.equals("desc")) {
            this.sortType = Sort.Direction.DESC;
        }else if (this.sortOrder.equals("asc")){
            this.sortType = Sort.Direction.ASC;
        }else {
            this.sortType = Sort.Direction.DESC;
        }
        this.sorts = Sort.by(this.sortType, StringUtils.underscoreName(this.sort).toLowerCase());
        return PageRequest.of(this.offset/this.limit, this.limit, this.sorts);
    }

    public void setRequestPage(PageRequest requestPage) {
        this.requestPage = requestPage;
    }

}