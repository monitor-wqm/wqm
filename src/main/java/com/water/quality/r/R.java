package com.water.quality.r;

import com.water.quality.r.enums.ResponseEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class R {

	private Integer code;
	private String message;
	private Map<String, Object> data = new HashMap<>();


	private R(){

	}



	/**
	 * 返回成功结果
	 * @return
	 */
	public static com.water.quality.r.R ok(){
		com.water.quality.r.R r = new com.water.quality.r.R();
		r.setCode(ResponseEnum.SUCCESS.getCode());
		r.setMessage(ResponseEnum.SUCCESS.getMessage());
		return r;
	}

	/**
	 * 返回失败结果
	 * @return
	 */
	public static com.water.quality.r.R error(){
		com.water.quality.r.R r = new com.water.quality.r.R();
		r.setCode(ResponseEnum.ERROR.getCode());
		r.setMessage(ResponseEnum.ERROR.getMessage());
		return r;
	}

	public static com.water.quality.r.R error(Integer code, String message){
		com.water.quality.r.R r = new com.water.quality.r.R();
		r.setCode(code);
		r.setMessage(message);
		return r;
	}

	/**
	 * 设置特定的结果
	 * @param responseEnum
	 * @return
	 */
	public static com.water.quality.r.R setResult(ResponseEnum responseEnum){
		com.water.quality.r.R r = new com.water.quality.r.R();
		r.setCode(responseEnum.getCode());
		r.setMessage(responseEnum.getMessage());
		return r;
	}

	public com.water.quality.r.R data(String key, Object value){
		this.put(key, value);
		return this;
	}

	/**
	 * 设置特定的响应消息
	 * @param message
	 * @return
	 */
	public com.water.quality.r.R message(String message){
		this.setMessage(message);
		return this;
	}

	public com.water.quality.r.R data(Map<String, Object> map){
		this.setData(map);
		return this;
	}

	/**
	 * 设置特定的响应码
	 * @param code
	 * @return
	 */
	public com.water.quality.r.R code(Integer code){
		this.setCode(code);
		return this;
	}

	public com.water.quality.r.R put(String key, Object value) {
		data.put(key, value);
		return this;
	}

	public com.water.quality.r.R setData(Object data) {
		put("data",data);
		return this;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
