package org.alvin.cishan.sys.util;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

/**
 * fallback 生成工具 tangzc
 */
public class FallbackUtil {

	/**
	 * 生成整个项目的fallback
	 *
	 * @param applicationContext
	 * @param feignClient
	 */
	public static void genFallBack(ApplicationContext applicationContext, Class<? extends Annotation> feignClient, String configPackage) throws ClassNotFoundException {
		String fileSeparator = File.separator;
		String userPath = System.getProperty("user.dir").concat(fileSeparator);
		userPath = userPath.concat("src").concat(fileSeparator).concat("main").concat(fileSeparator).concat("java").concat(fileSeparator);
		//循环所有的feignclient 生成 fallback
		Map<String, Object> clients = applicationContext.getBeansWithAnnotation(feignClient);
		List<Class<?>> proxyClass = Lists.newArrayList();
		for (Map.Entry<String, Object> entry : clients.entrySet()) {
			Class<?> c = Class.forName(entry.getKey());
			genClient(c, userPath + entry.getKey().replace('.', File.separatorChar));
			proxyClass.add(c);
		}
		genConfiguration(proxyClass, userPath, configPackage);
	}

	/**
	 * 生成配置类
	 *
	 * @param proxyClass
	 * @param userPath
	 * @param packageName
	 */
	private static void genConfiguration(List<Class<?>> proxyClass, String userPath, String packageName) {
		String cl = "package " + packageName + ";\n";
		cl += "@SpringBootConfiguration\n";
		cl += "public class FallBackConfiguration {\n";
		for (Class<?> c : proxyClass) {
			String bean = "\n@Bean\n";
			String name = c.getSimpleName();
			name = name.substring(0, 2).toLowerCase() + name.substring(2);
			bean += "public " + c.getTypeName() + "Fallback" + " " + name + "(){\n";
			bean += "return new " + c.getTypeName() + "Fallback();\n";
			bean += "}\n";
			cl += bean;
		}
		cl += "\n}";
		System.out.println(cl);
		System.out.println(userPath + packageName.replace('.', File.separatorChar) + File.separator + "FallBackConfiguration.java");
	}

	/**
	 * 生成fallback 类
	 *
	 * @param clazz
	 * @param targetFilePath
	 */
	private static void genClient(Class<?> clazz, String targetFilePath) {
		Method[] methods = clazz.getMethods();
		String cl = "package " + clazz.getPackage().getName() + ";\n";
		cl += "public class " + clazz.getSimpleName() + "Fallback" + " implements " + clazz.getTypeName() + "{ \n";
		for (Method m : methods) {
			String me = "@Override \n public " + m.getReturnType().getTypeName() + " " + m.getName() + "(";
			for (int i = 0; i < m.getParameterCount(); i++) {
				Parameter p = m.getParameters()[i];
				me += p.getParameterizedType().getTypeName() + " " + p.getName() + "";
				if (i == m.getParameterCount() - 1) {
					me += ")";
				} else {
					me += ",";
				}
			}
			me += "{ throw  new RuntimeException(); }\n";
			cl += me;
		}
		cl += "}";
		System.out.println(cl);
	}
	//一段配置，临时注释在这里，
//	#Hystrix支持，如果为true，hystrix库必须在classpath中
//	feign.hystrix.enabled=true
//
//			#请求和响应GZIP压缩支持
//	feign.compression.request.enabled=true
//	feign.compression.response.enabled=true
//			#支持压缩的mime types
//	feign.compression.request.mime-types=text/xml,application/xml,application/json
//	feign.compression.request.min-request-size=2048
}
