package org.alvin.cishan.common.aop;

import com.google.common.cache.Cache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
/**
 * @功能描述 扫描防止重复提交注解的切面
 * @author www.gaozz.club
 * @date 2018-08-26
 */
public class NoRepeatSubmitAop {

	private Log logger = LogFactory.getLog(getClass());

	@Autowired
	private Cache<String, Integer> cache;

	@Around("execution(* org.alvin.cishan.sys..*Controller.*(..)) && @annotation(nrs)")
	public Object arround(ProceedingJoinPoint pjp, NoRepeatSubmit nrs) {
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
			HttpServletRequest request = attributes.getRequest();
			String key = sessionId + "-" + request.getServletPath();
			if (cache.getIfPresent(key) == null) {
				Object o = pjp.proceed();
				cache.put(key, 0);
				return o;
			} else {
				logger.error("重复提交");
				return null;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("验证重复提交时出现未知异常!");
			return "{\"code\":-889,\"message\":\"验证重复提交时出现未知异常!\"}";
		}

	}

}
