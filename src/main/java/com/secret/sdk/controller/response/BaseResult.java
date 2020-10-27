package com.secret.sdk.controller.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class BaseResult {

	public static final transient String LIST ="list";
	public static final transient String RECORDS ="records";

	private Object data;

	private String msg;

	private boolean success;

	private String code;

	public static BaseResult success() {

		return success(null);
	}

	public static BaseResult success(Object object) {

		BaseResult baseResult = new BaseResult();
		baseResult.setSuccess(true);

		if (object == null) {
			object = Collections.EMPTY_MAP;
		}
		if (object instanceof Collection) {
			Map<String, Object> map = new HashMap<>(1);
			map.put(LIST, object);
			object = map;
		}
		baseResult.setData(object);
		return baseResult;
	}

	public static BaseResult error(String code, String msg) {

		BaseResult baseResult = new BaseResult();
		baseResult.setSuccess(false);
		baseResult.setCode(code);
		baseResult.setMsg(StringUtils.isEmpty(msg) ? "系统异常" : msg);
		return baseResult;
	}


	@JsonIgnore
	@JSONField(serialize = false)
	public boolean isSuccessAndDataNotNull() {
		return success && data != null;
	}
}