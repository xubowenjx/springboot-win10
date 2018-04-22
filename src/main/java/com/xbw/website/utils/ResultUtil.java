package com.xbw.website.utils;

public class ResultUtil {
	private Integer code;
	private String msg;
	private Object data;

	public ResultUtil() {

	}

	public ResultUtil(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ResultUtil ok(Object data) {
		return new ResultUtil(200, "success", data);
	}

	public static ResultUtil error(String msg) {
		return new ResultUtil(500, msg, null);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
