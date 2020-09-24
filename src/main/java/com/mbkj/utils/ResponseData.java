package com.mbkj.utils;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {

    private int status;
    private String msg;
    private T data;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功返回的状态码
     */
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }



    private ResponseData() {
    }

    private ResponseData(int status) {
        this.status = status;
    }


    private ResponseData(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }


    private ResponseData(int status, T data) {
        this.status = status;
        this.data = data;
    }


    private ResponseData(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }



    public static <T> ResponseData<T> success() {

        return new ResponseData<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResponseData<T> success(T data) {

        return new ResponseData<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ResponseData<T> success(String msg) {

        return new ResponseData<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ResponseData<T> success(String msg,T data) {

        return new ResponseData<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
