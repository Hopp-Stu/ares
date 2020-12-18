package com.ares.system.common.exception;


import com.ares.core.common.exception.UserException;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.base.ResultCode;
import com.ares.core.model.exception.ErrorCode;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: yy 2020/05/09
 **/
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class SysGlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public Object handleNotValidException(HttpServletRequest request, HttpServletResponse response, BindException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return BaseResult.result(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMsg(), error.getDefaultMessage());
    }

    @ExceptionHandler(value = UserException.class)
    public Object handleUserException(HttpServletRequest request, HttpServletResponse response, UserException e) {
        String code = e.getCode();
        if (code.equals(ErrorCode.NOUSER.getCode())) {
            return BaseResult.error(ResultCode.FAILED.getCode(), "用户不存在");
        } else if (code.equals(ErrorCode.NOAUTH.getCode())) {
            return BaseResult.unAuth();
        }
        return BaseResult.unLogin();
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public Object handleNumberFormatException(HttpServletRequest request, HttpServletResponse response, NumberFormatException e) {
        return BaseResult.error(ResultCode.FAILED.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = PersistenceException.class)
    public Object handlePersistenceException(HttpServletRequest request, HttpServletResponse response, PersistenceException e) {
        return BaseResult.error(ResultCode.FAILED.getCode(), e.getMessage());
    }

}
