/**
 * 
 */
package com.allook.statistics.core.exception;

/** 
 * @ClassName: ServiceException 
 * @Description: 自定义异常
 * @author: pengyu
 * @date: 2018年9月21日 上午10:36:58  
 */

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String message) {
        super(message);
    }

}
