package com.yjsh.constant;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T result;
    private boolean success;

    public boolean isSuccess(){
        return this.code == null ||this.code == 200;
    }

    public  Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /**
     * r转json的方法
     * @param r 待转化的对象
     * @return r对应的json字符串
     */
    public String toJsonString(Result r){
        return JSONObject.toJSONString(r);
    }

    /**
     * toString就是把自己变成json
     * @return 当前对象对应的json
     */
    @Override
    public String toString() {
        return toJsonString(this);
    }
}
