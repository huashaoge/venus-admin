package com.venus.admin.exception;

import com.baomidou.mybatisplus.extension.api.R;
import com.venus.admin.common.constants.ErrorCode;
import com.venus.admin.common.model.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tcg
 * @Date: 2020/4/28 14:07
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class VenusGlobalExceptionHandler {

    /**
     * 处理AuthenticationException异常转换
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({AuthenticationException.class})
    public static ResultBody authenticationException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return ResultBody.fail().code(ErrorCode.UNAUTHORIZED.getCode()).msg("认证失败").httpStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler({InvalidGrantException.class})
    public static ResultBody invalidGrantException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.info(e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultBody.fail().msg("用户名或密码不正确").httpStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler({VenusAlertException.class})
    public static ResultBody venusAlertException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        return ResultBody.fail().code(ErrorCode.ALERT.getCode()).msg(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public static ResultBody methodArgumentNotValidException(Exception e, HttpServletRequest request, HttpServletResponse response){
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
        return ResultBody.fail().code(ErrorCode.ERROR.getCode()).msg(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler({Exception.class})
    public static ResultBody defaultException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultBody.fail().code(ErrorCode.ERROR.getCode()).msg("系统异常").httpStatus(HttpStatus.UNAUTHORIZED.value());
    }


}
