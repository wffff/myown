package com.wanggoudan.www.baseconfig;


import com.wanggoudan.www.entity.CodeEntity;

import java.io.Serializable;

/**
 * 统一返回数据封装
 *
 * @param <T>
 */
public class ReturnMessage<T> implements Serializable {

    /**
     * code -1表示失败，大于等于0表示成功
     */
    private int code;
    /**
     * msg 文本描述信息
     */
    private String msg;

    /**
     * 分页总记录数
     */
    private int total;
    /**
     * data 返回数据信息
     */
    private T rows;

    public ReturnMessage(int code, String msg, T rows) {
        this.code = code;
        this.msg = msg;
        this.rows = rows;
    }

    public ReturnMessage(int code, String msg, int total, T rows) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.rows = rows;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public static <V> ReturnMessage<V> message(int code, String msg, V rows) {
        return new ReturnMessage<>(code, msg, rows);
    }

    public static <V> ReturnMessage<V> message(int code, String msg, int total, V rows) {
        return new ReturnMessage<>(code, msg, total, rows);
    }

    public static <V> ReturnMessage<V> success(String msg, V rows) {
        return new ReturnMessage<>(0, msg, rows);
    }

    public static <V> ReturnMessage<V> success(int total, V rows) {
        return new ReturnMessage<>(0, "", total, rows);
    }
    public static <V> ReturnMessage<V> success(int total, V rows, String msg) {
        return new ReturnMessage<>(0, msg, total, rows);
    }

    public static ReturnMessage success(String msg) {
        return new ReturnMessage<>(0, msg, null);
    }

    public static <V> ReturnMessage<V> success() {
        return new ReturnMessage<>(0, "", null);
    }

    public static <V> ReturnMessage<V> failed(String msg, V rows) {
        return new ReturnMessage<>(-1, msg, rows);
    }

    public static <V> ReturnMessage<V> failed(V rows) {
        return new ReturnMessage<>(-1, "", rows);
    }

    public static ReturnMessage failed(String msg) {
        return new ReturnMessage<>(-1, msg, null);
    }

    public static <V> ReturnMessage<V> failed() {
        return new ReturnMessage<>(-1, "", null);
    }
}