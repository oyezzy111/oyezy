package com.campus.handler;

import com.constant.MessageConstant;
import com.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理account重复异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        //
        String message = ex.getMessage();
        if(message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String account = split[2];
            String msg = account+ MessageConstant.ALREADY_EXIST;
            return Result.error(msg);
        }else {
            return Result.error( MessageConstant.UNKNOW_ERROR);
        }

    }
}
