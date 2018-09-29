package com.snkj.www.enums;

/**
 * Created by Danny on 2018/7/13.
 */
public enum OrganizationTypeEnum {
    COMPANY(0, "公司"),
    SHOP(1, "车间"),
    DEVICE(2,"设备");

    public Number code;
    public String des;

    OrganizationTypeEnum(Number code, String des) {
        this.code = code;
        this.des = des;
    }

    public static OrganizationTypeEnum get(Integer code) {

        for (OrganizationTypeEnum c : OrganizationTypeEnum.values()) {
            if (c.code.toString().equals(code.toString())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "code:" + code + ", des:" + des;
    }

}
