package com.yjsh.constant;


import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 封装rest api 的返回结果类
 * 这个类要支持链式编程
 */
public class R<T> implements Serializable {
    private Integer code;
    private String msg;
    private T result;

    private static final int OK_200=200;
    private static final int OK_201=200;
    private static final int OK_202=200;
    private static final int E_500=500;
    private static final int E_501=501;
    private static final int E_502=502;
    private static final int E_503=503;
    private static final int E_504=504;
    private static final int E_505=505;


    public boolean isSuccess(){
        return this.code == null ||this.code == OK_200;
    }


    public int getCode() {
        return code;
    }

    public Object getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }


    /**
     * 对set方法进行链式编程改造
     * @param code
     */

    public R setCode(int code) {
        this.code = code;
        return this;
    }
    public R setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public R setResult(T result) {
        this.result = result;
        return this;
    }


    /**
     * 调用成功的方法
     * @return
     */
    public static R ok(){
        R r=new R().setCode(200).setMsg("请求成功");
        return r;
    }

    public static R ok(int code){
        R r=new R().setCode(code).setMsg("请求成功");
        return r;
    }

    public static R ok(String msg){
        R r=new R().setCode(200).setMsg(msg);
        return r;
    }

    public static R ok(int code, String msg){
        R r=new R().setCode(code).setMsg(msg);
        return r;
    }
    public static R ok(Object data){
        return ok().setResult(data);
    }


    /**
     * 调用失败的方法
     */
    public static R error(){
        R r=new R();
        r.code=500;
        r.msg="请求失败";
        return r;
    }

    public static R error(int code){
        R r=new R();
        r.code=code;
        r.msg="请求失败";
        return r;
    }
    public static R error(String msg){
        R r=new R();
        r.code=500;
        r.msg=msg;
        return r;
    }
    public static R error(int code , String msg){
        R r=new R();
        r.code=code;
        r.msg=msg;;
        return r;
    }
    public static R error(Object data){
        R r=new R();
        r.code=500;
        r.msg="请求失败";
        r.result=data;
        return r;
    }
    public static R errorLogin(){
        R r=new R();
        r.code=500;
        r.msg="身份认证失效，请重新登陆";
        return r;
    }

    public static R errorPower(){
        R r=new R();
        r.code=510;
        r.msg="权限不足";
        return r;
    }

    /**
     * r转json的方法
     * @param r 待转化的对象
     * @return r对应的json字符串
     */
    public String toJsonString(R r){
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
