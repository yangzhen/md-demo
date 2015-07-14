package com.md.demo.server.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.md.demo.server.bean.param.TestGetParam;
import com.md.demo.server.bean.vo.ResultMsg;
import com.md.demo.server.bean.vo.TestGetResult;
import com.md.demo.server.common.constant.Constant;
import com.md.demo.server.service.TestService;

/**
 * 
 * TestController 
 * @author chenchao
 * @date Jul 14, 2015 10:40:02 AM
 *
 */

@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private TestService testService;

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "getDemo", method = RequestMethod.GET)
	@ResponseBody
	public ResultMsg<TestGetResult> getDemo(TestGetParam param,
			HttpServletRequest request) {

		long start = System.currentTimeMillis();

		String ip = getIpAddr(request);

		String text = testService.testResult();

		TestGetResult data = new TestGetResult();
		data.setResult(text);

		long methodCost = System.currentTimeMillis() - start;

		logger.info(request.getRequestURI() + Constant.LOG_SPLIT + ip + Constant.LOG_SPLIT
				+ 200 + Constant.LOG_SPLIT + "SUCCESS" + Constant.LOG_SPLIT + methodCost
				+ Constant.LOG_SPLIT + param.toString());

		return new ResultMsg<TestGetResult>(data, 200, "success");
	}

	/**
	 * 获取ip地址
	 * @param request
	 * @return ip地址
	 */
	private String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		// tengine config
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("NS-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		int index = ip.indexOf(",");

		if (index > 0) {
			ip = ip.substring(0, index);
		}

		return ip;

	}

}
