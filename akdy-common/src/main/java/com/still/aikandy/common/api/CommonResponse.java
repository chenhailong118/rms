package com.still.aikandy.common.api;

/**
 * 通用返回对象
 * @param <T>
 */
public class CommonResponse<T> {
  
  private long    code;
  private String message;
  private T      data;

  private CommonResponse(){
    this(ResultCode.OK.code, ResultCode.OK.msg);
  }

  private CommonResponse(long code, String message){
    this.code = code;
    this.message  = message;
  }

  private CommonResponse(long code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  /**
   * 成功 无返回结果
   * @param <T>
   * @return
   */
  public static <T> CommonResponse<T> success() {
    return new CommonResponse<T>();
  }

  /**
   * 成功 有返回结果
   * @param result
   * @param <T>
   * @return
   */
  public static <T> CommonResponse<T> success(T result) {
    CommonResponse<T> response = new CommonResponse<T>();
    response.setResult(result);
    return response;
  }

  /**
   * 失败
   * @param code
   * @param <T>
   * @return
   */
  public static <T> CommonResponse<T> error(ResultCode code) {
    return new CommonResponse<T>(code.code,code.msg);
  }

  /**
   * 失败
   * @param code
   * @param msg
   * @param <T>
   * @return
   */
  public static  <T> CommonResponse<T> error(long code, String msg) {
    return new CommonResponse<T>(code,msg);
  }

  /**
   * 失败
   * @param msg
   * @return
   */
  public static  <T> CommonResponse<T> error(String msg) {
    return new CommonResponse<T>(ResultCode.UNKNOWN_ERROR.code,msg);
  }

  /**
   * 未登录返回结果
   */
  public static <T> CommonResponse<T> unauthorized(String msg) {
    return new CommonResponse<T>(ResultCode.UNAUTHORIZED.code, msg);
  }

  /**
   * 未登录返回结果
   */
  public static <T> CommonResponse<T> unauthorized() {
    return new CommonResponse<T>(ResultCode.UNAUTHORIZED.code, ResultCode.UNAUTHORIZED.msg);
  }

  /**
   * 未授权返回结果
   */
  public static <T> CommonResponse<T> forbidden(T data) {
    return new CommonResponse<T>(ResultCode.FORBIDDEN.code, ResultCode.FORBIDDEN.msg, data);
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
    return "CommonResponse [code=" + code + ", message=" + message + ", data=" + data + "]";
  }

}
