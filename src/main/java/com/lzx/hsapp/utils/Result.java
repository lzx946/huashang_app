package com.lzx.hsapp.utils;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class Result<T> {

    private final static String SUCCESS_CODE = "ACK";

    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private T data;

    /**
     * 分页信息
     */
    private PageUtil page;

    /**
     * 其他内容
     */
    private Map<String, Object> ext;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public PageUtil getPage() {
        return page;
    }

    public void setPage(PageUtil page) {
        this.page = page;
    }

    public Result(){
        this.code = SUCCESS_CODE;
        this.message = "SUCCESS";
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(String code, String message, T data, Map<String, Object> ext) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.ext = ext;
    }

    public Result(String code, String message, T data, PageUtil pageInfo) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.page = pageInfo;
    }

    public Result(String code, String message, T data, Map<String, Object> ext, PageUtil pageInfo) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.ext = ext;
        this.page = pageInfo;
    }

    public Result(String code, String message, T data, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        this.code = code;
        this.message = message;
        this.data = data;
        this.page = pageInfo;
    }

    public Result(String code, String message, T data, Map<String, Object> ext, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        this.code = code;
        this.message = message;
        this.data = data;
        this.ext = ext;
        this.page = pageInfo;
    }

    //快速返回成功
    public static <T>Result success(){
        return new Result<T>(SUCCESS_CODE,"请求成功",null);
    }

    public static <T>Result success(T result){
        return new Result<T>(SUCCESS_CODE,"请求成功",result);
    }

    public static <T>Result success(String message, T result){
        return new Result<T>(SUCCESS_CODE,message,result);
    }

    public static <T>Result success(String message, T result, Map<String, Object> extra){
        return new Result<T>(SUCCESS_CODE,message,result, extra);
    }

    public static <T>Result success(T result, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        return new Result<T>(SUCCESS_CODE,"请求成功",result, pageInfo);
    }

    public static <T>Result success(T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        return new Result<T>(SUCCESS_CODE,"请求成功",result, extra,pageInfo);
    }

    public static <T>Result success(String message, T result, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        return new Result<T>(SUCCESS_CODE,message,result,pageInfo);
    }

    public static <T>Result success(String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        return new Result<T>(SUCCESS_CODE,message,result, extra,pageInfo);
    }

    //快速返回失败状态
    public static <T>Result fail(){
        return new Result<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage());
    }

    public static <T>Result fail(T result){
        return new Result<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage(),result);
    }

    public <T>Result fail(String message, T result){
        return new Result<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,result);
    }

    public <T>Result fail(String message, T result, Map<String, Object> extra){
        return new Result<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,result, extra);
    }

    public static <T>Result fail(ErrorCode errorCode){
        return new Result<T>(errorCode.getCode(),errorCode.getMessage());
    }

    public static <T>Result fail(ErrorCode errorCode, T result){
        return new Result<T>(errorCode.getCode(),errorCode.getMessage(),result);
    }

    public static <T>Result fail(ErrorCode errorCode, String message, T result){
        return new Result<T>(errorCode.getCode(),message,result);
    }

    public static <T>Result fail(ErrorCode errorCode, String message, T result, Map<String, Object> extra){
        return new Result<T>(errorCode.getCode(),message,result, extra);
    }

    //快速返回自定义状态码
    public static <T>Result result(String statusCode, String message){
        return new Result<T>(statusCode,message);
    }

    public static <T>Result result(String statusCode, String message, T result){
        return new Result<T>(statusCode,message,result);
    }

    public static <T>Result result(String statusCode, String message, T result, Map<String, Object> extra){
        return new Result<T>(statusCode,message,result, extra);
    }

    public static <T>Result result(String statusCode, String message, T result, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        return new Result<T>(statusCode,message,result, pageInfo);
    }

    public static <T>Result result(String statusCode, String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        return new Result<T>(statusCode,message,result, extra,pageInfo);
    }

    //快速返回Http状态
    public static <T>Result httpStatus(HttpStatus httpStatus, String message){
        return result(httpStatus.toString(),message);
    }

    public static <T>Result httpStatus(HttpStatus httpStatus, String message, T result){
        return result(httpStatus.toString(),message,result);
    }

    public static <T>Result httpStatus(HttpStatus httpStatus, String message, T result, Map<String, Object> extra){
        return result(httpStatus.toString(),message,result, extra);
    }

    public static <T>Result httpStatus(HttpStatus httpStatus, String message, T result, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        return result(httpStatus.toString(),message,result, total, pageNo, pageSize);
    }

    public static <T>Result httpStatus(HttpStatus httpStatus, String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageUtil pageInfo = new PageUtil(total, pageNo, pageSize);
        return result(httpStatus.toString(),message,result, extra, total, pageNo, pageSize);
    }
}
