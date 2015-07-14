package com.md.demo.server.bean.vo;

/**
 * 对外返回值统一封转，由此转为json
 * ResultMsg 
 * @author chenchao
 * @date Jul 14, 2015 2:26:47 PM
 *
 */
public class ResultMsg<T> {

	/**
	 * 对外返回的对象
	 */
	private T data;

	/**
	 * 返回状态码
	 */
	private int code;

	/**
	 * 返回消息
	 */
	private String msg;

	public ResultMsg() {
		super();
	}

	public ResultMsg(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResultMsg(T data, int code, String msg) {
		super();
		this.data = data;
		this.code = code;
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	/**
	 * 服务器unix utc时间戳秒值
	 */
	public long getTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

}
