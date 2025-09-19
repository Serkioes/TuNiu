package com.tjetc.vo;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;
    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }
    public static Result ok(){
        return new Result(200,"操作成功",null);
    }
    public static Result ok(int code,String msg){
        return new Result(code,msg,null);
    }
    public static Result ok(Object data){
        return new Result(200,"操作成功",data);
    }
    public static Result error(){
        return new Result(500,"操作失败",null);
    }
    public static Result error(int code,String msg){
        return new Result(code,msg,null);
    }
}
