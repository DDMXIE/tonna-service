package com.tony.tonna.util;

public class Result <T> {
    private static final int CODE_SUCCESS = 200;

    private static final int CODE_FAIL = 500;

    private static final String MSG_SUCCESS="success";
    private static final String MSG_FAIL="failed";

    private int meta;
    private T data;
    private String msg;

    public Result(int meta) {
        this.meta = meta;
    }

    public Result(int meta, String msg) {
        this.meta = meta;
        this.msg = msg;
    }

    public Result(int meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public Result(int meta, String msg, T data) {
        this.meta = meta;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(int i, String s){
        return new Result(CODE_SUCCESS,MSG_SUCCESS);
    }

    public static Result success(int i, Object data){
        return new Result(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static Result fail(){
        return new Result(CODE_FAIL, MSG_FAIL);
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
