package com.still.aikandy.auth.component;


import com.still.aikandy.common.api.ApiException;
import com.still.aikandy.common.api.CommonResponse;
import com.still.aikandy.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


/**
 * @Author FishAndFlower
 * @Description 全局统一异常处理类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * post请求参数校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
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
            return CommonResponse.error(ResultCode.ILLEGAL_PARAMS.code,errMsgBuf.toString());
        }
        return CommonResponse.error(ResultCode.UNKNOWN_ERROR);
    }

    /**
     * get请求参数校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public CommonResponse bindExceptionHandler(BindException e){
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
            return CommonResponse.error(ResultCode.ILLEGAL_PARAMS.code,errMsgBuf.toString());
        }
        return CommonResponse.error(ResultCode.UNKNOWN_ERROR);
    }

    /**
     * 请求方法中校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResponse constraintViolationExceptionHandler(ConstraintViolationException e){
        //获取异常中第一个错误信息
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return CommonResponse.error(message);
    }


    /**
     * 自定义异常处理器
     * @param ApiException
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public CommonResponse ApiExceptionHandler(ApiException ApiException){
        if(ApiException.getResultCode() != null){
            return CommonResponse.error(ApiException.getResultCode());
        }
        return CommonResponse.error(ResultCode.UNKNOWN_ERROR);
    }

    /**
     * 未知异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResponse unknownExceptionHandler(Exception e){
        e.printStackTrace();
        return CommonResponse.error(e.getMessage());
    }
}
