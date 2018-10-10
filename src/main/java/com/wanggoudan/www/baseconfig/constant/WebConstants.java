package com.wanggoudan.www.baseconfig.constant;

/**
 * Created by Adam.yao on 2017/10/25.
 */
public class WebConstants {
    public static final String INDEX_PAGE = "index";
    public static final String FORM_PAGE = "fragments/form";
    public static final String MAIN_PAGE = "main";
    public static final Integer DEF_OFFSET = 0;
    public static final Integer DEF_SIZE = 20;

    public static Integer validateOffset(Integer offset) {
        return offset == null || offset <= 0 ?DEF_OFFSET:offset;
    }
    public static Integer validateSize(Integer size) {
        return size == null || size <= 0 ?DEF_SIZE:size;
    }
}
