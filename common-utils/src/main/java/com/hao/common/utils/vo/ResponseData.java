package com.hao.common.utils.vo;



public class ResponseData<T> {

    /**
     * 返回码
     */
    private Integer code;
    public static final  Integer SUCCESS_CODE =200;
    public static final  Integer ERROR_CODE =500;
    /**
     * 返回描述
     */
    private String message;
    private Boolean status =true;
    private T data;

    public ResponseData() {
    }

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static  ResponseData ok(){
        ResponseData<Object> success = new ResponseData<>();
        success.setCode(ResponseData.SUCCESS_CODE);
        success.setStatus(true);
        return  success;
    }

    public static  ResponseData fail(){
        ResponseData<Object> success = new ResponseData<>();
        success.setCode(ResponseData.ERROR_CODE);
        success.setStatus(false);
        return  success;
    }
    public  ResponseData message(String message) {
        this.message = message;
        return  this;
    }
    public  ResponseData data(T data) {
        this.data = data;
        return  this;
    }



    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
