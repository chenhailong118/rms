package com.still.rms.common.api;

public class ApiException extends RuntimeException {

  private static final long serialVersionUID = -3760708879601335491L;

  private ResultCode resultCode;

  public ApiException(ResultCode resultCode){
    super(resultCode.code + ":" + resultCode.msg);
    this.resultCode = resultCode;
  }

  public ApiException(String errorCode) {
    super(errorCode);
  }

  public ApiException(String errorCode, Throwable cause) {
    super(errorCode, cause);
  }

  public ApiException(String errorCode, String errorMsg) {
    super(errorCode + ":" + errorMsg);
  }

  public ApiException(String errorCode, String errorMsg, Throwable cause) {
    super(errorCode + ":" + errorMsg, cause);
  }

  public ApiException(Throwable cause) {
    super(cause);
  }

  public ResultCode getResultCode() {
    return resultCode;
  }

  public void setResultCode(ResultCode resultCode) {
    this.resultCode = resultCode;
  }
}
