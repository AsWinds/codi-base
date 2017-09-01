package com.codi.base.spring.web;

import com.codi.base.common.Const;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.i18n.I18NMgr;
import com.codi.base.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 异常处理基类
 *
 * @author shi.pengyan
 * @version 1.0 2017-06-28 13:39
 * @since 1.0
 * <p>
 * Class used to handle global controller exceptions.
 * <p>
 * Spring MVC build Exceptions, in 3.2.17 Exception HTTP Status Code
 * BindException 400 (Bad Request) ConversionNotSupportedException 500 (Internal
 * Server Error) HttpMediaTypeNotAcceptableException 406 (Not Acceptable)
 * HttpMediaTypeNotSupportedException 415 (Unsupported Media Type)
 * HttpMessageNotReadableException 400 (Bad Request)
 * HttpMessageNotWritableException 500 (Internal Server Error)
 * HttpRequestMethodNotSupportedException 405 (Method Not Allowed)
 * MethodArgumentNotValidException 400 (Bad Request)
 * MissingServletRequestParameterException 400 (Bad Request)
 * MissingServletRequestPartException 400 (Bad Request)
 * NoSuchRequestHandlingMethodException 404 (Not Found) TypeMismatchException
 * 400 (Bad Request)
 */
@Slf4j
public abstract class AbstractExceptionHandler {

    @ExceptionHandler(value = BaseAppException.class)
    public ResponseEntity<?> handleBaseAppException(BaseAppException e) {
        String desc = e.getDesc();
        if (StringUtil.isEmpty(desc)) {
            desc = I18NMgr.getInstance().getValue(e.getCode());
        }
        BaseResult result = new BaseResult(false, desc);

        //or return ResponseEntity.ok().build();
        return ResponseEntity.ok(result);
    }


    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public ResponseEntity<?> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException be) {
        return new ResponseEntity<>(be.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException be) {
        return new ResponseEntity<>(be.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

//    @ExceptionHandler(value = NoSuchRequestHandlingMethodException.class)
//    @ResponseBody
//    public ResponseEntity<?> handleNoSuchRequestHandlingMethodException(NoSuchRequestHandlingMethodException be) {
//        return new ResponseEntity<>(be.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
//    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<BaseResult> handleBindException(BindException e) {

//        List<ObjectError> errors = e.getAllErrors();
//        Assert.notEmpty(errors);
//        ObjectError error = e.getAllErrors().get(0);

        BaseResult result = new BaseResult(false, null);
        result.setErrorCode("INVALID_PARAMETER");
        FieldError fieldError = e.getFieldError();
        result.setErrorMessage(String.format("%s,%s", fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(value = {
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class,
        MissingServletRequestParameterException.class,
        MissingServletRequestPartException.class,
        TypeMismatchException.class,
        ServletRequestBindingException.class})
    @ResponseBody
    public ResponseEntity<BaseResult> handleExceptions(Exception e) {
        return new ResponseEntity<>(buildBaseResult(Const.ERROR_INVALID_REQUEST, e.getMessage()),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
        ConversionNotSupportedException.class,
        HttpMessageNotWritableException.class,
        Exception.class})
    @ResponseBody
    public ResponseEntity<BaseResult> handleException(Exception e) {
        log.error("Unexpected exceptions!!!", e);

        return new ResponseEntity<>(buildBaseResult(Const.ERROR_SYS_EXCEPTION, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private BaseResult buildBaseResult(int errorCode, String errorMsg) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setErrorCode("" + errorCode);
        if (errorMsg == null) {
            String errorMessage = I18NMgr.getInstance().getValue("" + errorCode);
            result.setErrorMessage(errorMessage);
        } else {
            result.setErrorMessage(errorMsg);
        }
        return result;
    }
}
