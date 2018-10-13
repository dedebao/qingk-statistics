package com.allook.statistics.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/** 
 * @ClassName: AccessFilter 
 * @Description: 在请求被路由之前检查HttpServletRequest中是否有accessToken
 * @author: pengyu
 * @date: 2018年9月18日 下午2:19:27  
 */
@Component
public class AccessFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

	/**
	 * 判断该过滤器是否需要被执行
	 */
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	//过滤器的具体逻辑
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        /*
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        */
        log.info("access token ok");
        return null;
		
	}

	/**
	 * 过滤器的类型，决定过滤器在请求的哪个生命周期中执行。
	 * pre:可以在请求被路由之前调用
	 * routing:在路由请求时被调用
	 * post:在routing和error过滤器之后被调用
	 * error:处理请求时发生错误是被调用
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

    /**
     * 过滤器的执行顺序，数值越小优先级越高
     */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
