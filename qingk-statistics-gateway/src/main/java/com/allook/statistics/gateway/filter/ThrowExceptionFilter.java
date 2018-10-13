package com.allook.statistics.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @ClassName: ThrowExceptionFilter 
 * @Description: 在执行过滤器的逻辑时，如何捕捉异常
 * @author: pengyu
 * @date: 2018年9月18日 下午3:50:54
 */
@Component
public class ThrowExceptionFilter extends ZuulFilter  {

    private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }
    
    /**
     * post过滤器的SendErrorFilter会用来处理异常信息，但前提是请求上下文中要有"error.status_code"参数，
     * 这里的catch块中，就是将一些error的相关参数放入请求上下文
     * 这种方式仅用于可以捕捉的异常，对于意外抛出的异常还是会导致没有控制台输出也没有任何响应信息的情况出现。
     */
    @Override
    public Object run() {
        log.info("This is a pre filter, it will throw a RuntimeException");
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            doSomething();
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
            ctx.set("error.message", "自定义错误信息");
        }
        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }

}
