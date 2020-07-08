package com.still.aikandy.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RestResponse<T> {
  
  private long    code;
  private String message;
  private T      data;
  
  public static <T> RestResponse<T> success() {
    return new RestResponse<T>();
  }
  
  public static <T> RestResponse<T> success(T result) {
    RestResponse<T> response = new RestResponse<T>();
    response.setResult(result);
    return response;
  }
  
  public static <T> RestResponse<T> error(RestCode code) {
    return new RestResponse<T>(code.code,code.msg);
  }
  
  public RestResponse(){
    this(RestCode.OK.code, RestCode.OK.msg);
  }
  
  public RestResponse(long code, String message){
    this.code = code;
    this.message  = message;
  }
  
  public long getCode() {
    return code;
  }
  public void setCode(long code) {
    this.code = code;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String msg) {
    this.message = msg;
  }
  public T getData() {
    return data;
  }
  public void setResult(T data) {
    this.data = data;
  }
  
  @Override
  public String toString() {
    return "RestResponse [code=" + code + ", message=" + message + ", data=" + data + "]";
  }

}
