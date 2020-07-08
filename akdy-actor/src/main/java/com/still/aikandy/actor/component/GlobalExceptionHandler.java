package com.still.aikandy.actor.component;


import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestException;
import com.still.aikandy.common.api.RestResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @Author Lee
 * @Description TODO
 * @Date 2020/6/21 17:10
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * post请求参数校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        //获取异常中随机一个异常信息
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            StringBuffer errMsgBuf = new StringBuffer();
            result.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError)error;
                errMsgBuf.append(fieldError.getField());
                errMsgBuf.append(" -> ");
                errMsgBuf.append(fieldError.getDefaultMessage() + "; ");
            });
            return new RestResponse(00001,"参数校验异常： " + errMsgBuf.toString());
        }
        return RestResponse.error(RestCode.UNKNOWN_ERROR);
    }

    /**
     * get请求参数校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public RestResponse bindExceptionHandler(BindException e){
        //获取异常中随机一个异常信息
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            StringBuffer errMsgBuf = new StringBuffer();
            result.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError)error;
                errMsgBuf.append(fieldError.getField());
                errMsgBuf.append(" -> ");
                errMsgBuf.append(fieldError.getDefaultMessage() + "; ");
            });
            return new RestResponse(00001,"参数校验异常： " + errMsgBuf.toString());
        }
        return RestResponse.error(RestCode.UNKNOWN_ERROR);
    }

    /**
     * 请求方法中校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResponse constraintViolationExceptionHandler(ConstraintViolationException e){
        //获取异常中第一个错误信息
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return new RestResponse(00000,message);
    }

    /**
     * 自定义异常处理器
     * @param restException
     * @return
     */
    @ExceptionHandler(RestException.class)
    public RestResponse restExceptionHandler(RestException restException){
        if(restException.getRestCode() != null){
            return RestResponse.error(restException.getRestCode());
        }
        return RestResponse.error(RestCode.UNKNOWN_ERROR);
    }

    /**
     * 未知异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public RestResponse unknownExceptionHandler(Exception e){
        return RestResponse.error(RestCode.UNKNOWN_ERROR);
    }
}
