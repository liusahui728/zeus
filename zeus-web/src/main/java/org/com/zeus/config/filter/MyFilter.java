package org.com.zeus.config.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/", filterName = "myFilter")
public class MyFilter implements Filter {
	Logger logger=LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter init-----------------------------");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 过滤用户请求，判断是否登录
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		System.out.println("getRequestURL: " + httpServletRequest.getRequestURL());
		System.out.println("getRequestURI: " + httpServletRequest.getRequestURI());
		System.out.println("getQueryString: " + httpServletRequest.getQueryString());
		System.out.println("getRemoteAddr: " + httpServletRequest.getRemoteAddr());
		System.out.println("getRemoteHost: " + httpServletRequest.getRemoteHost());
		System.out.println("getRemotePort: " + httpServletRequest.getRemotePort());
		System.out.println("getRemoteUser: " + httpServletRequest.getRemoteUser());
		System.out.println("getLocalAddr: " + httpServletRequest.getLocalAddr());
		System.out.println("getLocalName: " + httpServletRequest.getLocalName());
		System.out.println("getLocalPort: " + httpServletRequest.getLocalPort());
		System.out.println("getMethod: " + httpServletRequest.getMethod());
		System.out.println("-------request.getParamterMap()-------");
		// 得到请求的参数Map，注意map的value是String数组类型
		Map map = request.getParameterMap();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			String[] values = (String[]) map.get(key);
			for (String value : values) {
				System.out.println(key + "=" + value);
			}
		}
		System.out.println("--------request.getHeader()--------");
		// 得到请求头的name集合
		Enumeration<String> em = httpServletRequest.getHeaderNames();
		while (em.hasMoreElements()) {
			String name = (String) em.nextElement();
			String value = httpServletRequest.getHeader(name);
			System.out.println(name + "=" + value);
		}

		httpServletRequest.getParameter("account");
		 //获取json
        String contentType = httpServletRequest.getContentType();
        /**
         * 直接取会导致错误，需要包装成HttpServletRequestWrapper
         */
/*        String paramJson = "";
        if ("application/json".equals(contentType)) {
            paramJson = this.getJsonParam(httpServletRequest);
        } else {
            paramJson = JSON.toJSONString(httpServletRequest.getParameterMap());
        }
		System.out.println(paramJson);
		System.out.println("-----------------------------doFilter");*/
		chain.doFilter(request, response);
	}
	

    /**
     * 获取Json数据
     *
     * @param request
     * @return
     */
    private String getJsonParam(HttpServletRequest request) {
        String jsonParam = "";
        ServletInputStream inputStream = null;
        try {
            int contentLength = request.getContentLength();
            if (!(contentLength < 0)) {
                byte[] buffer = new byte[contentLength];
                inputStream = request.getInputStream();
                for (int i = 0; i < contentLength; ) {
                    int len = inputStream.read(buffer, i, contentLength);
                    if (len == -1) {
                        break;
                    }
                    i += len;
                }
                jsonParam = new String(buffer, "utf-8");
            }
        } catch (IOException e) {
        	logger.error("参数转换成json异常g{}", e);
        } /*finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                	logger.error("参数转换成json异常s{}", e);
                }
            }
        }*/
        return jsonParam;
    }

	@Override
	public void destroy() {}

}
