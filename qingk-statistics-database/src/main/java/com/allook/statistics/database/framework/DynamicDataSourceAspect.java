/**
 * 
 */
package com.allook.statistics.database.framework;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DynamicDataSourceAspect
 * @Description: 数据源选择切面
 * @author: pengyu
 * @date: 2018年10月8日 下午3:56:28
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	@Before("@annotation(TargetDatasource)")
	public void beforeSwitchDS(JoinPoint point) {

		// 获得当前访问的class
		Class<?> className = point.getTarget().getClass();

		// 获得访问的方法名
		String methodName = point.getSignature().getName();

		// 数据源默认选择为mysql
		DatasourceType dataSource = DatasourceType.mysqldb;
		try {

			// 得到方法的参数的类型
			MethodSignature methodSignature = (MethodSignature) point.getSignature();
			Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();

			// 得到访问的方法对象
			Method method = className.getMethod(methodName, parameterTypes);

			// 判断是否存在@TargetDatasource注解
			if (method.isAnnotationPresent(TargetDatasource.class)) {

				TargetDatasource annotation = method.getAnnotation(TargetDatasource.class);
				// 取出注解中的数据源名
				dataSource = annotation.value();
			}
		} catch (Exception e) {
			logger.error("切面切换数据源出现异常:", e);
		}

		// 切换数据源
		DatabaseContextHolder.setDB(dataSource);
	}

}
