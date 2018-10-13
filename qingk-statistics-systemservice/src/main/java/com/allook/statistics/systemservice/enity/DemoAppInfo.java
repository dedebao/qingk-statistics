package com.allook.statistics.systemservice.enity;

import java.io.Serializable;

import com.allook.statistics.core.model.Model;

/**
 * 
 * @ClassName: AppInfo
 * @Description:app信息实体
 * @author: pengyu
 * @date: 2018年8月2日 上午10:43:09
 */
public class DemoAppInfo implements Serializable,Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4393235225170700285L;

	private String app_information_key;
	private String url;

	public String getApp_information_key() {
		return app_information_key;
	}

	public void setApp_information_key(String app_information_key) {
		this.app_information_key = app_information_key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
