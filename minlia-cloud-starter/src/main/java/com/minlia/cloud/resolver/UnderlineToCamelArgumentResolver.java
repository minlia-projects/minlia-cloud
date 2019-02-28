package com.minlia.cloud.resolver;

import com.google.common.base.CaseFormat;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.minlia.cloud.annotation.ParameterModel;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;

public class UnderlineToCamelArgumentResolver extends AbstractCustomizeResolver {

	/**
	 * Whether the given {@linkplain MethodParameter method parameter} is
	 * supported by this resolver.
	 * @param parameter the method parameter to check
	 * @return {@code true} if this resolver supports the supplied parameter;
	 * {@code false} otherwise
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(ParameterModel.class);
	}
 
	/**
	 * 装载参数
	 *
	 * @param methodParameter       方法参数
	 * @param modelAndViewContainer 返回视图容器
	 * @param nativeWebRequest      本次请求对象
	 * @param webDataBinderFactory  数据绑定工厂
	 * @return the resolved argument value, or {@code null}
	 * @throws Exception in case of errors with the preparation of argument values
	 */
	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//		Object org=handleParameterNames(methodParameter, nativeWebRequest);
//		valid(methodParameter,modelAndViewContainer,nativeWebRequest,webDataBinderFactory,org);

		Object jsonObj = handleJsonParameterNames(methodParameter, nativeWebRequest);
		valid(methodParameter,modelAndViewContainer,nativeWebRequest,webDataBinderFactory,jsonObj);
		return jsonObj;
	}

	//处理地址参数
	private Object handleParameterNames(MethodParameter parameter, NativeWebRequest webRequest) {
		Object obj = BeanUtils.instantiate(parameter.getParameterType());
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
		Iterator<String> paramNames = webRequest.getParameterNames();

		while (paramNames.hasNext()) {
			String paramName = paramNames.next();
			Object o = webRequest.getParameter(paramName);
//			wrapper.setPropertyValue(StringHelpers.underLineToCamel(paramName), o);

			String formatted = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, paramName);
			wrapper.setPropertyValue(formatted, o);
		}
		return obj;
	}

	//处理Json参数
	private Object handleJsonParameterNames(MethodParameter parameter, NativeWebRequest webRequest) {
		Object obj = BeanUtils.instantiate(parameter.getParameterType());
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);

		// 有就直接获取
		String jsonBody = getRequestBody(webRequest);
		JSONObject jsonObject = JSONObject.fromObject(jsonBody);

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gsonBuilder.create();

		Iterator<String> it = jsonObject.keys();
		while(it.hasNext()){
			String key = it.next();
			String value = jsonObject.getString(key);

			//判断是否包含下划线
			if (containUnderLine(key)) {
				key = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
			}

			//如果参数为json
			if (isJson(value)) {
				Class<?> t = wrapper.getPropertyType(key);
				wrapper.setPropertyValue(key, gson.fromJson(value, t));
			} else {
				wrapper.setPropertyValue(key, jsonObject.get(key));
			}
		}
		return obj;
	}

	private static final String JSONBODY_ATTRIBUTE = "JSON_REQUEST_BODY";

	/**
	 * 获取请求体JSON字符串
	 */
	private String getRequestBody(NativeWebRequest webRequest) {
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

		// 有就直接获取
		String jsonBody = (String) webRequest.getAttribute(JSONBODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
		// 没有就从请求中读取
		if (jsonBody == null) {
			try {
				jsonBody = IOUtils.toString(servletRequest.getReader());
				webRequest.setAttribute(JSONBODY_ATTRIBUTE, jsonBody, NativeWebRequest.SCOPE_REQUEST);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return jsonBody;
	}

	public static boolean isJson(String content) {
		try {
			JSONObject.fromObject(content);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 匹配下划线的格式
	 */
	private static boolean containUnderLine(String source) {
		return source.contains("_");
	}

}