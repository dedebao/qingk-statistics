package com.allook.statistics.gateway.processor;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 
 * @ClassName: MyFilterProcessor
 * @Description: 记录抛出的异常来自于哪个过滤器
 * @author: pengyu
 * @date: 2018年9月18日 下午4:50:47
 */
public class MyFilterProcessor extends FilterProcessor {

	@Override
	public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
		try {
			return super.processZuulFilter(filter);
		} catch (ZuulException e) {
			RequestContext ctx = RequestContext.getCurrentContext();

			// 请求上下文记录捕获的异常信息
			ctx.set("failed.exception", e);

			// 存储抛出异常的过滤器实例
			ctx.set("failed.filter", filter);
			throw e;
		}
	}

}
