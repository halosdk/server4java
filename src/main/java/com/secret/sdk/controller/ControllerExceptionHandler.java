package com.secret.sdk.controller;

import com.secret.sdk.controller.response.BaseResult;
import com.secret.sdk.utils.LxtxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public BaseResult processException(Exception e) {
		log.error("服务调用异常", e);
		if (e instanceof MethodArgumentNotValidException) {
			List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
			if (!CollectionUtils.isEmpty(errors)) {

				String error = errors.get(0).getDefaultMessage();
				String localeError;
				try{
					//没有对应的key
					localeError = "参数缺失";
				}catch (Exception ex){
					localeError = error;
				}
				return BaseResult.error("500", localeError);
			}
			// 返回自定义异常
		} else if (e instanceof LxtxException) {
			LxtxException lxtxException = (LxtxException) e;
			return BaseResult.error(lxtxException.getCode(), e.getMessage());
		} else if (e instanceof HttpRequestMethodNotSupportedException) {
			return BaseResult.error("500",e.getMessage());
		} else if (e instanceof HttpMessageNotReadableException) {
			return BaseResult.error("500", e.getMessage());
		} else if(e instanceof MissingServletRequestPartException){
			return BaseResult.error("500", e.getMessage());
		}
		return BaseResult.error("500", "系统异常");
	}





}
