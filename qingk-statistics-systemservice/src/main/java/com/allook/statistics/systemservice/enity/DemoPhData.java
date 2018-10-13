/**
 * 
 */
package com.allook.statistics.systemservice.enity;

import java.io.Serializable;

import com.allook.statistics.core.model.Model;

/** 
 * @ClassName: DemoData 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月20日 上午11:24:53  
 */

public class DemoPhData implements Serializable,Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String s1;
	private String s2;
	private String s3;
	private String s4;
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public String getS3() {
		return s3;
	}
	public void setS3(String s3) {
		this.s3 = s3;
	}
	public String getS4() {
		return s4;
	}
	public void setS4(String s4) {
		this.s4 = s4;
	}
}
